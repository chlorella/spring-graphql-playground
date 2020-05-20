package com.gt.spring_graphql.graphql

import com.expediagroup.graphql.annotations.GraphQLID
import com.expediagroup.graphql.spring.operations.Query
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


interface BookQuery : Query {
    suspend fun getBookById(id: String): BookModel?
    fun list(payload: BookListParam, limit :Int?, offset: Int?): List<BookModel>?
}

@Component
class BookQueryImpl : BookQuery {
    @Autowired
    lateinit var dbService: DbService

    override suspend fun getBookById(@GraphQLID id: String): BookModel? {
        return BookModel(dbService.getBooksById(id), dbService.getCommentsByBookId(id))
    }

    override fun list(payload: BookListParam, limit :Int?, offset: Int?): List<BookModel>? =
            dbService.getBookList(payload, limit ?: 10, offset ?: 0).map { BookModel(it, dbService.getCommentsByBookId(it.id.toString())) }
}

