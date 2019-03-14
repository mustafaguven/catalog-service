package com.mg.catalog.controller.command

import com.mg.catalog.controller.AbstractController
import com.mg.catalog.event_messaging.publish.CatalogMessageProducer
import com.mg.catalog.model.Order
import com.mg.catalog.services.ProductService
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
class CatalogCommandController(val messageProducer: CatalogMessageProducer,
                               val productService: ProductService) : AbstractController() {

    @Value("\${app.id}")
    private val instance: String? = null

    @PostMapping(value = ["/create"])
    fun create(@RequestBody order: Order?): ResponseEntity<Order> {
        messageProducer.sendCatalogCreatedMessage(order)
        return ResponseEntity.ok(order)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getCommands(): ResponseEntity<ResourceSupport> {
        val commands = ResourceSupport()
        commands.add(linkTo(methodOn(this::class.java).create(null)).withRel("create").withTitle("POST"))
        return ResponseEntity.ok(commands)
    }


}
