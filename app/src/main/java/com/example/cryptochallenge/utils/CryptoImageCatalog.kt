package com.example.cryptochallenge.utils

import com.example.cryptochallenge.R

enum class CryptoImageCatalog(val book: Int) {
    AAVE (R.drawable.ic_aave_crypto_coin),
    ALGORAND (R.drawable.ic_algorand_algo_crypto_coin),
    APECOIN (R.drawable.ic_apecoin_ape_crypto_coin),
    AXIE_INFINITY (R.drawable.ic_axie_infinity_axs_crypto_coin),
    BALANCER (R.drawable.ic_balancer_bal_crypto_coin),
    BASIC_ATTENTION_TOKEN (R.drawable.ic_basic_attention_token_bat_crypto_coin),
    BITCOIN (R.drawable.ic_bitcoin_btc_crypto_coin),
    BITCOIN_CASH (R.drawable.ic_bitcoin_cash_bch_crypto_coin),
    CARDANO (R.drawable.ic_cardano_ada_crypto_coin),
    CHAINLINK (R.drawable.ic_chainlink_link_crypto_coin),
    CHILIZ (R.drawable.ic_chiliz_chz_crypto_coin),
    COMPOUND (R.drawable.ic_compound_comp_crypto_coin),
    CURVE_DAO (R.drawable.ic_curve_dao_token_crv_crypto_coin),
    DECENTRALAND (R.drawable.ic_decentraland_mana_crypto_coin),
    DODGE_COIN (R.drawable.ic_dodge_coin_doge_crypto_coin),
    DYDX (R.drawable.ic_dydx_crypto_coin),
    ENJIN_COIN (R.drawable.ic_enjin_coin_enj_crypto_coin),
    ETHEREUM (R.drawable.ic_ethereum_eth_crypto_coin),
    FANTOM (R.drawable.ic_fantom_ftm_crypto_coin),
    GALA (R.drawable.ic_gala_crypto_coin),
    LIDO_DAO (R.drawable.ic_lido_dao_ldo_crypto_coin),
    LITECOIN (R.drawable.ic_litecoin_ltc_crypto_coin),
    LOOPRING (R.drawable.ic_loopring_lrc_crypto_coin),
    MAKER (R.drawable.ic_maker_mkr_crypto_coin),
    OMG_NETWORK (R.drawable.ic_omg_network_omg_crypto_coin),
    POLKADOT (R.drawable.ic_polkadot_dot_crypto_coin),
    POLYGON (R.drawable.ic_polygon_matic_crypto_coin),
    QUANT (R.drawable.ic_quant_qnt_crypto_coin),
    SHIBA_INU (R.drawable.ic_shiba_inu_shib_crypto_coin),
    SOLANA (R.drawable.ic_solana_sol_crypto_coin),
    SYNTHETIX (R.drawable.ic_synthetix_snx_crypto_coin),
    THE_GRAPH (R.drawable.ic_the_graph_grt_crypto_coin),
    THE_SANDOX (R.drawable.ic_the_sandbox_sand_crypto_coin),
    SUSHISWAP (R.drawable.ic_sushiswap_sushi_crypto_coin),
    TRUUSD (R.drawable.ic_tru_usd_tusd_crypto_coin),
    UNISWAP (R.drawable.ic_uniswap_uni_crypto_coin),
    USD (R.drawable.ic_usd_crypto_coin),
    YEARN_FINANCE (R.drawable.ic_yearn_finance_yfi_crypto_coin),
    TRON (R.drawable.ic_tron_trx_crypto_coin),
    XRP (R.drawable.ix_xrp_crypto_coin),
    DAI (R.drawable.ic_dai_crypto_coin);

    fun toImage(): Int {
        return book
    }
}