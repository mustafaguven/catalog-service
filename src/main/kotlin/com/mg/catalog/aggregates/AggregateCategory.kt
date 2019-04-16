package com.mg.catalog.aggregates

import com.mg.catalog.command.CreateCategoryItemCommand
import com.mg.catalog.command.DeleteCategoryItemCommand
import com.mg.catalog.document.CategoryDocument
import com.mg.catalog.repository.CategoryRepository
import com.mg.eventbus.gateway.CommandGateway
import com.mg.eventbus.inline.logger
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import java.lang.Exception
import java.lang.RuntimeException

@Service
class AggregateCategory(val categoryRepository: CategoryRepository,
                        val commandGateway: CommandGateway) {

    companion object {
        val log = logger(this)
    }

    @RabbitListener(queues = [CreateCategoryItemCommand.QUEUE_ID])
    fun on(command: CreateCategoryItemCommand) = commandGateway.onHandle(command) {
        val doc = CategoryDocument(
                parentId = command.categoryEntity?.parentId,
                title = command.categoryEntity?.title,
                priority = command.categoryEntity?.priority,
                type = command.categoryEntity?.type
        )
        categoryRepository.save(doc)
    }

    @RabbitListener(queues = [DeleteCategoryItemCommand.QUEUE_ID])
    fun on(command: DeleteCategoryItemCommand) = commandGateway.onHandle(command) {
        val categoryDocument = categoryRepository.findBy_id(command.deleteCategoryEntity.id)
        if (categoryDocument != null) {
            categoryRepository.deleteById(command.deleteCategoryEntity.id.toString())
            "${command.deleteCategoryEntity.id} deleted successfully"
        } else {
            Exception("${command.deleteCategoryEntity.id} not found")
        }
    }

}