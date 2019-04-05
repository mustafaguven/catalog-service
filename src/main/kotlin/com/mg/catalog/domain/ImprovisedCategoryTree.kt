package com.mg.catalog.domain

import com.mg.catalog.document.CategoryDocument

class ImprovisedCategoryTree(id: String, category: CategoryDocument) : AbstractTree<String, CategoryDocument>(id, category) {

    companion object {
        fun createTree() = ImprovisedCategoryTree("0", CategoryDocument("0", "top"))
    }

    fun show() = super.show( "0")

    fun addChild(parentId: String?, id: String?, node: CategoryDocument) {
        super.addChild(parentId ?: "0", id ?: "0", node)
    }

}