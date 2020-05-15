package com.gt.spring_graphql.graphql

import com.expediagroup.graphql.spring.execution.DataLoaderRegistryFactory
import org.dataloader.DataLoader
import org.dataloader.DataLoaderRegistry
import org.jooq.DSLContext
import org.jooq.generated.Tables
import org.jooq.generated.tables.records.AuthorRecord
import org.jooq.generated.tables.records.BookRecord
import org.jooq.generated.tables.records.CommentRecord
import org.jooq.impl.DSL
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import java.util.concurrent.CompletableFuture

@Configuration
class DataLoaderConfiguration {
    private lateinit var dsl: DSLContext

    @Value("\${datasource.url}")
    var url: String = "jdbc:postgresql://localhost:5432/postgres"

    @Value("\${datasource.username}")
    var username: String = "postgres"

    @Value("\${datasource.password}")
    var password: String = "123456"

    @Bean
    fun dataLoaderRegistryFactory(): DataLoaderRegistryFactory = object : DataLoaderRegistryFactory {

        override fun generate(): DataLoaderRegistry {
            dsl = DSL.using(url, username, password)

            val registry = DataLoaderRegistry()
            val bookLoader = DataLoader<String?, BookRecord?> { ids ->
                CompletableFuture.supplyAsync {
                    dsl.use { ctx ->
                        ctx.selectFrom(Tables.BOOK)
                                .where(Tables.BOOK.ID.`in`(ids.map { UUID.fromString(it) })).fetch().toMutableList()
                    }
                }
            }

            val authorLoader = DataLoader<String?, AuthorRecord?> { ids ->
                CompletableFuture.supplyAsync {
                    dsl.use { ctx ->
                        ctx.selectFrom(Tables.AUTHOR)
                                .where(Tables.AUTHOR.ID.`in`(ids.map { UUID.fromString(it) })).fetch().toMutableList()
                    }
                }
            }

            val commentLoader = DataLoader<String?, CommentRecord?> { ids ->
                CompletableFuture.supplyAsync {
                    dsl.use { ctx ->
                        ctx.selectFrom(Tables.COMMENT)
                                .where(Tables.COMMENT.ID.`in`(ids.map { UUID.fromString(it) })).fetch().toMutableList()
                    }
                }
            }

            registry.register("bookLoader", bookLoader)
            registry.register("authorLoader", authorLoader)
            registry.register("commentLoader", commentLoader)

            return registry
        }
    }


}