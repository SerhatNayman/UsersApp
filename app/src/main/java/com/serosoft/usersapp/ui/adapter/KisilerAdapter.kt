package com.serosoft.usersapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.serosoft.usersapp.R
import com.serosoft.usersapp.data.entity.Kisiler
import com.serosoft.usersapp.databinding.CardTasarimBinding
import com.serosoft.usersapp.ui.anasayfaFragment.AnasayfaFragmentDirections
import com.serosoft.usersapp.ui.anasayfaFragment.AnasayfaFragmentViewModel
import com.serosoft.usersapp.util.gecisYap

class KisilerAdapter(
    var mContext: Context,
    var kisilerListesi: List<Kisiler>,
    var viewModel: AnasayfaFragmentViewModel
) :
    RecyclerView.Adapter<KisilerAdapter.CardTasarimTutucu>() {


    inner class CardTasarimTutucu(binding: CardTasarimBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var binding: CardTasarimBinding

        init {
            this.binding = binding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {

        val layoutInflater = LayoutInflater.from(mContext)
        val binding: CardTasarimBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.card_tasarim, parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {

        val kisi = kisilerListesi.get(position)

        val b = holder.binding

        b.kisiNesnesi = kisi

        b.textViewKisiBilgi.text = "${kisi.kisiAd} - ${kisi.kisiTel}"

        b.card.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.anasayfaToDetay(kisi = kisi)
            Navigation.gecisYap(it, gecis)
        }

        b.imageViewSil.setOnClickListener {

            Snackbar.make(it, "${kisi.kisiAd} silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("EVET") {

                    viewModel.sil(kisi.kisiId)

                }.show()
        }
    }

    override fun getItemCount(): Int {
        return kisilerListesi.size
    }
}