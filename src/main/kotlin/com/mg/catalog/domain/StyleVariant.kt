package com.mg.catalog.domain

import com.fasterxml.jackson.annotation.JsonProperty


data class StyleVariant(@JsonProperty("name") val name: String,
                        @JsonProperty("base") val base: String,
                        @JsonProperty("sku") val sku: String,
                        @JsonProperty("description") val description: String,
                        @JsonProperty("image") val image: String,
                        @JsonProperty("seasonCode") val seasonCode: String,
                        @JsonProperty("sizeVariants") val sizeVariants: Array<SizeVariant>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StyleVariant

        if (name != other.name) return false
        if (base != other.base) return false
        if (sku != other.sku) return false
        if (description != other.description) return false
        if (image != other.image) return false
        if (seasonCode != other.seasonCode) return false
        if (!sizeVariants.contentEquals(other.sizeVariants)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + base.hashCode()
        result = 31 * result + sku.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + seasonCode.hashCode()
        result = 31 * result + sizeVariants.contentHashCode()
        return result
    }
}