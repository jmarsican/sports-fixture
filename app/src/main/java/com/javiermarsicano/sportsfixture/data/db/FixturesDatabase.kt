package com.javiermarsicano.sportsfixture.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(FixtureTable::class), version = 1, exportSchema = false)
abstract class FixturesDatabase: RoomDatabase() {
    abstract fun fixtureDao(): FixtureDAO
}