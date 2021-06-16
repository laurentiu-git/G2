package com.rld.g2esports.data.models.shop

data class Prices(
    var currency_code: String,
    var currency_decimal_separator: String,
    var currency_minor_unit: Int,
    var currency_prefix: String,
    var currency_suffix: String,
    var currency_symbol: String,
    var currency_thousand_separator: String,
    var price: String,
    var regular_price: String,
    var sale_price: String
)