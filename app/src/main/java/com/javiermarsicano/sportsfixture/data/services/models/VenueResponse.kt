package com.javiermarsicano.sportsfixture.data.services.models

import com.javiermarsicano.sportsfixture.views.viewmodels.Venue

data class VenueResponse(
    val id: Int?,
    val name: String?
)

fun VenueResponse.toViewModel() = Venue(
        id = this.id,
        name = this.name
)