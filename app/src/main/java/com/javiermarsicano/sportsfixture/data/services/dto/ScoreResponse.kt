package com.javiermarsicano.sportsfixture.data.services.dto

import com.javiermarsicano.sportsfixture.views.models.Score

data class ScoreResponse(
    val away: Int?,
    val home: Int?,
    val winner: String?
)

fun ScoreResponse.toViewModel() = Score(
        away = this.away ?: 0,
        home = this.home ?: 0,
        winner = this.winner
)