package com.example.cryptochallenge.utils

enum class CryptoNameCatalog(val book: String) {
    Aavea ("ave_usd"),
    Algorand ("algo_usd"),
    Apecoin ("ape_usd"),
    Axie_Infinity ("axs_usd"),
    Balancer ("bal_usd"),
    Basic_Attention_Token ("bat_mxn"),
    Bitcoin ("btc_mxn"),
    Bitcoin_Cash ("bch_mxn"),
    Cardano ("ada_usd"),
    Chainlink ("link_usd"),
    Chiliz ("chz_usd"),
    Compound ("comp_usd"),
    Curve_Dao ("crv_usd"),
    Decentraland ("mana_mxn"),
    Dodge_Coin ("doge_usd"),
    Dydx ("dydx_usd"),
    Enjin_Coin ("enj_usd"),
    Ethereum ("eth_mxn"),
    Fantom ("ftm_usd"),
    Gala ("gala_usd"),
    Lido_Dao ("ldo_usd"),
    Litecoin ("ltc_mxn"),
    Loopring ("lrc_usd"),
    Maker ("mkr_usd"),
    Omg_Network ("omg_usd"),
    Polkadot ("dot_usd"),
    Polygon ("matic_usd"),
    Quant ("qnt_usd"),
    Shiba_Inu ("shib_usd"),
    Solana ("sol_usd"),
    Synthetix ("snx_usd"),
    The_Graph ("grt_usd"),
    The_Sandbox ("sand_usd"),
    SushiSwap ("sushi_usd"),
    TruUsd ("tusd_mxn"),
    UniSwap ("uni_usd"),
    Usd ("usd_mxn"),
    Yearn_Finance ("yfi_usd"),
    Tron ("trx_usd"),
    Xrp ("xrp_mxn"),
    Dai ("dai_mxn");

    fun toName(): String {
        return name
    }
}