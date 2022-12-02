package ba.etf.rma21.projekat

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.data.models.Account
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.repositories.*
import ba.etf.rma21.projekat.view.FragmentKvizovi
import ba.etf.rma21.projekat.view.FragmentPoruka
import ba.etf.rma21.projekat.view.FragmentPredmeti
import ba.etf.rma21.projekat.viewmodel2.AccountModel
import ba.etf.rma21.projekat.viewmodel2.DBModel
import ba.etf.rma21.projekat.viewmodel2.KvizTakenModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    //private var ac = AccountRepository()
    private var kvizTakenModel: KvizTakenModel = KvizTakenModel()
    private var accountModel: AccountModel = AccountModel()
    private var DBModel: DBModel = DBModel()


    companion object {

        public var trenutniKviz: Kviz? = null

        private var lastSelectedYear : String = "2"
        public fun setLastSelectetYear(lastSelectedYear: String) {
            this.lastSelectedYear = lastSelectedYear;
        }

        public fun getLastSelectedYear() : String {
            return this.lastSelectedYear
        }
    }

    private lateinit var bottomNav: BottomNavigationView

    fun onSuccess(kvizTakens: List<KvizTaken>) {
        Toast.makeText(applicationContext,"DOŠLO",Toast.LENGTH_LONG).show()
        DBModel.updateuj()
        val porukaFragment = FragmentPoruka.newInstance("",(kvizTakens[0].osvojeniBodovi.toFloat()/100F).toString(),
                trenutniKviz!!.naziv)
        openFragment(porukaFragment)
    }

    fun onError() {

    }

    fun OdgovoriPoslaniNaApi() {

        if(trenutniKviz != null) {
            kvizTakenModel.getPokusajiZaKviz(
                    trenutniKviz!!.id,
                    onSuccess = ::onSuccess,
                    onError = ::onError
            )
        }


    }

    fun onSuccessUcitaniOdgovoriDB(odgovori: List<Odgovor>,kvizId: Int) {
        // sada salji ove odgovore redom
        //Toast.makeText(applicationContext,odgovori.toString(),Toast.LENGTH_LONG).show()
        //for(odgovor in odgovori) {
        //kvizTakenModel.odgovori(trenutniKviz!!.id,onSuccess = ::OdgovoriPoslaniNaApi,onError = {})
        if(odgovori.isNotEmpty()) kvizTakenModel.odgovori(odgovori[0].KvizId,onSuccess = ::OdgovoriPoslaniNaApi,onError = {})
        else kvizTakenModel.odgovori(kvizId,onSuccess = ::OdgovoriPoslaniNaApi,onError = {})
        //}
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        fun onSuccess(kvizTakens: List<KvizTaken>) {

            // ovdje sada treba citati odgovore sa id-om kvizTaken
            /*var odgovori = DBModel.dajOdgovoreDB(onSuccess = ::onSuccessUcitaniOdgovoriDB,
                    onError = {})

             */
            /*
            var odgovoriZaKvizTaken = DBModel.dajOdgovoreZaKvizTakenDB(kvizTakens[0].id,onSuccess = ::onSuccessUcitaniOdgovoriDB,
                    onError = {})
             */

            /*
            var odgovoriZaKviz = DBModel.dajOdgovoreZaKvizDB(trenutniKviz!!.id,onSuccess = ::onSuccessUcitaniOdgovoriDB,
                    onError = {})

             */



            val porukaFragment = FragmentPoruka.newInstance("",(kvizTakens[0].osvojeniBodovi.toFloat()/100F).toString(),
                trenutniKviz!!.naziv)
            openFragment(porukaFragment)
        }

        fun onError() {

        }

        when (item.itemId) {
            R.id.kvizovi -> {
                val kvizoviFragment = FragmentKvizovi.newInstance()
                openFragment(kvizoviFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.upisDugme -> {
                val predmetiFragment = FragmentPredmeti.newInstance()
                openFragment(predmetiFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.predajKviz -> {
                var odgovoriZaKviz = DBModel.dajOdgovoreZaKvizDB(trenutniKviz!!.id,onSuccess = ::onSuccessUcitaniOdgovoriDB,
                        onError = {})
                // ovo se mora mijenjati na sljedeci nacin, prvo ce se dobaviti svi kvizovi iz baze sa id-om trenutniKviz!!.id
                /*
                if(trenutniKviz != null) {
                    kvizTakenModel.getPokusajiZaKviz(
                        trenutniKviz!!.id,
                        onSuccess = ::onSuccess,
                        onError = ::onError
                    )
                }

                 */


                // imamo id trenutnog kviza, pa se uvijek moze naci i broj osvojenih bodova
                /*val porukaFragment = FragmentPoruka.newInstance("",75.toString())
                openFragment(porukaFragment)

                 */
                return@OnNavigationItemSelectedListener true
            }
            R.id.zaustaviKviz -> {
                val kvizoviFragment = FragmentKvizovi.newInstance()
                openFragment(kvizoviFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    //private var korisnik: Korisnik = Korisnik(arrayListOf())
    //private var korisnik: Korisnik_v2 = Korisnik_v2()

    fun onSuccessPayload(payload: String) {
        // nema potreba da se updateuje odmah pri startu aplikacije
        //DBModel.updateuj()


    /*var toast = Toast.makeText(applicationContext,payload, Toast.LENGTH_LONG)
        toast.show()

         */
    }

    fun onSuccessStudent(student: Account) {
        var toast = Toast.makeText(applicationContext,student.toString(), Toast.LENGTH_LONG)
        toast.show()




        //intent.getStringExtra("payload")?.let { accountModel.postaviKljuc(student,it,applicationContext,onSuccess = ::onSuccessPayload,
        //    onError = {}) }


        //accountModel.postaviKljuc(student,"testni",applicationContext,onSuccess = ::onSuccessPayload,onError = {})

    }

    fun handleIntent(payload: String) {
        //accountModel.getStudentaZaId(AccountRepository.getHash(),onSuccess = ::onSuccessStudent,onError = {})

        accountModel.postaviKljuc(payload,applicationContext,onSuccess = ::onSuccessPayload,
                onError = {})

        /*
        var toast = Toast.makeText(applicationContext,intent.getStringExtra("payload"), Toast.LENGTH_LONG)
        toast.show()

        intent.getStringExtra("payload")?.let { accountModel.postaviKljuc(it,applicationContext,onSuccess = ::onSuccessPayload,
                onError = {}) }

         */
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        postaviContext()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


        // ovdje ce biti intent
        //AccountRepository.postaviHash(BuildConfig.API_KEY)
        if(intent!=null) {
            if (intent?.action == Intent.ACTION_VIEW) intent.getStringExtra("payload")?.let {
                handleIntent(
                    it
                )
                //DBModel.updateuj()
            }

            else {
                handleIntent(AccountRepository.getHash())
                //DBModel.updateuj()
            }
        }
        else {
            handleIntent(AccountRepository.getHash())
            //DBModel.updateuj()
        }


        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottomNav.selectedItemId = R.id.kvizovi
        val kvizoviFragment = FragmentKvizovi.newInstance()
        openFragment(kvizoviFragment)

        /*
        configureButton()
        configureAdapter()
        configureSpinner()


         */

        /*
        val extras = intent.extras
        if(extras!=null) {
            lastSelectedYear = extras.getString("godina","")
            Korisnik.dodajKvizove(KvizRepository.getKvizoviByPredmetAndGrupa(extras.getString("naziv",""),extras.getString("grupa","")))
            //korisnik.predmeti.add(Predmet(extras.getString("naziv",""),extras.getString("godina","").toInt()))
            //korisnik.grupe.add(Grupa(extras.getString("grupa",""),extras.getString("naziv","")))
            kvizoviAdapter.updateKvizovi(KvizRepository.getMyKvizes());
        }

         */
    }

    private fun postaviContext() {
        AccountRepository.setContext(applicationContext)
        DBRepository.setContext(applicationContext)
        KvizRepository.setContext(applicationContext)
        PitanjeKvizRepository.setContext(applicationContext)
        PredmetIGrupaRepository.setContext(applicationContext)
        TakeKvizRepository.setContext(applicationContext)
        OdgovorRepository.setContext(applicationContext)
    }

    override fun onBackPressed() {
        var f: Fragment? = supportFragmentManager.findFragmentById(R.id.container)
        if(f is FragmentKvizovi) {
            finish()
            System.exit(0)
            //super.onBackPressed()
        }
        else {
            val kvizoviFragment = FragmentKvizovi.newInstance()
            openFragment(kvizoviFragment)
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}

