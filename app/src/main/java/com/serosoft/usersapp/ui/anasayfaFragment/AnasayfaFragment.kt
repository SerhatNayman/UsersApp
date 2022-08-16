package com.serosoft.usersapp.ui.anasayfaFragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.serosoft.usersapp.R
import com.serosoft.usersapp.databinding.FragmentAnasayfaBinding
import com.serosoft.usersapp.ui.adapter.KisilerAdapter
import com.serosoft.usersapp.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AnasayfaFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false)

        binding.anasayfaFragment = this

        binding.anasayfaToolbarBaslik = "Kişiler"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarAnasayfa) // menu eklendigi icin

        viewModel.kisilerListesi.observe(viewLifecycleOwner) {

            val adapter = KisilerAdapter(requireContext(), it, viewModel)
            binding.kisilerAdapter = adapter
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menuInflater.inflate(R.menu.toolbar_menu, menu)

                val item = menu.findItem(R.id.action_ara)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@AnasayfaFragment)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnasayfaFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        //arama kelimesi girdikce calisir

        viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        //harf girdikce calisir
        viewModel.ara(newText)
        return true
    }


    override fun onResume() {
        super.onResume()
        viewModel.kisileriYukle()
    }

    fun fubTikla(it: View) {
        Navigation.gecisYap(it,R.id.anasayfaToKayit)

    }

}