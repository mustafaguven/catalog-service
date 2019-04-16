package com.mg.catalog.domain.request

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateCategoryRequestBody(
        @JsonProperty("parentId") val parentId: String?,
        @JsonProperty("title") val title: String,
        @JsonProperty("priority") val priority: Int,
        @JsonProperty("type") val type: String)
