package com.serosoft.usersapp.ui.kisiDetayFragment

import androidx.lifecycle.ViewModel
import com.serosoft.usersapp.data.repo.KisilerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KisiDetayFragmentViewModel @Inject constructor(var krepo: KisilerDaoRepository) :
    ViewModel() {

    fun Guncelle(kisiId: Int, kisiAd: String, kisiTel: String) {

        krepo.Guncelle(kisiId, kisiAd, kisiTel) //Room
        krepo.kisiGuncelleRetrofit(kisiId, kisiAd, kisiTel) //Retrofit
    }
}