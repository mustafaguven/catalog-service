package com.mg.catalog.aggregates

import com.mg.catalog.command.CreateCategoryItemCommand
import com.mg.catalog.command.DeleteCategoryItemCommand
import com.mg.catalog.document.CategoryDocument
import com.mg.catalog.event.CategoryCreatedEvent
import com.mg.catalog.event.CategoryDeletedEvent
import com.mg.catalog.repository.CategoryRepository
import com.mg.eventbus.gateway.EveCom
import com.mg.eventbus.inline.logger
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class AggregateCategory(val categoryRepository: CategoryRepository,
                        val eveCom: EveCom) {

    companion object {
        val log = logger(this)
    }

    @RabbitListener(queues = [CreateCategoryItemCommand.QUEUE_ID])
    fun on(command: CreateCategoryItemCommand) = eveCom.onHandle(command) {
        val doc = CategoryDocument(
                parentId = command.requestBody?.parentId,
                title = command.requestBody?.title,
                priority = command.requestBody?.priority,
                type = command.requestBody?.type
        )
       val category = categoryRepository.save(doc)
        eveCom.publishEvent(CategoryCreatedEvent(category._id))
        category
    }

    @RabbitListener(queues = [DeleteCategoryItemCommand.QUEUE_ID])
    fun on(command: DeleteCategoryItemCommand) = eveCom.onHandle(command) {
        val id = command.requestBody.id
        val categoryDocument = categoryRepository.findBy_id(id)
        if (categoryDocument != null) {
            categoryRepository.deleteById(id.toString())
            eveCom.publishEvent(CategoryDeletedEvent(id.toString()))
            "${command.requestBody.id} deleted successfully"
        } else {
            Exception("${command.requestBody.id} not found")
        }
    }
}