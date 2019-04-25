package com.mg.catalog.controller

import com.mg.catalog.domain.response.GetCategoryResponse
import com.mg.catalog.query.ViewCatalog
import com.mg.eventbus.AbstractController
import com.mg.eventbus.response.BaseResponse
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RefreshScope
@RestController
@ResponseStatus(HttpStatus.ACCEPTED)
class CatalogController(val viewCatalog: ViewCatalog) : AbstractController() {

    @GetMapping(value = ["/"])
    fun getAllCategory(@RequestHeader(required = false, name = "retrieveCachedData") retrieveCachedData: Boolean = false)
            : ResponseEntity<BaseResponse<GetCategoryResponse>> {
        val data = viewCatalog.showAllCatalogItemsWithChildren(retrieveCachedData)
        val response = BaseResponse(BaseResponse.SUCCESS, data = data)
        return ResponseEntity.ok(response)
    }

    @GetMapping(value = ["/{id}"])
    fun getCategoryById(@PathVariable("id") id: ObjectId,
                        @RequestHeader(required = false, name = "retrieveCachedData") retrieveCachedData: Boolean = false)
            : ResponseEntity<BaseResponse<GetCategoryResponse>> {
        val data = viewCatalog.showSpecifiedCatalogItemWithChildren(id, retrieveCachedData)
        val response = BaseResponse(BaseResponse.SUCCESS, data = data)
        return ResponseEntity.ok(response)
    }

    override fun createRestLinks(): List<ControllerLink> {
        return listOf(
                ControllerLink(method = methodOn(CatalogCommandController::class.java).createRestLinks()!!, rel = QUERY, methodType = GET, title="command"),
                ControllerLink(method = methodOn(this::class.java).getAllCategory(), rel = QUERY, methodType = GET, title="getAllCategory"),
                ControllerLink(method = methodOn(this::class.java).getCategoryById(ObjectId()), rel = QUERY, methodType = GET, title="getCategoryById")
        )
    }

}