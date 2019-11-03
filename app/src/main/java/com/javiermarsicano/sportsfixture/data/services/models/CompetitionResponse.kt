package com.javiermarsicano.sportsfixture.data.services.models

import com.javiermarsicano.sportsfixture.views.viewmodels.Competition

data class CompetitionResponse(
    val id: Int?,
    val name: String?
)

fun CompetitionResponse.toViewModel() = Competition(
        id = this.id,
        name = this.name
)