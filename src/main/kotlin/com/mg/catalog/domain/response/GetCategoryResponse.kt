package com.mg.catalog.domain.response

import com.fasterxml.jackson.annotation.JsonProperty

data class GetCategoryResponse(
        @JsonProperty("isCached") val isCached: Boolean,
        @JsonProperty("categoryTree") val categoryTree: String)
