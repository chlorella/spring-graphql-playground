package com.gt.spring_graphql

import com.gt.spring_graphql.graphql.BookModel
import com.gt.spring_graphql.graphql.UserModel
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "book")
class BookEntity(
        var name: String?,
        var publishDate: Date?,
//        var user: UserEntity? = null,
        @Id
        var id: UUID = UUID.randomUUID()

) {
    constructor() : this("", Date())
    constructor(model: BookModel) : this(model.name, Date())
}

@Entity
@Table(name = "user")
class UserEntity(
        var name: String?,
        var avatar: String?,
        @Id
        var id: UUID = UUID.randomUUID()
){
    constructor() :this("","")
    constructor(model: UserModel) : this(model.name, model.avatar)
}