package com.gt.spring_graphql.graphql

import org.jooq.DSLContext
import org.jooq.generated.Tables
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DbServiceTest {

    @Autowired
    private lateinit var dsl: DSLContext

    @Test
    fun seek() {
        dsl.selectFrom(Tables.BOOK)
                .where()
                .orderBy(Tables.BOOK.NAME.asc()).seek("book 1").limit(2).fetch()
    }

    @Test
    fun getBookList() {
    }

    @Test
    fun getBooksById() {
    }

    @Test
    fun getBooksByIds() {
    }

    @Test
    fun getAuthorByIds() {
    }

    @Test
    fun getCommentsByBookId() {
    }

    @Test
    fun getCommentsByIds() {
    }
}