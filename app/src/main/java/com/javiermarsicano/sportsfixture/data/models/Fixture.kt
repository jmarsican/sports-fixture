package com.javiermarsicano.sportsfixture.data.models

data class Fixture(
        val awayTeam: Team?,
        val competitionStage: CompetitionStage?,
        val date: String?,
        val homeTeam: Team?,
        val id: Int?,
        val state: String?,
        val type: String?,
        val venue: Venue?
)