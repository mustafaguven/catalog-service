package com.mg.catalog.controller

import com.mg.catalog.command.CreateCatalogItemCommand
import com.mg.catalog.domain.SizeVariant
import com.mg.catalog.domain.StyleVariant
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
@RequestMapping("/command")
@ResponseStatus(HttpStatus.ACCEPTED)
class CatalogCommandController(val commandGateway: CommandGateway) : AbstractController() {

    @Value("\${app.id}")
    private val instance: String? = null

    @PostMapping(value = ["/create"])
    fun createItem(@RequestBody catalogItem: StyleVariant?): CompletableFuture<ResponseEntity<BaseResponse>>? {
        return commandGateway.send(CreateCatalogItemCommand(catalogItem))
    }

    @DeleteMapping(value = ["/delete/{id}"])
    fun deleteItem(@PathVariable("id") id: Int?): ResponseEntity<BaseResponse> {
        //commandGateway.send(DeleteCatalogItemCommand(id))
        val response = BaseResponse()
        response.status = 1

        val size = arrayOf(SizeVariant( "123", length = "3", size = "21"))
        val styleVariant = StyleVariant(name = "abc",
                base = "aa",
                sku = "123",
                description = "desc",
                image = "aaaa",
                seasonCode = "asdasd",
                sizeVariants =  size)
        response.data = styleVariant
        return ResponseEntity.ok(response)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getCommands(): ResponseEntity<ResourceSupport> {
        val commands = ResourceSupport()
        commands.add(linkTo(methodOn(this::class.java).createItem(null)).withRel("create").withTitle("POST"))
        return ResponseEntity.ok(commands)
    }


}
