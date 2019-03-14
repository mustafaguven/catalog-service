package com.mg.catalog.event_messaging.publish

import com.mg.eventbus.producer.EventData

data class CatalogEventData<T>(override val topic: String,
                               override val routingKey: String,
                               override val data: T) : EventData<T>
