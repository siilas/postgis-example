package com.github.siilas.postgis.service

import com.github.siilas.postgis.exception.LocationNotFound
import com.github.siilas.postgis.model.LocationRequest
import com.github.siilas.postgis.model.LocationResponse
import com.github.siilas.postgis.repository.LocationRepository
import org.springframework.stereotype.Service

@Service
class LocationService(
    private val locationRepository: LocationRepository
) {

    fun createLocation(request: LocationRequest): LocationResponse {
        return locationRepository.create(request.toLocation()).toResponse()
    }

    fun updateLocation(id: Long, request: LocationRequest): LocationResponse {
        return locationRepository.findById(id)
            ?.let { locationRepository.update(it.id, it).toResponse() }
            ?: throw LocationNotFound(id)
    }

    fun getLocation(): List<LocationResponse> {
        return locationRepository.find()
            .map { it.toResponse() }
    }

    fun getLocationById(id: Long): LocationResponse {
        return locationRepository.findById(id)
            ?.let { it.toResponse() }
            ?: throw LocationNotFound(id)
    }

    fun deleteLocation(id: Long) {
        return locationRepository.findById(id)
            ?.let { locationRepository.delete(it.id) }
            ?: throw LocationNotFound(id)
    }
}
