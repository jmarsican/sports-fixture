package com.javiermarsicano.sportsfixture.common.di

import android.app.Application
import com.javiermarsicano.sportsfixture.data.repository.FixtureRepository
import com.javiermarsicano.sportsfixture.data.repository.FixtureRepositoryImpl
import com.javiermarsicano.sportsfixture.data.services.SportsApiServices
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://storage.googleapis.com/cdn-og-test-api/test-task/"

@Module
class RepositoriesModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideRestApi() : Retrofit {

        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Timber.i(it) })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okhttpClient = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okhttpClient)
                .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit) : SportsApiServices {
        return retrofit.create(SportsApiServices::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(service: SportsApiServices) : FixtureRepository {
        return FixtureRepositoryImpl(service)
    }

}