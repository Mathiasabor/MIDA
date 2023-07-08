package com.kirt.finalbibliouut.Routes

sealed class Ecrans (val destination : String="", item : String="") {

    object Accueil : Ecrans("accueil")
    object Consultes : Ecrans("consultes")
    object Login : Ecrans("login")
    object SearchThese : Ecrans("searchthese")
    object SearchPage : Ecrans("searchpage")
    object Telecharge : Ecrans("telecharge")
    object These : Ecrans("these")


}