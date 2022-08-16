package com.serosoft.usersapp.ui.kisiDetayFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.serosoft.usersapp.R
import com.serosoft.usersapp.databinding.FragmentKisiDetayBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KisiDetayFragment : Fragment() {

    private lateinit var binding: FragmentKisiDetayBinding
    private lateinit var viewModel: KisiDetayFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kisi_detay, container, false)
        binding.kisiDetayToolbarBaslik = "Kişi Detay"
        binding.kisiDetayFragment = this

        val bundle: KisiDetayFragmentArgs by navArgs()
        val gelenKisi = bundle.kisi

        binding.kisiNesnesi = gelenKisi

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: KisiDetayFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun KisiGuncelle(kisiId: Int, kisiAd: String, kisiTel: String) {

        viewModel.Guncelle(kisiId, kisiAd, kisiTel)
    }


}