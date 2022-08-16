package com.serosoft.usersapp.room

import androidx.room.*
import com.serosoft.usersapp.data.entity.Kisiler

@Dao
interface KisilerDao {

    @Query("SELECT * FROM kisiler")
    suspend fun tumKisiler(): List<Kisiler> //tum kisileri getir


    @Query("select * from kisiler where kisiAd like '%' ||:aramaKelimesi || '%'")
    suspend fun kisiArama(aramaKelimesi: String): List<Kisiler>

    @Insert
    suspend fun kisiEkle(kisi: Kisiler)

    @Update
    suspend fun kisiGuncelle(kisi: Kisiler)

    @Delete
    suspend fun kisiSil(kisi:Kisiler)

}