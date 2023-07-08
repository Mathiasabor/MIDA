package com.kirt.finalbibliouut.ViewModel


import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirt.finalbibliouut.Databases.API.ApiClient
import com.kirt.finalbibliouut.Databases.BookCategory
import com.kirt.finalbibliouut.Databases.Bookdata
import com.kirt.finalbibliouut.Databases.Consulte
import com.kirt.finalbibliouut.Databases.Telecharge
import com.kirt.finalbibliouut.Databases.These
import com.kirt.finalbibliouut.Databases.Users
import com.kirt.finalbibliouut.Databases.key
import com.kirt.finalbibliouut.Databases.refresh
import com.kirt.finalbibliouut.Databases.user
import com.kirt.finalbibliouut.LoginActivity
import com.kirt.finalbibliouut.Preferences.UsersSharedPreferencesRepository
import com.kirt.finalbibliouut.Preferences.keySharedPreferencesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDate


class LoginViewModel(appcontext: Context, scoped : CoroutineScope): ViewModel() {



    val sharedPrefs = appcontext.getSharedPreferences("Token", Context.MODE_PRIVATE)
    val keySharedPrefsRepo = keySharedPreferencesRepository(sharedPrefs)

    val UserSharedPrefs = appcontext.getSharedPreferences("User", Context.MODE_PRIVATE)
    val UserSharedPrefsRepo = UsersSharedPreferencesRepository(UserSharedPrefs)
    val scope = scoped
    //no use boolean utilisé dans Login()
    var bood = mutableStateOf(false)
    var _bood : State<Boolean> = bood



    /* ici on a les événements liés à l'accueil */
    //ici on charge l'ensemble des catégories de  l'API

    suspend fun ChargeCategories()
    {

        val key = keySharedPrefsRepo.read()
        val accessed = "Bearer ${key.access}"
        _bookcat.value = executeCallCategorie(accessed)
    }

    var Ncat = mutableStateOf("")
    var _Ncat : State<String> = Ncat





    var _bookcat = mutableStateOf(mutableListOf<BookCategory>())
    val bookcat : State<MutableList<BookCategory>> = _bookcat

    /* ici on la  les événements liés à la liste de livres*/
    var _booklist = mutableStateOf(
        mutableListOf<Bookdata>()

    )
    val booklist : State<MutableList<Bookdata>> = _booklist

    var bdlist : MutableList<Bookdata> = mutableListOf()


    var bglist = mutableStateOf(bdlist)

    var Bglist : State<MutableList<Bookdata>> = bglist
    //fonction qui permettra d'aller à la liste des livres de chaque categories



    fun DetailCatego(cate : String)
    {
        bglist.value.clear()
        Ncat.value = cate
        booklist.value.forEach{book ->
            if(book.categoried.nom ==cate)
            {
                bglist.value.add(book)
            }
        }
    }

    //ici on va récupérer les infirmation de l'utilisateur pour l'afficher dans le menu

    var infoUsers = mutableStateOf(getInfoUser())
    var _infoUsers : State<Users> =infoUsers
    var antUser = mutableListOf<Users>()
    fun getInfoUser() :Users
    {
        return  UserSharedPrefsRepo.read()
    }

    suspend fun ChargeUser()
    {
        val key = keySharedPrefsRepo.read()
        val accessed = "Bearer ${key.access}"
        antUser = executeUser(accessed)
    }


    /* ici on aura l'événement liés à la categorie*/




    //fonction qui permettra de charger le livre à la page de détail du livre

    var bookdet = mutableStateOf( Bookdata(1,"DUBRULLE Louis;-Compta analytique de gestion","Paris;Dunod,03","4ème EDITION",BookCategory("COMPTABILITE",1),5,"45AD"))
     var book : State<Bookdata> = bookdet


    fun ReturnLivre(isbn : String)
    {
        booklist.value.forEach{ boot ->
            if(isbn == boot.isbn)
            {
                bookdet.value =boot
            }

        }

    }


    /* ici on aura les événements liés au livre*/

    suspend fun ChargeLivres()
    {
        val key = keySharedPrefsRepo.read()
        val accessed = "Bearer ${key.access}"
        _booklist.value = executeCallBook(accessed)

    }

    var isbn = mutableStateOf("")
    var _isbn : State<String> = isbn
    //la fonction lié au bouton consulté de la page de détail du livre qui permet d'afficher le isbn
    fun Consultat(isbnt : String)
    {
        isbn.value = isbnt

    }

    fun videConsultat()
    {
        isbn.value =""
    }


    /* ici on aura les événements liés aux livres consultés*/

    var ConsultBook = mutableStateOf(mutableListOf<Consulte>())
    var _Consultbook : State<MutableList<Consulte>> = ConsultBook

    lateinit var bookConsd : Consulte


   suspend fun LivreConsulte()
    {
        val key = keySharedPrefsRepo.read()
        val accessed = "Bearer ${key.access}"
        ConsultBook.value = executeGetConsulte(accessed)

    }

    suspend fun Consulter()
    {


        val key = keySharedPrefsRepo.read()
        val accessed = "Bearer ${key.access}"
        val bookconsulted = Consulte(LocalDate.now().toString(),book.value.id,_infoUsers.value.id)

        bookConsd= executePostConsulte(accessed,bookconsulted)

    }


    /* ici on aura les événement lié à la barre de recherche des theses et memoires et la page de recherche */
    var reThese = mutableStateOf(mutableListOf<These>())
    var _reThese : State<MutableList<These>> = reThese

    fun TRechercher(mot : String)
    {
        reThese.value = TRechercherOperation(mot)
    }


    fun TRechercherOperation(mot : String) :MutableList<These>
    {
       return _LisThese.value.filter { it.name.contains(mot,true) } as MutableList<These>
    }

    /* ici on aura les événements liés à la barre de recherche et la page de recherche */

    var reBook = mutableStateOf(mutableListOf<Bookdata>())
    var _rebook : State<MutableList<Bookdata>> = reBook


    fun Rechercher(mot : String)
    {
        reBook.value = RechercherOperation(mot)
    }

    fun RechercherOperation(mot : String) :MutableList<Bookdata>
    {
        return booklist.value.filter { it.description.contains(mot, true) } as MutableList<Bookdata>
    }







    /* ici on aura les événements liés aux thèses*/

    var LisThese = mutableStateOf(mutableListOf<These>())
    var _LisThese : State<MutableList<These>> = LisThese
    lateinit var TheseTel : Telecharge
    var LisTelec = mutableStateOf(mutableListOf<Telecharge>())
    var _LisTelec : State<MutableList<Telecharge>> = LisTelec

    //fonction qui va charger les thèses
   suspend fun ChargeThese()
    {
        val key = keySharedPrefsRepo.read()
        val accessed = "Bearer ${key.access}"

        val lstThese = executeGetThese(accessed)
        LisThese.value = lstThese

    }

    //fonction qui va charger les thèses téléchargés

    suspend fun ChargeTelech()
    {
        val key = keySharedPrefsRepo.read()
        val accessed = "Bearer ${key.access}"

        val lstTele = executeGetTelecharge(accessed)
        LisTelec.value = lstTele
    }

    suspend fun Telecharger(thesed : Int)
    {
        val key = keySharedPrefsRepo.read()
        val accessed = "Bearer ${key.access}"
        val telchTo = Telecharge(LocalDate.now().toString(),thesed,_infoUsers.value.id)
        val postTotel = executePostTelecharge(accessed,telchTo)
        TheseTel = postTotel


    }




    /*Les fonctions liés à l'api */





    //fonction qui va renvoyer la liste des categories

    suspend fun executeCallCategorie(token : String) :MutableList<BookCategory>
    {



        return viewModelScope.async {
            var _bookcatt = mutableListOf<BookCategory>()
            try {
                var response = ApiClient.apiService.getCategories(token)
                if (response.isSuccessful && response.body()!=null)
                {
                    var content = response.body()
                    _bookcatt = content!!
                }else{

                }
            }
            catch (e: Exception)
            {
                println(e.message)
            }
            return@async _bookcatt
        }.await()
    }

    //fonction qui va renvoyer la liste des livres

    suspend fun executeCallBook(token : String) : MutableList<Bookdata>
    {
        return viewModelScope.async {
            var _bookda = mutableListOf<Bookdata>()
            try {
                var response = ApiClient.apiService.getLivres(token)
                if(response.isSuccessful && response.body()!=null)
                {
                    var content = response.body()
                    _bookda= content!!
                }else{

                }
            } catch (e: Exception)
            {
                println(e.message)
            }
            return@async _bookda
        }.await()
    }


    //fonction qui va faire le login


    var token : String=""
    var Key = key("","")
    var refresht = refresh("")
    suspend fun executeCallLogin(username :String, password:String) : Boolean
    {

        var user =user(username, password)
        var auth = false
        return GlobalScope.async {
            var tokend =key("","")
            try {
                var response = ApiClient.apiService.Login(user)
                if (response.isSuccessful && response.body()!=null)
                {
                    var content = response.body()
                    auth = true
                    tokend = content!!
                    Key = content

                    keySharedPrefsRepo.write(content)



                }else{

                }
            }
            catch (e:Exception){
                println(e.message)
            }
            return@async auth
        }.await()

    }


    //fonction qui va récupérer les informations liés à l'utilisateur

    suspend fun executeUser(token: String) :MutableList<Users>
    {
       return viewModelScope.async {
            var user = mutableListOf(Users(0,"","","","","",false))

            try {

                var response = ApiClient.apiService.getCurrentUser(token)
                if (response.isSuccessful && response.body()!=null)
                {
                    val content = response.body()
                    user = content!!
                    UserSharedPrefsRepo.write(user[0])

                }else{
                    scope.launch {

                    }
                }

            }catch (e : Exception)
            {
                println(e.message)
            }
return@async user

        }.await()
    }

    // fonction qui va récupérer la liste livres consultés par l'utilisateur
    suspend fun executeGetConsulte(token: String): MutableList<Consulte>
    {
        return viewModelScope.async {
            var conList = mutableListOf<Consulte>()

            try {
                val response = ApiClient.apiService.getConsultes(token)
                if(response.isSuccessful && response.body()!=null)
                {
                    val content = response.body()
                    conList = content!!

                }else{

                }

            }catch (e : Exception)
            {
                println(e.message)
            }

            return@async conList
        }.await()
    }

    // fonction qui consulte les livres qui est lié au bouton qui se trouve sur la page consulte
    suspend fun executePostConsulte(token : String, consulte: Consulte): Consulte
    {
        return viewModelScope.async {

            var consulted = Consulte("",0,0)
            try {
                val response = ApiClient.apiService.postConsultes(token,consulte)
                if (response.isSuccessful && response.body()!=null)
                {
                    val content = response.body()
                    consulted = content!!


                }else{

                }


            }catch (e :Exception){
                println(e.message)
            }
            return@async consulted
        }.await()

    }
    // fonction qui renvoie la liste des theses
    suspend fun executeGetThese(token:String) : MutableList<These>
    {
        return viewModelScope.async {
            var theseda = mutableListOf<These>()

            try {
                val response = ApiClient.apiService.getTheses(token)
                if(response.isSuccessful && response.body() !=null)
                {
                    val content = response.body()
                    theseda = content!!

                }else{

                }

            }catch (e: Exception)
            {
                println(e.message)
            }


            return@async theseda
        }.await()
    }
    //fonction qui permet d'ajouter les thèses aux telecharges
    suspend fun executePostTelecharge(token:String, telecharge: Telecharge): Telecharge
    {
        return viewModelScope.async {
            var telechd = Telecharge("",0,0)
            try {
                val response = ApiClient.apiService.postTelecharges(token,telecharge)
                if (response.isSuccessful && response.body()!=null) {
                    val content = response.body()
                    telechd = content!!
                }else{

                }

            }catch(e : Exception)
            {
                println(e.message)
            }


            return@async telechd
        }.await()
    }
    // fonction qui affiche les these telecharge
    suspend fun executeGetTelecharge(token:String) :MutableList<Telecharge>
    {
        return viewModelScope.async {
            var telecharda = mutableListOf<Telecharge>()

            try {
                val response = ApiClient.apiService.getTelecharges(token)
                if(response.isSuccessful && response.body() !=null) {
                    val content = response.body()
                    telecharda = content !!
                }else{

                }


            }catch (e: Exception)
            {
                println(e.message)
            }


            return@async telecharda
        }.await()
    }


}