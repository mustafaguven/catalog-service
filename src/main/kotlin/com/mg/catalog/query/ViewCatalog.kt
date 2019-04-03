package com.mg.catalog.query

import com.mg.catalog.document.CategoryDocument
import com.mg.catalog.domain.CategoryTree
import com.mg.catalog.domain.ParentChildNode
import com.mg.catalog.repository.CategoryRepository
import com.mg.eventbus.inline.logger
import org.bson.types.ObjectId
import org.springframework.stereotype.Component

@Component
class ViewCatalog(val categoryRepository: CategoryRepository) {

    companion object {
        val log = logger(this)
    }


    fun showAll(): String {
        val allCategories = categoryRepository.findAll()
        val tree = CategoryTree("0", CategoryDocument())
        allCategories.forEach {
            tree.addChild(it.parent ?: "0", it._id, it)
        }
        return tree.subtreeToString("0")

    }


    //fun showCatalogItems(_id: ObjectId) = categoryRepository.findBy_id(_id)


    fun showCatalogItems(_id: ObjectId): String {
        val allCategories = categoryRepository.findAll()
        val tree = CategoryTree("0", CategoryDocument())
        allCategories.forEach {
            tree.addChild(it.parent ?: "0", it._id, it)
        }

        return tree.subtreeToString(_id.toString())

    }
}