package com.javiermarsicano.sportsfixture.views.models

import com.javiermarsicano.sportsfixture.data.db.FixtureTable

data class Fixture(
        val awayTeam: Team?,
        val competitionStage: CompetitionStage?,
        val date: String?,
        val homeTeam: Team?,
        val id: Int,
        val state: String?,
        val type: String?,
        val venue: Venue?,
        val score: Score?
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
        score = Score(away = this.awayScore, home = this.homeScore, winner = null)
)