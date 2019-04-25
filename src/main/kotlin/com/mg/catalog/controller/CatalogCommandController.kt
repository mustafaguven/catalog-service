package com.mg.catalog.controller

import com.mg.catalog.command.CreateCategoryItemCommand
import com.mg.catalog.command.DeleteCategoryItemCommand
import com.mg.catalog.domain.request.CreateCategoryRequestBody
import com.mg.catalog.domain.request.DeleteCategoryRequestBody
import com.mg.eventbus.AbstractController
import com.mg.eventbus.gateway.EveCom
import com.mg.eventbus.response.BaseResponse
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture


@RefreshScope
@RestController
@RequestMapping("/category/command")
@ResponseStatus(HttpStatus.ACCEPTED)
class CatalogCommandController(val eveCom: EveCom) : AbstractController() {

    @PostMapping(value = ["/create"])
    fun create(@RequestBody request: CreateCategoryRequestBody?): CompletableFuture<ResponseEntity<BaseResponse<Any>>>? {
        return eveCom.sendCommand(CreateCategoryItemCommand(request))
    }

    @DeleteMapping(value = ["/delete"])
    fun delete(@RequestBody request: DeleteCategoryRequestBody): CompletableFuture<ResponseEntity<BaseResponse<Any>>> {
        return eveCom.sendCommand(DeleteCategoryItemCommand(request))
    }

    override fun createRestLinks(): List<ControllerLink>? {
        return listOf(
                ControllerLink(method = methodOn(this::class.java).create(null)!!, rel = COMMAND, methodType = POST, title = "creates category"),
                ControllerLink(method = methodOn(this::class.java).delete(DeleteCategoryRequestBody(null)), rel = COMMAND, methodType = DELETE, title = "deletes category")
        )
    }
}
