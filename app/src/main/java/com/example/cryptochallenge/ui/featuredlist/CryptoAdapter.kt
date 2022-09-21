package com.example.cryptochallenge.ui.featuredlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptochallenge.utils.CryptoImageCatalog
import com.example.cryptochallenge.domain.base.CryptoCoins
import com.example.cryptochallenge.databinding.CryptoItemBinding
import com.example.cryptochallenge.utils.CryptoNameCatalog
import com.example.cryptochallenge.utils.formatAsCurrency

class CryptoAdapter(
    private var data: ArrayList<CryptoCoins>,
    private var onClickItem: (CryptoCoins) -> Unit
) : RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CryptoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val itemBinding: CryptoItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(item: CryptoCoins) {
            itemBinding.apply {
                val name = CryptoNameCatalog.values().find { it.book == item.book }?.toName()
                imgCryptoLogo.setImageResource(
                    CryptoImageCatalog.values().find { it.name == name }?.toImage() ?: 0
                )
                txtCryptoName.text = name
                txtCryptoValue.text = item.book
                txtMaxPriceValue.text = "max ${item.maxPrice?.toDouble()?.formatAsCurrency()}"
                txtMinPriceValue.text = "min ${item.minPrice?.toDouble()?.formatAsCurrency()}"
                onClickItem.invoke(item)
            }
        }
    }

}