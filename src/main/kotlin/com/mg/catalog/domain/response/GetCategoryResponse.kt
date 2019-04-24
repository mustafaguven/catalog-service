package com.mg.catalog.domain.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.mg.catalog.document.CategoryDocument

data class GetCategoryResponse(
        @JsonProperty("isCached") val isCached: Boolean,
        @JsonProperty("categoryTree") var categoryTree: CategoryDocument,
        @JsonProperty("remainingSeconds") var remainingSeconds: Long)
