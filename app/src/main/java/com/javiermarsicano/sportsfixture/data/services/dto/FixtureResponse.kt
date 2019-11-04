package com.javiermarsicano.sportsfixture.data.services.dto

import com.javiermarsicano.sportsfixture.views.models.API_DATE_FORMAT
import com.javiermarsicano.sportsfixture.views.models.Fixture
import java.text.SimpleDateFormat
import java.util.*

data class FixtureResponse(
        val awayTeam: TeamResponse?,
        val competitionStage: CompetitionStageResponse?,
        val date: String?,
        val homeTeam: TeamResponse?,
        val id: Int,
        val state: String?,
        val type: String?,
        val venue: VenueResponse?,
        val score: ScoreResponse?
)

fun FixtureResponse.toVieModel() = Fixture(
        id = this.id,
        date = this.date,
        type = this.type,
        state = this.state,
        awayTeam = this.awayTeam?.toViewModel(),
        homeTeam = this.homeTeam?.toViewModel(),
        venue = this.venue?.toViewModel(),
        competitionStage = this.competitionStage?.toViewModel(),
        score = this.score?.toViewModel(),
        formatedDate = SimpleDateFormat(API_DATE_FORMAT, Locale.US).parse(this.date)
)