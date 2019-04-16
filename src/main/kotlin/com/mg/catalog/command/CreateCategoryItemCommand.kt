package com.mg.catalog.command

import com.fasterxml.jackson.annotation.JsonProperty
import com.mg.catalog.document.CategoryDocument
import com.mg.catalog.domain.CreateCategoryEntity
import com.mg.eventbus.gateway.Commandable

data class CreateCategoryItemCommand(
        @JsonProperty("entity") val categoryEntity: CreateCategoryEntity?) : Commandable(categoryEntity) {

    companion object {
        const val QUEUE_ID =  QUEUE_CLUSTER_ID.plus("CreateCategoryItemCommand")
    }
}