package com.mg.catalog.document

import com.mg.catalog.domain.ParentChildNode
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "categories")
data class CategoryDocument(
        @Id override val _id: String? = "0",
        override val parent: String? = null,
        override val name: String? = "top") : ParentChildNode