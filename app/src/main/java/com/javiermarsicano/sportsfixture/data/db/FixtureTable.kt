package com.javiermarsicano.sportsfixture.data.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(
        tableName = "fixtures",
        indices = arrayOf(Index("id"))
)
data class FixtureTable (
        val date: String?,
        val homeTeam: String?,
        val awayTeam: String?,
        @PrimaryKey
        val id: Int,
        val state: String?,
        val type: String?,
        val venue: String?,
        val competition: String?
)