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

    @Value("\${app.id}")
    private val instance: String? = null

    @GetMapping(value = ["/id"])
    fun instanceId(): ResponseEntity<String> {
        val id = "{ \"catalog_service_running_instance_id\": \"$instance\"}"
        return ResponseEntity.ok(id)
    }

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

    @GetMapping(value = ["/rest"])
    @ResponseStatus(HttpStatus.OK)
    fun getLinks(): ResponseEntity<ResourceSupport> {
        val commands = ResourceSupport()
        commands.add(linkTo(methodOn(CatalogCommandController::class.java).getCommands()).withRel("command").withTitle("GET"))
        commands.add(linkTo(methodOn(this::class.java).instanceId()).withRel("instanceId").withType("GET"))
        return ResponseEntity.ok(commands)
    }

}