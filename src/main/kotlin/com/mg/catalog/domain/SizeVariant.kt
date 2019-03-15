package com.mg.catalog.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class SizeVariant(@JsonProperty("barcode") val barcode: String,
                       @JsonProperty("length") val length: String?,
                       @JsonProperty("size") val size: String?)