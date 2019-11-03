package com.javiermarsicano.sportsfixture.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface FixtureDAO {

    @Query("SELECT * FROM fixtures")
    fun load(): Single<List<FixtureTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(fixtures: List<FixtureTable>)
}