package com.github.siilas.postgis.model

data class Location(
    val id: Long?
) {

    fun toResponse(): LocationResponse {

    }
}
