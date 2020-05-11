package com.gt.spring_graphql.graphql

import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.annotations.GraphQLID
import com.gt.spring_graphql.BookEntity
import com.gt.spring_graphql.CommentEntity
import com.gt.spring_graphql.UserEntity
import org.joda.time.DateTime
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.Predicate


data class BookListPayload(
        var filterBookName: String?,
        var filterAuthorName: String?,
        var filterKeyword: String?,
        var sortBy: SortBy?,
        var sortOrder: Sort.Direction?,
        var page: Int?,
        @GraphQLDescription("Page size must be greater than 1")
        var pageSize: Int?
) {
    fun toSpec(): Specification<BookEntity> = Specification { root, _, cb ->
        val predicates = mutableListOf<Predicate>()
        filterBookName?.takeIf { it.isNotBlank() }?.apply {
            predicates.add(cb.equal(root.get<String>("name"), this))
        }
        filterAuthorName?.takeIf { it.isNotBlank() }?.apply {
            predicates.add(cb.equal(root.get<UserEntity>("user").get<String>("name"), this))
        }
        filterKeyword?.takeIf { it.isNotBlank() }?.apply {
            predicates.add(cb.or(cb.like(root.get("name"), "%${this}%"),
                    cb.like(root.get<UserEntity>("user").get<String>("name"), "%${this}%")))
        }
        cb.and(*predicates.toTypedArray())
    }

    fun toPage(): Pageable = PageRequest.of(page ?: 0, pageSize ?: 1, Sort.by(
            sortOrder ?: Sort.Direction.DESC, sortBy?.field ?: SortBy.BOOK_PUBLISH_DATE.field
    ))
}

enum class SortBy(val field: String) {
    BOOK_NAME("name"), BOOK_PUBLISH_DATE("publishDate")
}

data class BookListModel(
        var totalCount: Long = 0,
        var totalPage: Int = 0,
        var books: List<BookModel> = emptyList()
) {
    constructor(page: Page<BookEntity>) : this(
            totalCount = page.totalElements,
            totalPage = page.number + 1,
            books = page.content.map {
                println("$it")
                BookModel(it)
            })
}

data class BookModel(
        @GraphQLID
        var id: String,
        var name: String?,
        var publishDate: String,
        var comments: List<CommentModel> = emptyList(),
        var author: UserModel?
) {
    constructor(entity: BookEntity) :
            this(
                    id = entity.id.toString(),
                    name = entity.name,
                    publishDate = DateTime(entity.publishDate).toString("yyyy/MM/dd"),
                    comments = entity.comments.map { CommentModel(it) },
                    author = entity.user?.let { UserModel(it) }
            )
}

data class CommentModel(
        @GraphQLID
        var id: String,
        var content: String?,
        var author: UserModel?
) {
    constructor(entity: CommentEntity) : this(
            id = entity.id.toString(),
            content = entity.content,
            author = entity.user?.let { UserModel(it) }
    )
}

data class UserModel(
        @GraphQLID
        var id: String,
        var name: String?,
        var avatar: String?
) {
    constructor(entity: UserEntity) : this(
            id = entity.id.toString(),
            name = entity.name,
            avatar = entity.avatar
    )
}