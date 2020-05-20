package com.gt.spring_graphql.graphql

import org.jooq.DSLContext
import org.jooq.OrderField
import org.jooq.TableField
import org.jooq.generated.Tables
import org.jooq.generated.tables.records.AuthorRecord
import org.jooq.generated.tables.records.BookRecord
import org.jooq.generated.tables.records.CommentRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface DbService {
    fun getBooksById(id: String): BookRecord
    fun getBooksByIds(ids: List<String>): List<BookRecord>
    fun getAuthorByIds(ids: List<String>): List<AuthorRecord>
    fun getCommentsByBookId(bookId: String): List<UUID>
    fun getCommentsByIds(ids: List<UUID>): List<CommentRecord>
    fun getBookList(param: BookListParam, limit: Int, offset: Int): List<BookRecord>
}

@Component
@Transactional
class DbServiceImpl : DbService {

    @Autowired
    private lateinit var dsl: DSLContext

    private fun <T> TableField<BookRecord, T>.order(order: SortOrder): OrderField<T> {
        return this.apply {
            when (order) {
                SortOrder.ASC -> this.asc()
                SortOrder.DESC -> this.desc()
            }
        }
    }

    override fun getBookList(param: BookListParam, limit: Int, offset: Int): List<BookRecord> {
        val order = param.sortOrder ?: SortOrder.ASC

        val filter = param.filter?.let {
            when (it) {
                Filter.BOOK_NAME -> Tables.BOOK.NAME.like("%${param.content}%")
                Filter.BOOK_AUTHOR_NAME -> Tables.AUTHOR.NAME.like("%${param.content}%")
                Filter.KEYWORDS -> Tables.BOOK.NAME.like("%${param.content}%")
                        .or(Tables.AUTHOR.NAME.like("%${param.content}%"))
            }
        }

        val orderBy = param.orderBy.let {
            when (it) {
                OrderBy.BOOK_NAME -> Tables.BOOK.NAME.order(order)
                OrderBy.BOOK_PUBLISH_DATE -> Tables.BOOK.PUBLISH_DATE.order(order)
                else -> Tables.BOOK.NAME.order(order)
            }
        }

        return dsl.select().from(Tables.BOOK)
                .join(Tables.AUTHOR)
                .onKey()
                .where(filter)
                .orderBy(orderBy).limit(limit).offset(offset).fetchInto(Tables.BOOK).toList()
    }

    override fun getBooksById(id: String): BookRecord {
        return dsl.selectFrom(Tables.BOOK)
                .where(Tables.BOOK.ID.eq(UUID.fromString(id))).fetchAny()
    }

    override fun getBooksByIds(ids: List<String>): List<BookRecord> {
        return dsl.selectFrom(Tables.BOOK)
                .where(Tables.BOOK.ID.`in`(ids.map { UUID.fromString(it) })).fetch().toList()

    }

    override fun getAuthorByIds(ids: List<String>): List<AuthorRecord> {
        return dsl.selectFrom(Tables.AUTHOR)
                .where(Tables.AUTHOR.ID.`in`(ids.map { UUID.fromString(it) })).fetch().toList()
    }

    override fun getCommentsByBookId(bookId: String): List<UUID> {
        return dsl.select(Tables.COMMENT.ID).from(Tables.COMMENT)
                .where(Tables.COMMENT.BOOK_ID.eq(UUID.fromString(bookId)))
                .fetch(Tables.COMMENT.ID).toList()

    }

    override fun getCommentsByIds(ids: List<UUID>): List<CommentRecord> {
        return dsl.selectFrom(Tables.COMMENT)
                .where(Tables.COMMENT.ID.`in`(ids.map { it })).fetch().toList()
    }
}
