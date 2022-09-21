package com.example.cryptochallenge.ui.featuredlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.cryptochallenge.repository.Retrofit
import com.example.cryptochallenge.usecase.CryptoUseCase
import com.example.cryptochallenge.repository.CryptoRepositoryImp
import com.example.cryptochallenge.databinding.FragmentCryptoListBinding
import com.example.cryptochallenge.ui.CryptoVM
import com.example.cryptochallenge.utils.ViewModelFactory

class CryptoListFragment : Fragment() {

    private lateinit var binding: FragmentCryptoListBinding
    private val cryptoVM by activityViewModels<CryptoVM> { ViewModelFactory(cryptoUseCase) }
    private lateinit var cryptoUseCase: CryptoUseCase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCryptoListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cryptoUseCase = CryptoUseCase(CryptoRepositoryImp(Retrofit.webService))
        binding.apply {
            cryptoVM.cryptoList.observe(viewLifecycleOwner) {
                recyclerView.adapter = CryptoAdapter(cryptoVM.cryptoList.value ?: arrayListOf()) {
                    //TODO consume the service to get the detail info
                    //cryptoVM.getDetailCripto()
                    //cryptoVM.getOpenOrders()
                    //findNavController().navigate(R.id.cryptoDetailFragment)
                }
            }
        }
    }
}