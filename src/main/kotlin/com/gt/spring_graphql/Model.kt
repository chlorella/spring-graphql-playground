package com.gt.spring_graphql

import org.springframework.stereotype.Component

//@Component
data class Book (
        val id: Int,
        val name: String?,
        val publishDate: String
)