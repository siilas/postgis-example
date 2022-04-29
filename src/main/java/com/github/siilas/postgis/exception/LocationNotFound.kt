package com.github.siilas.postgis.exception

class LocationNotFound(id: Long) : RuntimeException("Location with id $id not found")