package com.gt.spring_graphql

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BookRepository : JpaRepository<BookEntity, UUID> {
    fun findAll(spec: Specification<*>, pageable: Pageable): Page<BookEntity>
}