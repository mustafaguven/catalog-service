package com.mg.catalog.document

import com.mg.catalog.config.MongoDBConfig
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = MongoDBConfig.BRANDS)
data class BrandDocument(
        @Id val _id: String? = "",
        val name: String? = "",
        val categories: List<String>? = null)
