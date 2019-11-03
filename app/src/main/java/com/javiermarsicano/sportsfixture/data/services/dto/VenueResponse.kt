package com.javiermarsicano.sportsfixture.data.services.dto

import com.javiermarsicano.sportsfixture.views.models.Venue

data class VenueResponse(
    val id: Int?,
    val name: String?
)

fun VenueResponse.toViewModel() = Venue(
        id = this.id,
        name = this.name
)