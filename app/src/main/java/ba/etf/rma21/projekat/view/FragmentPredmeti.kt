package ba.etf.rma21.projekat.view

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.repositories.DBRepository
import ba.etf.rma21.projekat.viewmodel2.*

class FragmentPredmeti : Fragment() {

    private lateinit var odabirGodina : Spinner
    private lateinit var odabirPredmet : Spinner
    private lateinit var odabirGrupa : Spinner
    private lateinit var dodajPredmetDugme : Button
    private lateinit var ispisiDugme : Button

    // viewmodel klase za web servis
    private lateinit var predmetSpinnerModel : PredmetSpinnerModel
    private lateinit var grupaSpinnerModel: GrupaSpinnerModel
    private lateinit var upisModel: UpisModel
    private lateinit var accountModel: AccountModel
    private lateinit var dbModel: DBModel
    /*
    private lateinit var predmetSpinnerModel : PredmetSpinnerModel
    private lateinit var grupaSpinnerModel : GrupaSpinnerModel
    private lateinit var upisModel : UpisModel
    private lateinit var accountModel: AccountModel

     */

    // adapteri za spinnere
    private lateinit var adapterPredmeti : ArrayAdapter<String>
    private lateinit var adapterGrupe : ArrayAdapter<String>

    // ovdje se sada moraju cuvati i predmeti i grupe
    private lateinit var predmeti : List<Predmet>
    private lateinit var grupe: List<Grupa>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        MainActivity.trenutniKviz = null


        var view =  inflater.inflate(R.layout.predmeti_fragment, container, false)

        // inicijalizacija viewmodel
        predmetSpinnerModel = PredmetSpinnerModel()
        grupaSpinnerModel = GrupaSpinnerModel()
        upisModel = UpisModel()
        accountModel = AccountModel()
        dbModel = DBModel()

        ispisiDugme = view.findViewById(R.id.ispisiDugme)
        ispisiDugme.setBackgroundColor(Color.RED)
        ispisiDugme.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("BRISANJE PODATAKA")
            builder.setMessage("Da li ste sigurni da želite obrisati sve korisničke podatke?")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                accountModel.ispisi()
            }
            builder.setNegativeButton(android.R.string.no) { dialog, which ->

            }
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.show()
        }


        dodajPredmetDugme = view.findViewById(R.id.dodajPredmetDugme)
        dodajPredmetDugme.isEnabled = false
        dodajPredmetDugme.isClickable = false

        dodajPredmetDugme.setOnClickListener {

            upisModel.upisi(grupe[odabirGrupa.selectedItemPosition].id,onSuccess = ::onSuccessUpis, onError = ::onErrorUpis)
            val toast = Toast.makeText(context,"USPJEŠAN UPIS",Toast.LENGTH_SHORT)
            toast.show()
            //Korisnik.dodajKvizove(KvizRepository.getKvizoviByPredmetAndGrupa(odabirPredmet.selectedItem.toString(),odabirGrupa.selectedItem.toString()))
            //var porukaFragment = FragmentPoruka.newInstance(odabirPredmet.selectedItem.toString(),odabirGrupa.selectedItem.toString())
            //openFragment(porukaFragment)
        }

        odabirGrupa = view.findViewById(R.id.odabirGrupa)
        odabirPredmet = view.findViewById(R.id.odabirPredmet)
        odabirGodina = view.findViewById(R.id.odabirGodina)

        configureSpinnerGodina();
        return view;
    }


    companion object {
        fun newInstance(): FragmentPredmeti = FragmentPredmeti()
    }

    fun onSuccessUpis(message: String) {
        dbModel.updateuj()
        var porukaFragment = FragmentPoruka.newInstance(odabirPredmet.selectedItem.toString(),odabirGrupa.selectedItem.toString())
        openFragment(porukaFragment)
    }

    fun onErrorUpis(message: String) {
        val toast = Toast.makeText(context,"Greška sa povezivanjem na /grupa/{gid}/student/{id}",Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if (transaction != null) {
            transaction.replace(R.id.container, fragment)
        }
        if (transaction != null) {
            transaction.addToBackStack(null)
        }
        if (transaction != null) {
            transaction.commit()
        }
    }

    fun searchGrupe() {
        grupaSpinnerModel.search(predmeti[odabirPredmet.selectedItemPosition].id,onSuccess = ::onSuccessGrupe, onError = ::onErrorGrupe)
    }

    fun onSuccessGrupe(grupe:List<Grupa>) {
        //val toast = Toast.makeText(context,"Povezivanje sa /predmet/{id}/grupa uspješno",Toast.LENGTH_SHORT)
        //toast.show();

        this.grupe = grupe

        var nazivi = mutableListOf<String>();
        for (grupa in grupe) {
            nazivi.add(grupa.naziv)
        }
        adapterGrupe = ArrayAdapter<String>(activity?.applicationContext!!,R.layout.spinner_item,nazivi)
        odabirGrupa.adapter = adapterGrupe
    }

    fun onErrorGrupe() {
        val toast = Toast.makeText(context,"Greška sa povezivanjem na /predmet/{id}/grupa",Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun configureSpinnerGrupa() {
        searchGrupe()
        /*
        var nazivi = mutableListOf<String>();
        var adapter: ArrayAdapter<String>

        var grupe = GrupaRepository.getGroupsByPredmet(odabirPredmet.selectedItem.toString())
        for (grupa in grupe) {
            nazivi.add(grupa.naziv);
        }

        //adapter = ArrayAdapter<String>(activity?.applicationContext!!,android.R.layout.simple_spinner_dropdown_item,nazivi);
        adapter = ArrayAdapter<String>(activity?.applicationContext!!,R.layout.spinner_item,nazivi);


        odabirGrupa.adapter = adapter

         */

        odabirGrupa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                dodajPredmetDugme.isClickable = false
                dodajPredmetDugme.isEnabled = false
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                dodajPredmetDugme.isClickable = true
                dodajPredmetDugme.isEnabled = true
            }

        }
    }

    fun searchPredmeti() {
        predmetSpinnerModel.search(onSuccess = ::onSuccessPredmeti, onError = ::onErrorPredmeti)
    }

    fun onSuccessPredmeti(predmeti:List<Predmet>) {
        //val toast = Toast.makeText(context,"Povezivanje sa /predmet",Toast.LENGTH_SHORT)
        //toast.show();

        this.predmeti = predmeti.filter { it -> it.godina == odabirGodina.selectedItem.toString().toInt() }

        var nazivi = mutableListOf<String>();
        for (predmet in predmeti) {
            if(predmet.godina == odabirGodina.selectedItem.toString().toInt()) nazivi.add(predmet.naziv);
        }
        adapterPredmeti = ArrayAdapter<String>(activity?.applicationContext!!,R.layout.spinner_item,nazivi);
        odabirPredmet.adapter = adapterPredmeti
    }

    fun onErrorPredmeti() {
        val toast = Toast.makeText(context,"Greška sa povezivanjem na /predmet",Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun configureSpinnerPredmet() {
        /* stari kod
        var nazivi = mutableListOf<String>();
        var predmeti = PredmetRepository.getPredmetsByGodina(odabirGodina.selectedItem.toString().toInt())
        for (predmet in predmeti) {
            nazivi.add(predmet.naziv);
        }

         */

        // dohvatiti predmete sa godine odabirGodina.selectedItem.toString().toInt()

        searchPredmeti()

        //var adapter : ArrayAdapter<String> = ArrayAdapter<String>(activity?.applicationContext!!,android.R.layout.simple_spinner_dropdown_item,nazivi);
        //
        // vratiti liniju ispod
        // var adapter : ArrayAdapter<String> = ArrayAdapter<String>(activity?.applicationContext!!,R.layout.spinner_item,nazivi);

        //odabirPredmet.adapter = adapter
        odabirPredmet.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                odabirGrupa.isEnabled = isPredmetSelected()
                configureSpinnerGrupa()
            }

        }
    }

    private fun isPredmetSelected(): Boolean {
        return odabirPredmet.selectedItem !=null
    }

    private fun getIndex(spinner : Spinner,myString:String) : Int{
        for (i in 0..spinner.count-1){
            if (spinner.getItemAtPosition(i).toString() == myString){
                return i;
            }
        }
        return 0;
    }

    private fun configureSpinnerGodina() {
        var arraySpinner = mutableListOf<String>();
        //var thisYear : Int = Calendar.getInstance().get(Calendar.YEAR);
        for (i in 1..5) {
            arraySpinner.add(i.toString())
        }
        arraySpinner.reverse()
        var adapter : ArrayAdapter<String> = ArrayAdapter<String>(activity?.applicationContext!!,R.layout.spinner_item,arraySpinner);

        odabirGodina.adapter = adapter

        odabirGodina.setSelection(getIndex(odabirGodina,MainActivity.getLastSelectedYear()));

        odabirGodina.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                rememberSelectedYear();
                configureSpinnerPredmet()
                odabirGrupa.isEnabled = odabirPredmet.selectedItem != null
            }

        }
    }

    private fun rememberSelectedYear() {
        MainActivity.setLastSelectetYear(odabirGodina.selectedItem.toString())
    }
}