package com.javiermarsicano.sportsfixture.data.services.dto

import com.javiermarsicano.sportsfixture.views.models.Team

data class TeamResponse(
    val abbr: String?,
    val alias: String?,
    val id: Int?,
    val name: String?,
    val shortName: String?
)

fun TeamResponse.toViewModel() = Team(
        abbr = this.abbr,
        alias = this.alias,
        id = this.id,
        name = this.name,
        shortName = this.shortName
)