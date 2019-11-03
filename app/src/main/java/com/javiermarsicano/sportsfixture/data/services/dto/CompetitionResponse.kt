package com.javiermarsicano.sportsfixture.data.services.dto

import com.javiermarsicano.sportsfixture.views.models.Competition

data class CompetitionResponse(
    val id: Int?,
    val name: String?
)

fun CompetitionResponse.toViewModel() = Competition(
        id = this.id,
        name = this.name
)