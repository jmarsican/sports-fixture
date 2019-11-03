package com.javiermarsicano.sportsfixture.data.services.dto

import com.javiermarsicano.sportsfixture.views.models.CompetitionStage

data class CompetitionStageResponse(
    val competition: CompetitionResponse?
)

fun CompetitionStageResponse.toViewModel() = CompetitionStage(
        competition = this.competition?.toViewModel()
)