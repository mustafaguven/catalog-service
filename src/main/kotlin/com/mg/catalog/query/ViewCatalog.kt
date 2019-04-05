package com.mg.catalog.query

import com.mg.catalog.config.MongoDBConfig
import com.mg.catalog.document.CategoryDocument
import com.mg.catalog.domain.SimpleCategoryTree
import com.mg.catalog.repository.BrandRepository
import com.mg.catalog.repository.CategoryRepository
import com.mg.eventbus.inline.logger
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.LookupOperation
import org.springframework.stereotype.Component


@Component
class ViewCatalog(val categoryRepository: CategoryRepository,
                  val mongoTemplate: MongoTemplate,
                  val brandRepository: BrandRepository) {

    companion object {
        val log = logger(this)
    }

    fun showAllCatalogItemsWithChildren(): String {
        val tree = SimpleCategoryTree.createTree()
        val allCategories = lookupOperation()
        allCategories.forEach {
            tree.addChild(it)
        }
        return tree.show()
    }

    //fun showSpecifiedCatalogItemWithChildren(_id: ObjectId) = categoryRepository.findBy_id(_id)

    fun showSpecifiedCatalogItemWithChildren(_id: ObjectId): String {
        val tree = SimpleCategoryTree.createTree()
        val allCategories = lookupOperation()
        allCategories.forEach {
            tree.addChild(it)
        }
        return tree.show(_id.toString())
    }

    fun lookupOperation(): MutableList<CategoryDocument> {
        val lookupOperation = LookupOperation.newLookup()
                .from(MongoDBConfig.BRANDS)
                .localField("brands")
                .foreignField("_id")
                .`as`("brands")

        val aggregation = Aggregation.newAggregation(lookupOperation)
        return mongoTemplate.aggregate(aggregation, MongoDBConfig.CATEGORIES, CategoryDocument::class.java).mappedResults
    }

}