package com.mg.catalog.command

import com.fasterxml.jackson.annotation.JsonProperty
import com.mg.catalog.domain.DeleteCategoryEntity
import com.mg.eventbus.gateway.Commandable

data class DeleteCategoryItemCommand(
        @JsonProperty("entity") val deleteCategoryEntity: DeleteCategoryEntity) : Commandable(deleteCategoryEntity) {

    companion object {
        const val QUEUE_ID = Commandable.QUEUE_CLUSTER_ID.plus("DeleteCategoryItemCommand")
    }
}