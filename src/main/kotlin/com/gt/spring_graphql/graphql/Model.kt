package com.gt.spring_graphql.graphql

import com.gt.spring_graphql.BookEntity
import org.joda.time.DateTime

//@Component
data class BookModel (
    var id: String,
    var name: String?,
    var publishDate: String,
    var comments: List<CommentModel>,
    var author: UserModel?
){
    constructor(entity: BookEntity):
            this(
                    id = entity.id.toString(),
                    name = entity.name,
                    publishDate = DateTime(entity.publishDate).toString("yyyy/MM/dd"),
                    comments = emptyList(),
                    author = null
            )
}

data class CommentModel (
    var id: String,
    var content: String?,
    var publishDate: String,
    var user: UserModel
)

data class UserModel (
    var id: String,
    var name: String?,
    var avatar: String?
)