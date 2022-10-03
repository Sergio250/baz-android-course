package com.example.cryptochallenge.ui.featureddetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.cryptochallenge.R
import com.example.cryptochallenge.databinding.FragmentCryptoDetailBinding
import com.example.cryptochallenge.domain.base.CryptoGraph
import com.example.cryptochallenge.ui.CryptoVM
import com.example.cryptochallenge.utils.CryptoCatalog
import com.example.cryptochallenge.utils.format
import com.example.cryptochallenge.utils.formatAsCurrency
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class CryptoDetailFragment : Fragment() {
    private lateinit var binding: FragmentCryptoDetailBinding
    private val cryptoVM by activityViewModels<CryptoVM>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCryptoDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            cryptoVM.apply {
                cryptoDetail.observe(viewLifecycleOwner) {
                    txtHighPriceData.text = it.high?.toDouble()?.formatAsCurrency()
                    txtLowPriceData.text = it.low?.toDouble()?.formatAsCurrency()
                    txtLastPriceData.text = it.last?.toDouble()?.formatAsCurrency()
                }
                graphDataObject.observe(viewLifecycleOwner) {
                    txtCryptoNameHeader.text = CryptoCatalog.values().find { cryptoInfo ->
                        cryptoInfo.book == it.book
                    }?.toName()?.format()
                    drawLinesDataSet(setGraphLineConfiguration(it), it.xValues ?: arrayListOf())
                }
            }
        }
    }

    private fun setGraphLineConfiguration(values: CryptoGraph): LineDataSet {
        val graphBidsAsksDataSet = LineDataSet(values.yValues, getString(R.string.txtDescriptionLabel))
        graphBidsAsksDataSet.color = ContextCompat.getColor(requireContext(), R.color.black)
        return graphBidsAsksDataSet
    }

    private fun drawLinesDataSet(lines: LineDataSet, xValues: ArrayList<String>){
        binding.chart.data = LineData(xValues, lines)
        binding.chart.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.cyan))
    }
}