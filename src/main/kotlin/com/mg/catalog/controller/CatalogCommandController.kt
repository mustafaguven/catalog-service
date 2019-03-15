package com.mg.catalog.controller

import com.mg.catalog.command.CreateCatalogItemCommand
import com.mg.catalog.command.DeleteCatalogItemCommand
import com.mg.catalog.domain.StyleVariant
import com.mg.eventbus.AbstractController
import com.mg.eventbus.gateway.CommandGateway
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
@RequestMapping("/command")
@ResponseStatus(HttpStatus.ACCEPTED)
class CatalogCommandController(val commandGateway: CommandGateway) : AbstractController() {

    @Value("\${app.id}")
    private val instance: String? = null

    @PostMapping(value = ["/create"])
    fun createItem(@RequestBody catalogItem: StyleVariant?): ResponseEntity<StyleVariant> {
        commandGateway.send(CreateCatalogItemCommand(catalogItem))
        return ResponseEntity.ok(catalogItem)
    }

    @DeleteMapping(value = ["/delete/{id}"])
    fun deleteItem(@PathVariable("id") id: Int?): ResponseEntity<Int> {
        commandGateway.send(DeleteCatalogItemCommand(id))
        return ResponseEntity.ok(id)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getCommands(): ResponseEntity<ResourceSupport> {
        val commands = ResourceSupport()
        commands.add(linkTo(methodOn(this::class.java).createItem(null)).withRel("create").withTitle("POST"))
        return ResponseEntity.ok(commands)
    }


}
