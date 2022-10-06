package com.example.cryptochallenge.ui.featuredhomepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cryptochallenge.R
import com.example.cryptochallenge.databinding.FragmentCryptoFrontPageBinding
import com.example.cryptochallenge.ui.CryptoVM
import com.example.cryptochallenge.utils.InternetConnectionVerifier

class CryptoFrontPageFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentCryptoFrontPageBinding
    private val cryptoVM by activityViewModels<CryptoVM>()
    private val internetVerifier by lazy { InternetConnectionVerifier(requireActivity().application) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCryptoFrontPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply { onClickListener = this@CryptoFrontPageFragment }
        cryptoVM.getAllCryptoCoins()
    }

    override fun onClick(view: View) {
        binding.apply {
            when (view.id) {
                btnStart.id -> {
                    internetVerifier.observe(viewLifecycleOwner) { internetAvailable ->
                        cryptoVM.cryptoList.observe(viewLifecycleOwner) { dataFromDatabase ->
                            if (internetAvailable) {
                                findNavController().navigate(R.id.cryptoListFragment)
                            } else {
                                if (!dataFromDatabase.isNullOrEmpty()) {
                                    findNavController().navigate(R.id.cryptoListFragment)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
