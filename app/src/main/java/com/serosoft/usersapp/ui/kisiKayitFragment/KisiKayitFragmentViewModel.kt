package com.serosoft.usersapp.ui.kisiKayitFragment

import androidx.lifecycle.ViewModel
import com.serosoft.usersapp.data.repo.KisilerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KisiKayitFragmentViewModel @Inject constructor(var krepo: KisilerDaoRepository) :
    ViewModel() {

    fun Kayit(kisiAd: String, kisiTel: String) {
        krepo.KisiKayit(kisiAd, kisiTel) //room
        krepo.kisiKayitRetrofit(kisiAd, kisiTel) //retrofit

    }
}