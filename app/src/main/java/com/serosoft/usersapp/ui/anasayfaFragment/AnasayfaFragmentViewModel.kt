package com.serosoft.usersapp.ui.anasayfaFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serosoft.usersapp.data.entity.Kisiler
import com.serosoft.usersapp.data.repo.KisilerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnasayfaFragmentViewModel @Inject constructor(var krepo: KisilerDaoRepository) : ViewModel() {
    var kisilerListesi = MutableLiveData<List<Kisiler>>()

    init {
        kisileriYukle()
        kisilerListesi = krepo.kisileriGetir()
    }

    fun ara(aramaKelimesi: String) {

        krepo.KisiAra(aramaKelimesi) //room
        krepo.kisiAraRetrofit(aramaKelimesi) //retrofit
    }

    fun sil(kisiId: Int) {

        krepo.kisiSil(kisiId) //room
        krepo.kisiSilRetrofit(kisiId) //retrofit
    }

    fun kisileriYukle() {
        krepo.tumKisileriAl() //room
        krepo.tumKisileriAlRetrofit() //retrofit
    }

}