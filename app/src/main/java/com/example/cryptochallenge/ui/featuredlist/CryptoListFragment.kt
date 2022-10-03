package com.example.cryptochallenge.ui.featuredlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cryptochallenge.R
import com.example.cryptochallenge.databinding.FragmentCryptoListBinding
import com.example.cryptochallenge.ui.CryptoVM

class CryptoListFragment : Fragment() {

    private lateinit var binding: FragmentCryptoListBinding
    private val cryptoVM by activityViewModels<CryptoVM>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCryptoListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            cryptoVM.cryptoList.observe(viewLifecycleOwner) {
                val recycler = CryptoAdapter { cryptoCoin ->
                    cryptoVM.isInternetConnectionAvailable.observe(viewLifecycleOwner){
                        if(it) {
                            cryptoVM.getOpenOrders(cryptoCoin.book ?: "")
                            cryptoVM.getCryptoDetail(cryptoCoin.book ?: "")
                            findNavController().navigate(R.id.cryptoDetailFragment)
                        }
                    }
                }
                recyclerView.adapter = recycler
                recycler.submitList(cryptoVM.cryptoList.value ?: arrayListOf())
            }
        }
    }
}


