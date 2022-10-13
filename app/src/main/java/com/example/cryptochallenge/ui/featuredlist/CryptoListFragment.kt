package com.example.cryptochallenge.ui.featuredlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cryptochallenge.R
import com.example.cryptochallenge.databinding.FragmentCryptoListBinding
import com.example.cryptochallenge.ui.CryptoVM
import com.example.cryptochallenge.utils.InternetConnectionVerifier

class CryptoListFragment : Fragment() {

    private lateinit var binding: FragmentCryptoListBinding
    private val cryptoVM by activityViewModels<CryptoVM>()
    private val internetVerifier by lazy { InternetConnectionVerifier(requireActivity().application) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCryptoListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            cryptoVM.cryptoList.observe(viewLifecycleOwner) {
                val recycler = CryptoAdapter { cryptoCoin ->
                    internetVerifier.observe(viewLifecycleOwner) { internetAvailable ->
                        cryptoVM.getOpenOrders(cryptoCoin.book ?: "")
                        cryptoVM.getCryptoDetail(cryptoCoin.book ?: "")
                        if (internetAvailable) {
                            findNavController().navigate(R.id.cryptoDetailFragment)
                        } else {
                            cryptoVM.graphDataObject.observe(viewLifecycleOwner) {
                                if (it != null) {
                                    findNavController().navigate(R.id.cryptoDetailFragment)
                                }
                            }
                        }
                    }
                }
                recyclerView.adapter = recycler
                recycler.submitList(cryptoVM.cryptoList.value ?: arrayListOf())
            }
        }
    }
}
