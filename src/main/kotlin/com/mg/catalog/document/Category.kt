package com.mg.catalog.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "categories")
data class Category(@Id val _id: String, val parentId: String, val name: String)