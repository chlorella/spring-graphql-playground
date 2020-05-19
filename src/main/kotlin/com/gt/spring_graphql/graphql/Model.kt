package com.gt.spring_graphql.graphql

import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.annotations.GraphQLID
import com.expediagroup.graphql.annotations.GraphQLIgnore
import graphql.schema.DataFetchingEnvironment
import kotlinx.coroutines.future.await
import org.joda.time.DateTime
import org.jooq.generated.tables.records.AuthorRecord
import org.jooq.generated.tables.records.BookRecord
import org.jooq.generated.tables.records.CommentRecord
import java.time.format.DateTimeFormatter
import java.util.*


data class BookListPayload(
        var filterBookName: String?,
        var filterAuthorName: String?,
        var filterKeyword: String?,
        var sortBy: SortBy?,
//        var sortOrder: Sort.Direction?,
        var page: Int?,
        @GraphQLDescription("Page size must be greater than 1")
        var pageSize: Int?
) {
//    fun toSpec(): Specification<BookEntity> = Specification { root, _, cb ->
//        val predicates = mutableListOf<Predicate>()
//        filterBookName?.takeIf { it.isNotBlank() }?.apply {
//            predicates.add(cb.equal(root.get<String>("name"), this))
//        }
//        filterAuthorName?.takeIf { it.isNotBlank() }?.apply {
//            predicates.add(cb.equal(root.get<UserEntity>("user").get<String>("name"), this))
//        }
//        filterKeyword?.takeIf { it.isNotBlank() }?.apply {
//            predicates.add(cb.or(cb.like(root.get("name"), "%${this}%"),
//                    cb.like(root.get<UserEntity>("user").get<String>("name"), "%${this}%")))
//        }
//        cb.and(*predicates.toTypedArray())
//    }
//
//    fun toPage(): Pageable = PageRequest.of(page ?: 0, pageSize ?: 1, Sort.by(
//            sortOrder ?: Sort.Direction.DESC, sortBy?.field ?: SortBy.BOOK_PUBLISH_DATE.field
//    ))
}

enum class SortBy(val field: String) {
    BOOK_NAME("name"), BOOK_PUBLISH_DATE("publishDate")
}

//data class BookListModel(
//        var totalCount: Long = 0,
//        var totalPage: Int = 0,
//        var books: List<BookModel> = emptyList()
//) {
//    constructor(page: Page<BookEntity>) : this(
//            totalCount = page.totalElements,
//            totalPage = page.number + 1,
//            books = page.content.map {
//                println("$it")
//                BookModel(it)
//            })
//}

data class BookModel(
        @GraphQLID
        var id: String,
        var name: String?,
        var publishDate: String,
        @GraphQLIgnore
        var commentIds: List<UUID> = emptyList(),
        @GraphQLIgnore
        var authorId: String? = ""
) {
    suspend fun author(env: DataFetchingEnvironment): UserModel? {
        return env.getDataLoader<String?, AuthorRecord?>("authorLoader")
                ?.load(authorId)?.thenApply { entity -> entity?.let { UserModel(it) } }?.await()
    }

    suspend fun comments(env: DataFetchingEnvironment): List<CommentModel>? {
        return env.getDataLoader<UUID?, CommentRecord?>("commentLoader")
                ?.loadMany(commentIds)?.thenApply { list -> list?.filterNotNull()?.map { CommentModel(it) } }?.await() ?: emptyList()
    }

    constructor(entity: BookRecord, commentIds: List<UUID>
    ) : this(
            id = entity.id.toString(),
            name = entity.name,
            publishDate = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(entity.publishDate),
            authorId = entity.userId?.toString(),
            commentIds = commentIds
    )
}

data class CommentModel(
        @GraphQLID
        var id: String,
        var content: String?,
        @GraphQLIgnore
        var authorId: String? = "",
        @GraphQLIgnore
        var bookId: String? = ""
) {
    suspend fun author(env: DataFetchingEnvironment): UserModel? {
        return env.getDataLoader<String?, AuthorRecord?>("authorLoader")
                ?.load(authorId)?.thenApply { entity -> entity?.let { UserModel(it) } }?.await()
    }

    constructor(entity: CommentRecord) : this(
            id = entity.id.toString(),
            content = entity.content,
            authorId = entity.userId?.toString(),
            bookId = entity.bookId?.toString()
    )
}

data class UserModel(
        @GraphQLID
        var id: String,
        var name: String?,
        var avatar: String?
) {
    constructor(entity: AuthorRecord) : this(
            id = entity.id.toString(),
            name = entity.name,
            avatar = entity.avatar
    )
}