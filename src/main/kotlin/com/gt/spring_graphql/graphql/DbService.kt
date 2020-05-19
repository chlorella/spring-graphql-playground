package com.gt.spring_graphql.graphql

import org.jooq.DSLContext
import org.jooq.generated.Tables
import org.jooq.generated.tables.records.AuthorRecord
import org.jooq.generated.tables.records.BookRecord
import org.jooq.generated.tables.records.CommentRecord
import org.jooq.impl.DSL
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

interface DbService {
    fun getBooksById(id: String): BookRecord
    fun getBooksByIds(ids: List<String>): List<BookRecord>
    fun getAuthorByIds(ids: List<String>): List<AuthorRecord>
    fun getCommentsByBookId(id: String): List<UUID>
    fun getCommentsByIds(ids: List<UUID>): List<CommentRecord>
}

@Component
class DbServiceImpl : DbService {

    @Value("\${datasource.url}")
    final var url: String = "jdbc:postgresql://localhost:5432/postgres"

    @Value("\${datasource.username}")
    final var username: String = "postgres"

    @Value("\${datasource.password}")
    final var password: String = "123456"


    override fun getBooksById(id: String): BookRecord {
        return  DSL.using(url, username, password).use { ctx ->
            ctx.selectFrom(Tables.BOOK)
                    .where(Tables.BOOK.ID.eq(UUID.fromString(id))).fetchOne()
        }
    }

    override fun getBooksByIds(ids: List<String>): List<BookRecord> {
        return  DSL.using(url, username, password).use { ctx ->
            ctx.selectFrom(Tables.BOOK)
                    .where(Tables.BOOK.ID.`in`(ids.map { UUID.fromString(it) })).fetch().toList()
        }
    }

    override fun getAuthorByIds(ids: List<String>): List<AuthorRecord> {
        return  DSL.using(url, username, password).use { ctx ->
            ctx.selectFrom(Tables.AUTHOR)
                    .where(Tables.AUTHOR.ID.`in`(ids.map { UUID.fromString(it) })).fetch().toList()
        }
    }

    override fun getCommentsByBookId(id: String): List<UUID> {
        return  DSL.using(url, username, password).use { ctx ->
            ctx.selectFrom(Tables.COMMENT).where(Tables.COMMENT.BOOK_ID.eq( UUID.fromString(id)))
                    .fetch(Tables.COMMENT.ID).toList()
        }
    }

    override fun getCommentsByIds(ids: List<UUID>): List<CommentRecord> {
        return  DSL.using(url, username, password).use { ctx ->
            ctx.selectFrom(Tables.COMMENT).where(Tables.COMMENT.ID.`in`(ids.map { it })).fetch().toList()
        }
    }
}