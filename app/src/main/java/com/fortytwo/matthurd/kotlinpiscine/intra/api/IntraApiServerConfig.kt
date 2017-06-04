package com.fortytwo.matthurd.kotlinpiscine.intra.api

class IntraApiServerConfig(
        val baseUrl: String = "https://api.intra.42.fr/v2/",
        val uid: String,
        val secret: String
)