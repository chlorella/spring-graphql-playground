package com.gt.spring_graphql.graphql

import com.expediagroup.graphql.annotations.GraphQLID
import com.expediagroup.graphql.spring.operations.Query
import com.gt.spring_graphql.BookRepository
import org.jooq.DSLContext
import org.jooq.generated.Tables.BOOK
import org.jooq.impl.DSL
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*


interface BookQuery: Query {
    fun getBookById(id: String): BookModel?
//    fun list(payload: BookListPayload): BookListModel?
}

@Component
class BookQueryImpl : BookQuery {
    @Autowired
    private lateinit var bookRepository: BookRepository



    override fun getBookById(@GraphQLID id: String): BookModel? {


        return null

    }

//    override fun list(payload: BookListPayload): BookListModel? = bookRepository.findAll(
//            payload.toSpec(), payload.toPage()).let { BookListModel(it) }
}