package com.example.cryptochallenge.ui.featuredlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptochallenge.databinding.CryptoItemBinding
import com.example.cryptochallenge.domain.base.CryptoCoins
import com.example.cryptochallenge.utils.*
import kotlin.text.format

class CryptoAdapter(
    private var onClickItem: (CryptoCoins) -> Unit
): ListAdapter<CryptoCoins, CryptoAdapter.CryptoViewHolder>(DiffCallBack()){

    inner class CryptoViewHolder(private val itemBinding: CryptoItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        @SuppressLint("SetTextI18n")
        fun bindData(item: CryptoCoins){
            itemBinding.apply {
                val cryptoInfo = CryptoCatalog.values().find { it.book == item.book }
                imgCryptoLogo.setImageResource(
                    CryptoCatalog.values().find { it.image == cryptoInfo?.toImage() }?.toImage() ?: 0
                )
                txtCryptoName.text = cryptoInfo?.toName()?.format()?.toCaseLower()
                txtCryptoValue.text = item.book
                txtMaxPriceValue.text = "max ${item.maxPrice?.toDouble()?.formatAsCurrency()}"
                txtMinPriceValue.text = "min ${item.minPrice?.toDouble()?.formatAsCurrency()}"
                boxCryptoItem.setOnClickListener {
                    onClickItem.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder{
        val binding = CryptoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) = holder.bindData(getItem(position))
}

class DiffCallBack: DiffUtil.ItemCallback<CryptoCoins>() {
    override fun areItemsTheSame(oldItem: CryptoCoins, newItem: CryptoCoins) = oldItem.book == newItem.book
    override fun areContentsTheSame(oldItem: CryptoCoins, newItem: CryptoCoins) = oldItem == newItem
}