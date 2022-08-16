package com.serosoft.usersapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "kisiler")
data class Kisiler(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("kisi_id")
    @ColumnInfo("kisiId") @NotNull var kisiId: Int,
    @SerializedName("kisi_ad")
    @ColumnInfo("kisiAd") @NotNull var kisiAd: String,
    @SerializedName("kisi_tel")
    @ColumnInfo("kisiTel") @NotNull var kisiTel: String
) : Serializable {
}