package com.gt.spring_graphql

import com.expediagroup.graphql.SchemaGeneratorConfig
import com.expediagroup.graphql.TopLevelObject
import com.expediagroup.graphql.toSchema
import com.gt.spring_graphql.graphql.BookQuery
import graphql.schema.GraphQLSchema
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringGraphqlApplication

fun main(args: Array<String>) {
//    val config = SchemaGeneratorConfig(listOf("com.gt.spring_graphql"))
//    val queries = listOf(TopLevelObject(BookQuery()))
//    val schema: GraphQLSchema = toSchema(config, queries)

    runApplication<SpringGraphqlApplication>(*args)
}
