package com.mg.catalog.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateCategoryEntity(
        @JsonProperty("parentId") val parentId: String?,
        @JsonProperty("title") val title: String,
        @JsonProperty("priority") val priority: Int,
        @JsonProperty("type") val type: String)
