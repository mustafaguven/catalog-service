package com.mg.catalog.domain

import com.mg.catalog.document.CategoryDocument
import com.mg.catalog.domain.response.GetCategoryResponse
import org.codehaus.jackson.map.ObjectMapper
import java.io.Serializable

class SimpleCategoryTree(id: String, node: CategoryDocument) : Serializable {
    private val map = HashMap<String, CategoryDocument>()
    private val root: CategoryDocument = node
    private val objectMapper: ObjectMapper

    init {
        map[id] = root
        objectMapper = ObjectMapper()
    }

    companion object {
        const val ROOT_ID = "0"
        private const val ROOT_TITLE = "root"
        private const val ROOT_TYPE = "Root"
        fun createTree() = SimpleCategoryTree(ROOT_ID, CategoryDocument(_id = ROOT_ID, title = ROOT_TITLE, type = ROOT_TYPE))
    }

    fun addChild(categoryDocument: CategoryDocument) {
        val parent = map[categoryDocument.parentId ?: ROOT_ID]
        parent?.categories?.add(categoryDocument)
        map[categoryDocument._id!!] = categoryDocument
    }

   fun show(id: String? = ROOT_ID): CategoryDocument{
        return map[id]!!
    }



}