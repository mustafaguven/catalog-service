package com.mg.catalog.aggregates

import com.mg.catalog.command.CreateCategoryItemCommand
import com.mg.catalog.command.DeleteCategoryItemCommand
import com.mg.catalog.document.CategoryDocument
import com.mg.catalog.repository.CategoryRepository
import com.mg.eventbus.gateway.CommandGateway
import com.mg.eventbus.inline.logger
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class AggregateCategory(val categoryRepository: CategoryRepository,
                        val commandGateway: CommandGateway) {

    companion object {
        val log = logger(this)
    }

    @RabbitListener(queues = [CreateCategoryItemCommand.QUEUE_ID])
    fun on(command: CreateCategoryItemCommand) = commandGateway.onHandle(command) {
        val doc = CategoryDocument(
                parentId = command.requestBody?.parentId,
                title = command.requestBody?.title,
                priority = command.requestBody?.priority,
                type = command.requestBody?.type
        )
        categoryRepository.save(doc)
        //commandGateway.publishEvent()
    }

    @RabbitListener(queues = [DeleteCategoryItemCommand.QUEUE_ID])
    fun on(command: DeleteCategoryItemCommand) = commandGateway.onHandle(command) {
        val categoryDocument = categoryRepository.findBy_id(command.requestBody.id)
        if (categoryDocument != null) {
            categoryRepository.deleteById(command.requestBody.id.toString())
            "${command.requestBody.id} deleted successfully"
        } else {
            Exception("${command.requestBody.id} not found")
        }
    }

}