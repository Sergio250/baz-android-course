package com.example.cryptochallenge.ui.featuredhomepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cryptochallenge.R
import com.example.cryptochallenge.databinding.FragmentCryptoFrontPageBinding
import com.example.cryptochallenge.ui.CryptoVM

class CryptoFrontPageFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentCryptoFrontPageBinding
    private val cryptoVM by activityViewModels<CryptoVM>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCryptoFrontPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            onClickListener = this@CryptoFrontPageFragment
        }
        cryptoVM.getAllCryptoCoins()
    }

    override fun onClick(view: View) {
        binding.apply {
            when (view.id) {
                btnStart.id ->{
                    findNavController().navigate(R.id.cryptoAccountCreationFragment)
                }
            }
        }
    }
}