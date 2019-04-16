package com.mg.catalog.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.mg.eventbus.gateway.CommandEntity
import org.bson.types.ObjectId

data class DeleteCategoryEntity(@JsonProperty("id") val id: ObjectId?)
