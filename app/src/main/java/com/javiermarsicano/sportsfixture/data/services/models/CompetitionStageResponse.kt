package com.javiermarsicano.sportsfixture.data.services.models

import com.javiermarsicano.sportsfixture.views.viewmodels.CompetitionStage

data class CompetitionStageResponse(
    val competition: CompetitionResponse?
)

fun CompetitionStageResponse.toViewModel() = CompetitionStage(
        competition = this.competition?.toViewModel()
)