package com.gt.spring_graphql.graphql

import org.jooq.DSLContext
import org.jooq.generated.Tables
import org.jooq.generated.tables.records.AuthorRecord
import org.jooq.generated.tables.records.BookRecord
import org.jooq.impl.DSL
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

interface DbService {
    fun getBooksById(id: String): BookRecord
    fun getBooksByIds(ids: List<String>): List<BookRecord>
    fun getAuthorByIds(ids: List<String>): List<AuthorRecord>
}

@Component
class DbServiceImpl : DbService {
    private lateinit var dsl: DSLContext

    @Value("\${datasource.url}")
    var url: String = "jdbc:postgresql://localhost:5432/postgres"

    @Value("\${datasource.username}")
    var username: String = "postgres"

    @Value("\${datasource.password}")
    var password: String = "123456"

    override fun getBooksById(id: String): BookRecord {
        return DSL.using(url, username, password).use { ctx ->
            ctx.selectFrom(Tables.BOOK)
                    .where(Tables.BOOK.ID.eq(UUID.fromString(id))).fetchOne()
        }
    }

    override fun getBooksByIds(ids: List<String>): List<BookRecord> {
        return DSL.using(url, username, password).use { ctx ->
            ctx.selectFrom(Tables.BOOK)
                    .where(Tables.BOOK.ID.`in`(ids.map { UUID.fromString(it) })).fetch().toList()
        }
    }

    override fun getAuthorByIds(ids: List<String>): List<AuthorRecord> {
        return DSL.using(url, username, password).use { ctx ->
            ctx.selectFrom(Tables.AUTHOR)
                    .where(Tables.AUTHOR.ID.`in`(ids.map { UUID.fromString(it) })).fetch().toList()
        }
    }
}