package com.mg.catalog.repository

import com.mg.catalog.document.Category
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface CategoryRepository : MongoRepository<Category, String> {

    fun findBy_id(_id: ObjectId): Category

}