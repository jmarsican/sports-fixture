package com.javiermarsicano.sportsfixture.views.models

import com.javiermarsicano.sportsfixture.data.db.FixtureTable
import java.text.SimpleDateFormat
import java.util.*

const val API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX"

data class Fixture(
        val awayTeam: Team? = null,
        val competitionStage: CompetitionStage? = null,
        val date: String? = null,
        val formatedDate: Date? = null,
        val homeTeam: Team? = null,
        val id: Int,
        val state: String? = null,
        val type: String? = null,
        val venue: Venue? = null,
        val score: Score? = null
)

enum class STATE(val value: String) {
    POS("postponed"),
    PRE("preMatch"),
    FINISHED("finished")
}

fun Fixture.toDao() = FixtureTable(
        id = this.id,
        date = this.date,
        state = this.state,
        type = this.type,
        awayTeam = this.awayTeam?.name,
        homeTeam = this.homeTeam?.name,
        venue = this.venue?.name,
        competition = this.competitionStage?.competition?.name,
        awayScore = this.score?.away,
        homeScore = this.score?.home
)

fun FixtureTable.toViewModel() = Fixture(
        id = this.id,
        date = this.date,
        state = this.state,
        type = this.type,
        awayTeam = Team(null, null, null, this.awayTeam, null),
        homeTeam = Team(null, null, null, this.homeTeam, null),
        venue = Venue(null, this.venue),
        competitionStage = CompetitionStage(Competition(null, this.competition)),
        score = Score(away = this.awayScore ?: 0, home = this.homeScore ?: 0, winner = null),
        formatedDate = SimpleDateFormat(API_DATE_FORMAT, Locale.US).parse(this.date)
)