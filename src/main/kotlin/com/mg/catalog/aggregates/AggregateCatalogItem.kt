package com.mg.catalog.aggregates

import com.mg.catalog.command.CreateCatalogItemCommand
import com.mg.catalog.command.DeleteCatalogItemCommand
import com.mg.catalog.domain.SizeVariant
import com.mg.catalog.domain.StyleVariant
import com.mg.catalog.repository.CategoryRepository
import com.mg.eventbus.gateway.CommandGateway
import com.mg.eventbus.inline.logger
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import java.util.*

@Service
class AggregateCatalogItem(val categoryRepository: CategoryRepository,
                           val commandGateway: CommandGateway) {

    companion object {
        val log = logger(this)
    }

    @RabbitListener(queues = [CreateCatalogItemCommand.ID])
    fun on(command: CreateCatalogItemCommand) = commandGateway.onHandle(command) {
        val size = arrayOf(SizeVariant( "123", length = "3", size = "21"))
        StyleVariant(name = "abc",
                base = "aa",
                sku = "123",
                description = "desc",
                image = "aaaa",
                seasonCode = "asdasd",
                sizeVariants =  size)

    }

    @RabbitListener(queues = [DeleteCatalogItemCommand.ID])
    fun on(command: DeleteCatalogItemCommand) {
        log.info("silinecek catalog item id: ${command.id}")
    }

}