package com.mg.catalog.event_messaging.publish

import com.mg.eventbus.producer.MessageProducer
import com.mg.catalog.event_messaging.CatalogMessageConfig
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class CatalogMessageProducer(rabbit: RabbitTemplate) : MessageProducer(rabbit) {

    fun <T> sendCatalogCreatedMessage(t: T) {
        sendMessage(CatalogEventData(CatalogMessageConfig.EXCHANGE_CATALOG,
                CatalogMessageConfig.QUEUE_CATALOG_CREATED,
                t)
        )
    }

    fun <T> sendCatalogPlacedMessage(t: T) {
        sendMessage(CatalogEventData(CatalogMessageConfig.EXCHANGE_CATALOG, CatalogMessageConfig.QUEUE_CATALOG_PLACED, t))
    }
}