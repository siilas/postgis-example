package com.github.siilas.postgis.handler

import com.github.siilas.postgis.model.LocationRequest
import com.github.siilas.postgis.service.LocationService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.json

@Component
class LocationHandler(
    private val locationService: LocationService
) {

    suspend fun createLocation(request: ServerRequest): ServerResponse {
        return try {
            val location = request.awaitBody(LocationRequest::class)

            ServerResponse.ok()
                .json()
                .bodyValueAndAwait(locationService.createLocation(location))
        } catch (e: Exception) {
            ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .json()
                .bodyValueAndAwait("""{"error":"Error creating location"}""")
        }
    }

    suspend fun updateLocation(request: ServerRequest): ServerResponse {
        return try {
            val id = request.pathVariable("id").toLong()
            val location = request.awaitBody(LocationRequest::class)

            ServerResponse.ok()
                .json()
                .bodyValueAndAwait(locationService.updateLocation(id, location))
        } catch (e: Exception) {
            ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .json()
                .bodyValueAndAwait("""{"error":"Error updating location"}""")
        }
    }

    suspend fun getLocation(request: ServerRequest): ServerResponse {
        return try {
            ServerResponse.ok()
                .json()
                .bodyValueAndAwait(locationService.getLocation())
        } catch (e: Exception) {
            ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .json()
                .bodyValueAndAwait("""{"error":"Error getting location"}""")
        }
    }

    suspend fun getLocationById(request: ServerRequest): ServerResponse {
        return try {
            val id = request.pathVariable("id").toLong()

            ServerResponse.ok()
                .json()
                .bodyValueAndAwait(locationService.getLocationById(id))
        } catch (e: Exception) {
            ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .json()
                .bodyValueAndAwait("""{"error":"Error getting location by id"}""")
        }
    }

    suspend fun deleteLocation(request: ServerRequest): ServerResponse {
        return try {
            val id = request.pathVariable("id").toLong()

            ServerResponse.ok()
                .json()
                .bodyValueAndAwait(locationService.deleteLocation(id))
        } catch (e: Exception) {
            ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .json()
                .bodyValueAndAwait("""{"error":"Error deleting location"}""")
        }
    }
}
