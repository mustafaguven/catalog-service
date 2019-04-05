package com.mg.catalog.domain

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mg.catalog.document.CategoryDocument

class SimpleCategoryTree(id: String, node: CategoryDocument) {
    private val map = HashMap<String, CategoryDocument>()
    private val root: CategoryDocument = node
    private val gson: Gson

    init {
        map[id] = root
        gson = GsonBuilder().create()
    }

    companion object {
        private const val ROOT_ID = "0"
        private const val ROOT_TITLE = "root"
        private const val ROOT_TYPE = "Root"
        fun createTree() = SimpleCategoryTree(ROOT_ID, CategoryDocument(_id = ROOT_ID, title = ROOT_TITLE, type = ROOT_TYPE))
    }

    fun addChild(categoryDocument: CategoryDocument) {
        val parent = map[categoryDocument.parentId ?: ROOT_ID]
        parent?.categories?.add(categoryDocument)
        map[categoryDocument._id!!] = categoryDocument
    }

    fun show(id: String? = ROOT_ID): String {
        return gson.toJson(map[id])
    }
}