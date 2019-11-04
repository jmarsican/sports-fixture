package com.javiermarsicano.sportsfixture.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface FixtureDAO {

    @Query("SELECT * FROM fixtures WHERE state NOT LIKE 'finished'")
    fun loadFixtures(): Single<List<FixtureTable>>

    @Query("SELECT * FROM fixtures WHERE state LIKE 'finished'")
    fun loadResults(): Single<List<FixtureTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(fixtures: List<FixtureTable>)
}