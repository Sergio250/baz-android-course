package com.example.cryptochallenge.utils

enum class CryptoNameCatalog(val book: String) {
    AAVE ("ave_usd"),
    ALGORAND ("algo_usd"),
    APECOIN ("ape_usd"),
    AXIE_INFINITY ("axs_usd"),
    BALANCER ("bal_usd"),
    BASIC_ATTENTION_TOKEN ("bat_mxn"),
    BITCOIN ("btc_mxn"),
    BITCOIN_CASH ("bch_mxn"),
    CARDANO ("ada_usd"),
    CHAINLINK ("link_usd"),
    CHILIZ ("chz_usd"),
    COMPOUND ("comp_usd"),
    CURVE_DAO ("crv_usd"),
    DECENTRALAND ("mana_mxn"),
    DODGE_COIN ("doge_usd"),
    DYDX ("dydx_usd"),
    ENJIN_COIN ("enj_usd"),
    ETHEREUM ("eth_mxn"),
    FANTOM ("ftm_usd"),
    GALA ("gala_usd"),
    LIDO_DAO ("ldo_usd"),
    LITECOIN ("ltc_mxn"),
    LOOPRING ("lrc_usd"),
    MAKER ("mkr_usd"),
    OMG_NETWORK ("omg_usd"),
    POLKADOT ("dot_usd"),
    POLYGON ("matic_usd"),
    QUANT ("qnt_usd"),
    SHIBA_INU ("shib_usd"),
    SOLANA ("sol_usd"),
    SYNTHETIX ("snx_usd"),
    THE_GRAPH ("grt_usd"),
    THE_SANDBOX ("sand_usd"),
    SUSHISWAP ("sushi_usd"),
    TRUUSD ("tusd_mxn"),
    UNISWAP ("uni_usd"),
    USD ("usd_mxn"),
    YEARN_FINANCE ("yfi_usd"),
    TRON ("trx_usd"),
    XRP ("xrp_mxn"),
    DAI ("dai_mxn");

    fun toName(): String {
        return name
    }
}