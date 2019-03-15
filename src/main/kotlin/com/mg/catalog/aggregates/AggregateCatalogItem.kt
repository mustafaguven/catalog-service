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
        const val CreateStyleVariantItemCommand = "CreateCatalogItemCommand"
        const val DeleteItemCommand = "DeleteCatalogItemCommand"
    }

    @RabbitListener(queues = [CreateStyleVariantItemCommand])
    fun createItem(item: CreateCatalogItemCommand) {
        log.info("eklenecek olan catalog item: $item")
    }


    @RabbitListener(queues = [DeleteItemCommand])
    fun createItem(item: DeleteCatalogItemCommand) {
        log.info("silinecek catalog item: ${item.id}")
    }


}