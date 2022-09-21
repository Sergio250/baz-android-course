package com.example.cryptochallenge.ui.featureddetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.cryptochallenge.repository.Retrofit
import com.example.cryptochallenge.usecase.CryptoUseCase
import com.example.cryptochallenge.repository.CryptoRepositoryImp
import com.example.cryptochallenge.databinding.FragmentCryptoDetailBinding
import com.example.cryptochallenge.ui.CryptoVM
import com.example.cryptochallenge.utils.ViewModelFactory

class CryptoDetailFragment : Fragment() {
    private lateinit var binding: FragmentCryptoDetailBinding
    private val cryptoVM by activityViewModels<CryptoVM> { ViewModelFactory(cryptoUseCase) }
    private lateinit var cryptoUseCase: CryptoUseCase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCryptoDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cryptoUseCase = CryptoUseCase(CryptoRepositoryImp(Retrofit.webService))
        binding.apply {

        }
    }



}