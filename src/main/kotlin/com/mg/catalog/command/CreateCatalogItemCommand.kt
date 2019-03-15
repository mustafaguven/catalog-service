package com.mg.catalog.command

import com.fasterxml.jackson.annotation.JsonProperty
import com.mg.catalog.domain.StyleVariant
import com.mg.eventbus.gateway.Commandable

data class CreateCatalogItemCommand(
        @JsonProperty("styleVariant") val styleVariant: StyleVariant?) : Commandable {

    companion object {
        const val ID = "CreateCatalogItemCommand"
    }
}