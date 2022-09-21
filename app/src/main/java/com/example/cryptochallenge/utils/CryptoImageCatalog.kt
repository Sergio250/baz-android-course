package com.example.cryptochallenge.utils

import com.example.cryptochallenge.R

enum class CryptoImageCatalog(val book: Int) {
    Aave (R.drawable.ic_aave_crypto_coin),
    Algorand (R.drawable.ic_algorand_algo_crypto_coin),
    Apecoin (R.drawable.ic_apecoin_ape_crypto_coin),
    Axie_Iinfinity (R.drawable.ic_axie_infinity_axs_crypto_coin),
    Balancer (R.drawable.ic_balancer_bal_crypto_coin),
    Basic_Attention_Token (R.drawable.ic_basic_attention_token_bat_crypto_coin),
    Bitcoin (R.drawable.ic_bitcoin_btc_crypto_coin),
    Bitcoin_Cash (R.drawable.ic_bitcoin_cash_bch_crypto_coin),
    Cardano (R.drawable.ic_cardano_ada_crypto_coin),
    Chainlink (R.drawable.ic_chainlink_link_crypto_coin),
    Chiliz (R.drawable.ic_chiliz_chz_crypto_coin),
    Compound (R.drawable.ic_compound_comp_crypto_coin),
    Curve_Dao (R.drawable.ic_curve_dao_token_crv_crypto_coin),
    Decentraland (R.drawable.ic_decentraland_mana_crypto_coin),
    Dodge_Coin (R.drawable.ic_dodge_coin_doge_crypto_coin),
    Dydx (R.drawable.ic_dydx_crypto_coin),
    Enjin_Coin (R.drawable.ic_enjin_coin_enj_crypto_coin),
    Ethereum (R.drawable.ic_ethereum_eth_crypto_coin),
    Fantom (R.drawable.ic_fantom_ftm_crypto_coin),
    Gala (R.drawable.ic_gala_crypto_coin),
    Lido_Dao (R.drawable.ic_lido_dao_ldo_crypto_coin),
    Litecoin (R.drawable.ic_litecoin_ltc_crypto_coin),
    Loopring (R.drawable.ic_loopring_lrc_crypto_coin),
    Maker (R.drawable.ic_maker_mkr_crypto_coin),
    Omg_Network (R.drawable.ic_omg_network_omg_crypto_coin),
    Polkadot (R.drawable.ic_polkadot_dot_crypto_coin),
    Polygon (R.drawable.ic_polygon_matic_crypto_coin),
    Quant (R.drawable.ic_quant_qnt_crypto_coin),
    Shiba_Inu (R.drawable.ic_shiba_inu_shib_crypto_coin),
    Solana (R.drawable.ic_solana_sol_crypto_coin),
    Synthetix (R.drawable.ic_synthetix_snx_crypto_coin),
    The_Graph (R.drawable.ic_the_graph_grt_crypto_coin),
    The_Sandox (R.drawable.ic_the_sandbox_sand_crypto_coin),
    SushiSwap (R.drawable.ic_sushiswap_sushi_crypto_coin),
    TruUsd (R.drawable.ic_tru_usd_tusd_crypto_coin),
    UniSwap (R.drawable.ic_uniswap_uni_crypto_coin),
    Usd (R.drawable.ic_usd_crypto_coin),
    Yearn_Finance (R.drawable.ic_yearn_finance_yfi_crypto_coin),
    Tron (R.drawable.ic_tron_trx_crypto_coin),
    Xrp (R.drawable.ix_xrp_crypto_coin),
    Dai (R.drawable.ic_dai_crypto_coin);

    fun toImage(): Int {
        return book
    }
}