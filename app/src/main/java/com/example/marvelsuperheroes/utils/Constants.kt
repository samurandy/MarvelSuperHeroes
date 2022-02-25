package com.example.marvelsuperheroes.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp


class Constants {
    companion object {
        const val BASE_URL = "http://gateway.marvel.com/v1/public/"
        val ts = Timestamp(System.currentTimeMillis()).time.toString()

        const val API_KEY = "0409d555ebcc7d35becdb9436e75cda0"
        const val PRIVATE_KEY = "1b82f41e168d650fdc913a82328c1dc12cd230af"
        const val limit = "100"

        const val radix = 16
        const val padStart = 32

        fun hash(): String {
            val input = "$ts$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(radix).padStart(padStart, '0')
        }

        const val IMAGE_LARGE_SIZE = "/standard_large"
        const val IMAGE_XLARGE_SIZE = "/standard_xlarge"
        const val DOT = "."
    }




}