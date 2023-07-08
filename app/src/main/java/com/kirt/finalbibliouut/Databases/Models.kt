package com.kirt.finalbibliouut.Databases
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.LocalDate
import android.content.SharedPreferences
import javax.inject.Inject


data class Bookdata(
    val id : Int,
    val description : String,
    val linodate : String,
    val editions : String,
    val categoried : BookCategory,
    val exemplaire : Int,
    val isbn : String
)

data class BookCategory(
    val nom :  String,
    val id : Int
)

data class logind(
    val username : String,
    val email : String,
    val password :String,

)

data class Consulte(
    val date : String,
    val livred : Int,
    val utilisateur : Int
)

data class These(
    var id: Int,
    var name: String,
    var link : String
)

data class Telecharge(
    val dates : String,
    val thesed : Int,
    val utilisateur : Int
)


// information de login

data class user(
    val username:String,
    val password:String
)
//l'acess retounné lorsque du raffriahcissement
data class access(
    var access:String
)
// l'objet refresh pour raffraichir l'access
data class refresh(
    var refresh:String
)

//reponse de la connexion

data class key (

    var refresh :String,
    var access : String

)

//information complete de l'utilisateur connecté
data class Users(
    val id : Int,
    val email : String,
    val username:String,
    val password:String,
    val first_name : String,
    val last_name : String,
    val is_active : Boolean
)



