package com.javiermarsicano.sportsfixture.data.services.models

import com.javiermarsicano.sportsfixture.views.viewmodels.Fixture

data class FixtureResponse(
        val awayTeam: TeamResponse?,
        val competitionStage: CompetitionStageResponse?,
        val date: String?,
        val homeTeam: TeamResponse?,
        val id: Int,
        val state: String?,
        val type: String?,
        val venue: VenueResponse?
)

fun FixtureResponse.toVieModel() = Fixture(
        id = this.id,
        date = this.date,
        type = this.type,
        state = this.state,
        awayTeam = this.awayTeam?.toViewModel(),
        homeTeam = this.homeTeam?.toViewModel(),
        venue = this.venue?.toViewModel(),
        competitionStage = this.competitionStage?.toViewModel()
)