package com.mg.catalog.command

import com.fasterxml.jackson.annotation.JsonProperty
import com.mg.eventbus.gateway.Commandable

data class DeleteCatalogItemCommand(
        @JsonProperty("id") val id: Int?) : Commandable {

    companion object {
        const val ID = "DeleteCatalogItemCommand"
    }
}