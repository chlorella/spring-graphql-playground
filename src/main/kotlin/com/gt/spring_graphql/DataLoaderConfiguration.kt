package com.gt.spring_graphql

import com.expediagroup.graphql.spring.execution.DataLoaderRegistryFactory
import org.dataloader.DataLoader
import org.dataloader.DataLoaderRegistry
import org.jooq.DSLContext
import org.jooq.generated.Tables
import org.jooq.generated.tables.records.BookRecord
import org.jooq.impl.DSL
import org.springframework.context.annotation.Configuration
import java.util.*
import java.util.concurrent.CompletableFuture

@Configuration
class DataLoaderConfiguration {
    private val properties = Properties()
    private lateinit var dsl: DSLContext

    init {
        properties.load(Properties::class.java.getResourceAsStream("/src/main/resources/application.yml"))
        dsl = DSL.using(
                properties.getProperty("datasource.url"),
                properties.getProperty("datasource.username"),
                properties.getProperty("datasource.password")
        )
    }

    fun dataLoaderRegistryFactory(): DataLoaderRegistryFactory = object : DataLoaderRegistryFactory {
        override fun generate(): DataLoaderRegistry {
//            TODO("Not yet implemented")
            val registry = DataLoaderRegistry()
            val bookLoader = DataLoader<String?, BookRecord?> { ids ->
                CompletableFuture.supplyAsync {
                    dsl.use { ctx ->
                        ctx.selectFrom(Tables.BOOK)
                                .where(Tables.BOOK.ID.`in`(ids.map { UUID.fromString(it) })).fetch()
                    }
                }
            }

            registry.register("bookLoader", bookLoader)
            return registry
        }
    }

//    var userBatchLoader: BatchLoader<String?, BookRecord?> = BatchLoader<String?, BookRecord?> { ids ->
//        CompletableFuture.supplyAsync(Supplier {
//            dsl.use { ctx ->
//                ctx.selectFrom(Tables.BOOK)
//                        .where(Tables.BOOK.ID.`in`(ids.map{UUID.fromString(it)})).fetch()
//            }
//        })
//    }


}