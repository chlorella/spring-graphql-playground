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
import org.springframework.beans.factory.annotation.Autowired
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
    @Autowired
    lateinit var dbService: DbService

    override suspend fun getBookById(@GraphQLID id: String): BookModel? {
        return BookModel(dbService.getBooksById(id))
    }

//    override fun list(payload: BookListPayload): BookListModel? = bookRepository.findAll(
//            payload.toSpec(), payload.toPage()).let { BookListModel(it) }
}

