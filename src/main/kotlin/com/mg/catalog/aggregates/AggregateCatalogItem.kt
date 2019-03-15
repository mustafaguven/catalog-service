package com.mg.catalog.aggregates

import com.mg.catalog.command.CreateCatalogItemCommand
import com.mg.catalog.command.DeleteCatalogItemCommand
import com.mg.eventbus.inline.logger
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class AggregateCatalogItem {

    companion object {
        val log = logger(this)
    }

    @RabbitListener(queues = [CreateCatalogItemCommand.ID])
    fun createItem(command: CreateCatalogItemCommand) {
        log.info("eklenecek olan catalog item: $command")
    }

    @RabbitListener(queues = [DeleteCatalogItemCommand.ID])
    fun createItem(command: DeleteCatalogItemCommand) {
        log.info("silinecek catalog item id: ${command.id}")
    }

}