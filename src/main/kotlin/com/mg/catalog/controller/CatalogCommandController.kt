package com.mg.catalog.controller

import com.mg.catalog.command.CreateCategoryItemCommand
import com.mg.catalog.command.DeleteCategoryItemCommand
import com.mg.catalog.domain.request.CreateCategoryRequestBody
import com.mg.catalog.domain.request.DeleteCategoryRequestBody
import com.mg.eventbus.AbstractController
import com.mg.eventbus.gateway.CommandGateway
import com.mg.eventbus.response.BaseResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture


@RefreshScope
@RestController
@RequestMapping("/category/command")
@ResponseStatus(HttpStatus.ACCEPTED)
class CatalogCommandController(val commandGateway: CommandGateway) : AbstractController() {

    @Value("\${app.id}")
    private val instance: String? = null

    @PostMapping(value = ["/create"])
    fun createItem(@RequestBody request: CreateCategoryRequestBody?): CompletableFuture<ResponseEntity<BaseResponse>>? {
        return commandGateway.send(CreateCategoryItemCommand(request))
    }

    @DeleteMapping(value = ["/delete"])
    fun deleteItem(@RequestBody request: DeleteCategoryRequestBody): CompletableFuture<ResponseEntity<BaseResponse>> {
        return commandGateway.send(DeleteCategoryItemCommand(request))
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getCommands(): ResponseEntity<ResourceSupport> {
        val commands = ResourceSupport()
        commands.add(linkTo(methodOn(this::class.java).createItem(null)).withRel("create").withTitle(POST))
        commands.add(linkTo(methodOn(this::class.java).deleteItem(DeleteCategoryRequestBody(null))).withRel("delete").withTitle(DELETE))
        return ResponseEntity.ok(commands)
    }


}
