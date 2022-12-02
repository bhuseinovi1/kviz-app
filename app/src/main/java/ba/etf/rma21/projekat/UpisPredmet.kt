package ba.etf.rma21.projekat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import ba.etf.rma21.projekat.data.repositories.`Z-GrupaRepository`

class UpisPredmet : AppCompatActivity() {

    private lateinit var odabirGodina : Spinner
    private lateinit var odabirPredmet : Spinner
    private lateinit var odabirGrupa : Spinner
    private lateinit var dodajPredmetDugme : Button
    private var lastSelectedYear: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upis_predmet)

        val extras = intent.extras
        if(extras!=null) {
            lastSelectedYear = extras.getString("lastSelectedYear","")

        }

        dodajPredmetDugme = findViewById(R.id.dodajPredmetDugme)
        dodajPredmetDugme.isEnabled = false
        dodajPredmetDugme.isClickable = false

        dodajPredmetDugme.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java).apply {
                putExtra("godina",odabirGodina.selectedItem.toString())
                putExtra("naziv",odabirPredmet.selectedItem.toString())
                putExtra("grupa",odabirGrupa.selectedItem.toString())
            }
            startActivity(intent);
        }
        odabirGrupa = findViewById(R.id.odabirGrupa)
        odabirPredmet = findViewById(R.id.odabirPredmet)
        odabirGodina = findViewById(R.id.odabirGodina)

        configureSpinnerGodina();
    }

    private fun configureSpinnerGrupa() {
        var nazivi = mutableListOf<String>();
        var adapter: ArrayAdapter<String>

            var grupe = `Z-GrupaRepository`.getGroupsByPredmet(odabirPredmet.selectedItem.toString())
            for (grupa in grupe) {
                nazivi.add(grupa.naziv);
            }

            adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,nazivi);


        odabirGrupa.adapter = adapter

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

    private fun configureSpinnerPredmet() {
        var nazivi = mutableListOf<String>();
        /*var predmeti = PredmetRepository.getPredmetsByGodina(odabirGodina.selectedItem.toString().toInt())
        for (predmet in predmeti) {
            nazivi.add(predmet.naziv);
        }
        var adapter : ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,nazivi);
        odabirPredmet.adapter = adapter
        odabirPredmet.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                odabirGrupa.isEnabled = isPredmetSelected()
                configureSpinnerGrupa()
            }

        }

         */
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
        var adapter : ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,arraySpinner);
        odabirGodina.adapter = adapter

        odabirGodina.setSelection(getIndex(odabirGodina, lastSelectedYear));

        odabirGodina.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                configureSpinnerPredmet()
                odabirGrupa.isEnabled = odabirPredmet.selectedItem != null
            }

        }
    }
}