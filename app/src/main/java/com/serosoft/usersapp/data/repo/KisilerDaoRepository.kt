package com.serosoft.usersapp.data.repo

import androidx.lifecycle.MutableLiveData
import com.serosoft.usersapp.data.entity.CRUDCevap
import com.serosoft.usersapp.data.entity.Kisiler
import com.serosoft.usersapp.data.entity.KisilerCevap
import com.serosoft.usersapp.room.KisilerDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KisilerDaoRepository(
    var kdao: KisilerDao,
    var kdaoo: com.serosoft.usersapp.retrofit.KisilerDao
) {
    var kisilerListesi: MutableLiveData<List<Kisiler>>

    init {
        kisilerListesi = MutableLiveData()
    }


    fun KisiKayit(kisiAd: String, kisiTel: String) {

        val job = CoroutineScope(Dispatchers.Main).launch {
            val yeniKisi = Kisiler(0, kisiAd, kisiTel)
            kdao.kisiEkle(yeniKisi)
        }

    }

    fun Guncelle(kisiId: Int, kisiAd: String, kisiTel: String) {

        val job = CoroutineScope(Dispatchers.Main).launch {
            val guncellenenKisi = Kisiler(kisiId, kisiAd, kisiTel)
            kdao.kisiGuncelle(guncellenenKisi)
        }


    }

    fun KisiAra(aramaKelimesi: String) {

        val job = CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value = kdao.kisiArama(aramaKelimesi)
        }

    }

    fun kisiSil(kisiId: Int) {

        val job = CoroutineScope(Dispatchers.Main).launch {
            val silinenKisi = Kisiler(kisiId, "", "")
            kdao.kisiSil(silinenKisi)
            tumKisileriAl()
        }

    }

    fun tumKisileriAl() {

        val job = CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value = kdao.tumKisiler()

        }
    }

    fun kisileriGetir(): MutableLiveData<List<Kisiler>> {
        return kisilerListesi

    }

    fun tumKisileriAlRetrofit() {
        kdaoo.tumKisiler().enqueue(object : Callback<KisilerCevap> {
            override fun onResponse(call: Call<KisilerCevap>, response: Response<KisilerCevap>) {
                val liste = response.body()!!.kisiler
                kisilerListesi.value = liste
            }

            override fun onFailure(call: Call<KisilerCevap>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun kisiAraRetrofit(aramaKelimesi: String) {
        kdaoo.kisiAra(aramaKelimesi).enqueue(object : Callback<KisilerCevap> {
            override fun onResponse(call: Call<KisilerCevap>, response: Response<KisilerCevap>) {
                val liste = response.body()!!.kisiler
                kisilerListesi.value = liste
            }

            override fun onFailure(call: Call<KisilerCevap>, t: Throwable) {

            }
        })
    }

    fun kisiSilRetrofit(kisi_id: Int) {
        kdaoo.kisiSil(kisi_id).enqueue(object : Callback<CRUDCevap> {
            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
                val basari = response.body()!!.success
                val mesaj = response.body()!!.message

                tumKisileriAlRetrofit()
            }

            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {

            }
        })
    }

    fun kisiKayitRetrofit(kisi_ad: String, kisi_tel: String) {
        kdaoo.kisiEkle(kisi_ad, kisi_tel).enqueue(object : Callback<CRUDCevap> {
            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
                val basari = response.body()!!.success
                val mesaj = response.body()!!.success
            }

            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {

            }
        })
    }

    fun kisiGuncelleRetrofit(kisi_id: Int, kisi_ad: String, kisi_tel: String) {
        kdaoo.kisiGuncelle(kisi_id, kisi_ad, kisi_tel).enqueue(object : Callback<CRUDCevap> {
            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {

                val basari = response.body()!!.success
                val mesaj = response.body()!!.success
            }

            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {

            }
        })

    }
}