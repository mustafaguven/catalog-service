package com.mg.catalog.controller

import com.mg.catalog.event_messaging.publish.CatalogMessageProducer
import com.mg.catalog.services.ProductService
import com.mg.eventbus.AbstractController
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RefreshScope
@RestController
@ResponseStatus(HttpStatus.ACCEPTED)
class CatalogController(val messageProducer: CatalogMessageProducer,
                        val productService: ProductService) : AbstractController() {

    @Value("\${app.id}")
    private val instance: String? = null

    @GetMapping(value = ["/id"])
    fun instanceId(): ResponseEntity<String> {
        val id = "{ \"catalog_service_running_instance_id\": \"$instance\"}"
        return ResponseEntity.ok(id)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getLinks(): ResponseEntity<ResourceSupport> {
        val commands = ResourceSupport()
        commands.add(linkTo(methodOn(CatalogCommandController::class.java).getCommands()).withRel("command").withTitle("GET"))
        commands.add(linkTo(methodOn(this::class.java).instanceId()).withRel("instanceId").withType("GET"))
        return ResponseEntity.ok(commands)
    }

}