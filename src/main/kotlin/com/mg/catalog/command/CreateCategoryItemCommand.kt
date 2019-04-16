package com.mg.catalog.command

import com.fasterxml.jackson.annotation.JsonProperty
import com.mg.catalog.domain.request.CreateCategoryRequestBody
import com.mg.eventbus.gateway.Commandable

data class CreateCategoryItemCommand(
        @JsonProperty(ENTITY) val requestBody: CreateCategoryRequestBody?) : Commandable(requestBody) {

    companion object {
        const val QUEUE_ID = QUEUE_COMMAND_CLUSTER_ID.plus("CreateCategoryItemCommand")
    }
}