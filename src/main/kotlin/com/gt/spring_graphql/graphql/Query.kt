package com.gt.spring_graphql.graphql

import com.expediagroup.graphql.spring.operations.Query
import com.gt.spring_graphql.BookRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class BookQuery : Query {
    @Autowired
    lateinit var bookRepo: BookRepo

    fun getBookById(id: String): BookModel? {
        return bookRepo.findById(UUID.fromString(id)).orElse(null)?.let {
            BookModel(it)
        }
    }

    fun list(): List<BookModel>? = bookRepo.findAll().map{
        BookModel(it)
    }
}