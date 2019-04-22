package com.mg.catalog.event

import com.fasterxml.jackson.annotation.JsonProperty
import com.mg.eventbus.gateway.Commandable
import com.mg.eventbus.gateway.Eventable

data class CategoryCreatedEvent(@JsonProperty(Commandable.ENTITY) val categoryId: String?) : Eventable(categoryId) {

    companion object {
        const val QUEUE_ID = QUEUE_EVENT_CLUSTER_ID.plus("CategoryCreatedEvent")
    }
}