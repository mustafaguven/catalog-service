package com.mg.catalog.query

import com.mg.catalog.document.CategoryDocument
import com.mg.catalog.domain.SimpleCategoryTree
import com.mg.catalog.domain.response.GetCategoryResponse
import com.mg.catalog.repository.CategoryRepository
import com.mg.eventbus.inline.logger
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit


@Component
class ViewCatalog(val categoryRepository: CategoryRepository,
                  val redisTemplate: RedisTemplate<String, Any>,
                  val mongoTemplate: MongoTemplate) {

    companion object {
        val log = logger(this)
        const val CATEGORIES = "CATEGORIES"
    }

    fun showAllCatalogItemsWithChildren(retrieveCachedData: Boolean) = getCategoryTree(retrieveCachedData = retrieveCachedData)

    fun showSpecifiedCatalogItemWithChildren(_id: ObjectId, retrieveCachedData: Boolean = false) = getCategoryTree(_id.toString(), retrieveCachedData)

    private fun getCategoryTree(id: String? = "0", retrieveCachedData: Boolean = false): GetCategoryResponse {
        val data = if (!retrieveCachedData) {
            getCategoriesFromMongo(id)
        } else {
            val cached = redisTemplate.opsForValue().get(CATEGORIES) as CategoryDocument?
            cached?.let {
                log.info("retrieving from redis")
                cached
            } ?: getCategoriesFromMongo(id)
        }

        return GetCategoryResponse(retrieveCachedData, data)
    }

    private fun getCategoriesFromMongo(id: String?): CategoryDocument {
        log.warn("retrieving from db")
        val tree = SimpleCategoryTree.createTree()
        val allCategories = categoryRepository.findAll()
        allCategories.forEach {
            tree.addChild(it)
        }
        val result = tree.show(id)
        redisTemplate.opsForValue().set(CATEGORIES, result)
        redisTemplate.expire(CATEGORIES, 30, TimeUnit.MINUTES)
        return result
    }

    //fun showSpecifiedCatalogItemWithChildren(_id: ObjectId) = categoryRepository.findBy_id(_id)

/*    fun lookupOperation(): MutableList<CategoryDocument> {
        val lookupOperation = LookupOperation.newLookup()
                .from(MongoDBConfig.BRANDS)
                .localField("brands")
                .foreignField("_id")
                .`as`("brands")

        val aggregation = Aggregation.newAggregation(lookupOperation)
        return mongoTemplate.aggregate(aggregation, MongoDBConfig.CATEGORIES, CategoryDocument::class.java).mappedResults
    }*/

}