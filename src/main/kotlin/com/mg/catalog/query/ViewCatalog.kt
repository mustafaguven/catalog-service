package com.mg.catalog.query

import com.mg.catalog.repository.CategoryRepository
import com.mg.eventbus.inline.logger
import org.bson.types.ObjectId
import org.springframework.stereotype.Component

@Component
class ViewCatalog(val categoryRepository: CategoryRepository) {

    companion object {
        val log = logger(this)
    }

    fun showAll() = categoryRepository.findAll()


    fun showCatalogItems(_id: ObjectId) = categoryRepository.findBy_id(_id)


}