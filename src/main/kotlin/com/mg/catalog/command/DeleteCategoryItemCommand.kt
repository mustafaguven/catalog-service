package com.mg.catalog.command

import com.fasterxml.jackson.annotation.JsonProperty
import com.mg.catalog.domain.request.DeleteCategoryRequestBody
import com.mg.eventbus.gateway.Commandable

data class DeleteCategoryItemCommand(
        @JsonProperty(ENTITY) val requestBody: DeleteCategoryRequestBody) : Commandable(requestBody) {

    companion object {
        const val QUEUE_ID = QUEUE_COMMAND_CLUSTER_ID.plus("DeleteCategoryItemCommand")
    }
}