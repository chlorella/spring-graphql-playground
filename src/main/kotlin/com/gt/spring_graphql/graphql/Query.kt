package com.gt.spring_graphql.graphql

import com.expediagroup.graphql.annotations.GraphQLID
import com.expediagroup.graphql.spring.operations.Query
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.jooq.generated.Tables
import org.jooq.generated.tables.records.AuthorRecord
import org.jooq.impl.DSL
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.BeanFactoryAware
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.CompletableFuture


interface BookQuery : Query {
    suspend fun getBookById(id: String): BookModel?
//    fun list(payload: BookListPayload): BookListModel?
}

@Component
class BookQueryImpl : BookQuery {

    @Value("\${datasource.url}")
    var url: String = "jdbc:postgresql://localhost:5432/postgres"

    @Value("\${datasource.username}")
    var username: String = "postgres"

    @Value("\${datasource.password}")
    var password: String = "123456"

    override suspend fun getBookById(@GraphQLID id: String): BookModel? {
        return DSL.using(url, username, password).use { ctx ->
            ctx.selectFrom(Tables.BOOK)
                    .where(Tables.BOOK.ID.eq(UUID.fromString(id))).fetchOne()
        }.let { BookModel(it) }
    }

//    override fun list(payload: BookListPayload): BookListModel? = bookRepository.findAll(
//            payload.toSpec(), payload.toPage()).let { BookListModel(it) }
}

