package com.mg.catalog.repository

import com.mg.catalog.document.CategoryDocument
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface CategoryRepository : MongoRepository<CategoryDocument, String> {

    fun findBy_id(_id: ObjectId?): CategoryDocument?

}