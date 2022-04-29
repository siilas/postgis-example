package com.github.siilas.postgis.config

import com.github.siilas.postgis.handler.LocationHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ApiConfig(
    private val locationHandler: LocationHandler
) {

    @Bean
    fun route() = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            POST("/location", locationHandler::createLocation)
            PUT("/location/{id}", locationHandler::updateLocation)
            GET("/location", locationHandler::getLocation)
            GET("/location/{id}", locationHandler::getLocationById)
            DELETE("/location/{id}", locationHandler::deleteLocation)
        }
    }
}
