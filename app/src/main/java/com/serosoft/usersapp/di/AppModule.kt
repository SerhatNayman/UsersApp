package com.serosoft.usersapp.di

import android.content.Context
import androidx.room.Room
import com.serosoft.usersapp.data.repo.KisilerDaoRepository
import com.serosoft.usersapp.retrofit.ApiUtils
import com.serosoft.usersapp.room.KisilerDao
import com.serosoft.usersapp.room.Veritabani
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideKisilerDaoRepository(
        kdao: KisilerDao,
        kdaoo: com.serosoft.usersapp.retrofit.KisilerDao
    ): KisilerDaoRepository {
        return KisilerDaoRepository(kdao, kdaoo)
    }

    @Provides
    @Singleton
    fun provideKisilerDao(@ApplicationContext context: Context): KisilerDao {

        val vt = Room.databaseBuilder(context, Veritabani::class.java, "users.sqlite")
            .createFromAsset("users.sqlite").build()
        return vt.getKisilerDao()
    }

    //Retrofit
    @Provides
    @Singleton
    fun provideKisilerDaoRetrofit(): com.serosoft.usersapp.retrofit.KisilerDao {

        return ApiUtils.getKisilerDao()
    }
}