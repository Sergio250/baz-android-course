@file:Suppress("SpellCheckingInspection")

package com.example.cryptochallenge.utils

import com.example.cryptochallenge.R

enum class CryptoCatalog(val book: String, val image: Int) {
    AAVE("ave_usd", R.drawable.ic_aave_crypto_coin),
    ALGORAND("algo_usd", R.drawable.ic_algorand_algo_crypto_coin),
    APECOIN("ape_usd", R.drawable.ic_apecoin_ape_crypto_coin),
    AXIE_INFINITY("axs_usd", R.drawable.ic_axie_infinity_axs_crypto_coin),
    BALANCER("bal_usd", R.drawable.ic_balancer_bal_crypto_coin),
    BASIC_ATTENTION_TOKEN("bat_mxn", R.drawable.ic_basic_attention_token_bat_crypto_coin),
    BITCOIN("btc_mxn", R.drawable.ic_bitcoin_btc_crypto_coin),
    BITCOIN_CASH("bch_mxn", R.drawable.ic_bitcoin_cash_bch_crypto_coin),
    CARDANO("ada_usd", R.drawable.ic_cardano_ada_crypto_coin),
    CHAINLINK("link_usd", R.drawable.ic_chainlink_link_crypto_coin),
    CHILIZ("chz_usd", R.drawable.ic_chiliz_chz_crypto_coin),
    COMPOUND("comp_usd", R.drawable.ic_compound_comp_crypto_coin),
    CURVE_DAO("crv_usd", R.drawable.ic_curve_dao_token_crv_crypto_coin),
    DECENTRALAND("mana_mxn", R.drawable.ic_decentraland_mana_crypto_coin),
    DODGE_COIN("doge_usd", R.drawable.ic_dodge_coin_doge_crypto_coin),
    DYDX("dydx_usd", R.drawable.ic_dydx_crypto_coin),
    ENJIN_COIN("enj_usd", R.drawable.ic_enjin_coin_enj_crypto_coin),
    ETHEREUM("eth_mxn", R.drawable.ic_ethereum_eth_crypto_coin),
    FANTOM("ftm_usd", R.drawable.ic_fantom_ftm_crypto_coin),
    GALA("gala_usd", R.drawable.ic_gala_crypto_coin),
    LIDO_DAO("ldo_usd", R.drawable.ic_lido_dao_ldo_crypto_coin),
    LITECOIN("ltc_mxn", R.drawable.ic_litecoin_ltc_crypto_coin),
    LOOPRING("lrc_usd", R.drawable.ic_loopring_lrc_crypto_coin),
    MAKER("mkr_usd", R.drawable.ic_maker_mkr_crypto_coin),
    OMG_NETWORK("omg_usd", R.drawable.ic_omg_network_omg_crypto_coin),
    POLKADOT("dot_usd", R.drawable.ic_polkadot_dot_crypto_coin),
    POLYGON("matic_usd", R.drawable.ic_polygon_matic_crypto_coin),
    QUANT("qnt_usd", R.drawable.ic_quant_qnt_crypto_coin),
    SHIBA_INU("shib_usd", R.drawable.ic_shiba_inu_shib_crypto_coin),
    SOLANA("sol_usd", R.drawable.ic_solana_sol_crypto_coin),
    SYNTHETIX("snx_usd", R.drawable.ic_synthetix_snx_crypto_coin),
    THE_GRAPH("grt_usd", R.drawable.ic_the_graph_grt_crypto_coin),
    THE_SANDOX("sand_usd", R.drawable.ic_the_sandbox_sand_crypto_coin),
    SUSHISWAP("sushi_usd", R.drawable.ic_sushiswap_sushi_crypto_coin),
    TRUUSD("tusd_mxn", R.drawable.ic_tru_usd_tusd_crypto_coin),
    UNISWAP("uni_usd", R.drawable.ic_uniswap_uni_crypto_coin),
    USD("usd_mxn", R.drawable.ic_usd_crypto_coin),
    YEARN_FINANCE("yfi_usd", R.drawable.ic_yearn_finance_yfi_crypto_coin),
    TRON("trx_usd", R.drawable.ic_tron_trx_crypto_coin),
    XRP("xrp_mxn", R.drawable.ix_xrp_crypto_coin),
    DAI("dai_mxn", R.drawable.ic_dai_crypto_coin);

    fun toImage(): Int = image
    fun toName(): String = name
}
