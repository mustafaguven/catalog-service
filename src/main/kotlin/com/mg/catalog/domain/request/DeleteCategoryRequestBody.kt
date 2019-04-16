package com.mg.catalog.domain.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId

data class DeleteCategoryRequestBody(@JsonProperty("id") val id: ObjectId?)
