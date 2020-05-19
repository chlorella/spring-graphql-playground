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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import java.util.concurrent.CompletableFuture

@Configuration
class DataLoaderConfiguration {
    @Autowired
    lateinit var dbService: DbService

    @Bean
    fun dataLoaderRegistryFactory(): DataLoaderRegistryFactory = object : DataLoaderRegistryFactory {

        override fun generate(): DataLoaderRegistry {
            val registry = DataLoaderRegistry()
            val bookLoader = DataLoader<String, BookRecord?> { ids ->
                CompletableFuture.supplyAsync {
                    dbService.getBooksByIds(ids)
                }
            }

            val authorLoader = DataLoader<String, AuthorRecord?> { ids ->
                CompletableFuture.supplyAsync {
                    dbService.getAuthorByIds(ids)
                }
            }

            val commentLoader = DataLoader<UUID, CommentRecord?> { ids ->
                CompletableFuture.supplyAsync {
                    dbService.getCommentsByIds(ids)
                }
            }

            registry.register("bookLoader", bookLoader)
            registry.register("authorLoader", authorLoader)
            registry.register("commentLoader", commentLoader)

            return registry
        }
    }


}