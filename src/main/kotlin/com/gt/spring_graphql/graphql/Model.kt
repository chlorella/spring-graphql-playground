package com.gt.spring_graphql.graphql

import com.gt.spring_graphql.BookEntity
import com.gt.spring_graphql.CommentEntity
import com.gt.spring_graphql.UserEntity
import org.joda.time.DateTime
import java.util.*

//@Component
data class BookModel (
    var id: String,
    var name: String?,
    var publishDate: String,
    var comments: List<CommentModel> = emptyList(),
    var author: UserModel?
){
    constructor(entity: BookEntity):
            this(
                    id = entity.id.toString(),
                    name = entity.name,
                    publishDate = DateTime(entity.publishDate).toString("yyyy/MM/dd"),
                    comments = entity.comments.map{CommentModel(it)},
                    author = entity.user?.let{ UserModel(it) }
            )
}

data class CommentModel (
    var id: String,
    var content: String?,
    var author: UserModel?
){
    constructor(entity: CommentEntity): this(
            id = entity.id.toString(),
            content = entity.content,
            author = entity.user?.let{ UserModel(it) }
    )
}

data class UserModel (
    var id: String,
    var name: String?,
    var avatar: String?
){
    constructor(entity: UserEntity): this(
            id = entity.id.toString(),
            name = entity.name,
            avatar = entity.avatar
    )
}