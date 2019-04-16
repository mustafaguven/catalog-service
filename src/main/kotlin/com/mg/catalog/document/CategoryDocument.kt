package com.mg.catalog.document

import com.mg.catalog.config.MongoDBConfig
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document(collection = MongoDBConfig.CATEGORIES)
data class CategoryDocument(
        @Id val _id: String? = null,
        val parentId: String? = null,
        val title: String? = null,
        val priority: Int? = 0,
        val type: String? = "",
        var categories: MutableList<CategoryDocument>? = ArrayList()) : Serializable