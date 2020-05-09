package com.gt.spring_graphql

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BookRepo : CrudRepository<BookEntity, UUID> {
//    override fun findById(id: UUID): Optional<BookEntity>
}