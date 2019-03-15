package com.mg.catalog.event_messaging

import com.mg.eventbus.RabbitMqConfig
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CatalogMessageConfig : RabbitMqConfig() {

/*    companion object {
        const val ROUTING_KEY_CATALOG_ALL = "catalog.#"
        const val QUEUE_CATALOG_ALL = "catalog.all"
        const val QUEUE_CATALOG_CREATED = "catalog.created"
        const val QUEUE_CATALOG_PLACED = "catalog.placed"
        const val EXCHANGE_CATALOG = "catalog"
    }

    @Bean
    fun exchangeOrder(): TopicExchange = TopicExchange(EXCHANGE_CATALOG)

    @Bean
    fun queueOrderCreated() = Queue(QUEUE_CATALOG_CREATED)

    @Bean
    fun queueOrderPlaced() = Queue(QUEUE_CATALOG_PLACED)

    @Bean
    fun queueOrderAll() = Queue(QUEUE_CATALOG_ALL)

    @Bean
    fun bindingCreated() = BindingBuilder.bind(queueOrderCreated()).to(exchangeOrder()).with(QUEUE_CATALOG_CREATED)

    @Bean
    fun bindingPlaced() = BindingBuilder.bind(queueOrderPlaced()).to(exchangeOrder()).with(QUEUE_CATALOG_PLACED)

    @Bean
    fun bindingAll() = BindingBuilder.bind(queueOrderAll()).to(exchangeOrder()).with(ROUTING_KEY_CATALOG_ALL)*/

}