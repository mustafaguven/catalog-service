package com.mg.catalog.repository

import com.mg.catalog.document.BrandDocument
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface BrandRepository : MongoRepository<BrandDocument, String> {

    fun findBy_id(_id: ObjectId): BrandDocument

}