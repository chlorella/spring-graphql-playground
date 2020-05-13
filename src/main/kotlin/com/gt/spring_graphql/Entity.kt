package com.gt.spring_graphql

import com.gt.spring_graphql.graphql.BookModel
import com.gt.spring_graphql.graphql.CommentModel
import com.gt.spring_graphql.graphql.UserModel
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "book")
class BookEntity(
        var name: String?,
        var publishDate: Date?,
        @Id
        var id: UUID = UUID.randomUUID()

) {
    constructor() : this("", Date())
    constructor(model: BookModel) : this(model.name, Date())

    @ManyToOne(cascade= [CascadeType.ALL])
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_comment",
            joinColumns = [JoinColumn(name = "book_id")],
            inverseJoinColumns = [JoinColumn(name = "comment_id")])
    var comments: List<CommentEntity> = emptyList()
}

@Entity
@Table(name = "comment")
class CommentEntity(
        var content: String?,
        @Id
        var id: UUID = UUID.randomUUID()

) {
    constructor() : this("")
    constructor(model: CommentModel) : this(model.content)

    @ManyToOne(cascade= [CascadeType.ALL])
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null
}

@Entity
@Table(name = "author")
class UserEntity(
        var name: String?,
        var avatar: String?,
        @Id
        var id: UUID = UUID.randomUUID()
){
    constructor() :this("","")
    constructor(model: UserModel) : this(model.name, model.avatar)
}