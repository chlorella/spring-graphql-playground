package com.gt.spring_graphql

import com.expediagroup.graphql.spring.operations.Query
import org.springframework.stereotype.Component

@Component
class BookQuery : Query {
    fun getBookById(id: Int): Book{
        return Book(1,"test","YYYY/MM/DD")
    }
}