package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.viewmodel2.DBModel
import ba.etf.rma21.projekat.viewmodel2.KvizTakenModel
import kotlinx.coroutines.withContext

class FragmentPitanje(val pitanje: Pitanje?,val idPokusaja: Int,val idKviza: Int) : Fragment() {

    private var brojPokusaja = 0
    private lateinit var tekstPitanja: TextView
    private lateinit var odgovoriLista: ListView

    private lateinit var kvizTakenModel : KvizTakenModel
    private lateinit var dbModel: DBModel




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // ako postoji odgovor na pitanje sa id-om pitanjeId, klikni ga
        // to mozemo provjeriti na nacin da ako nam
        // /student/{id}/kviz/{ktid}/odgovori



        var view: View = inflater.inflate(R.layout.pitanje_fragment, container, false)
        kvizTakenModel = KvizTakenModel()
        dbModel = DBModel()

        tekstPitanja = view.findViewById(R.id.tekstPitanja)
        if (pitanje != null) {
            tekstPitanja.text = pitanje.tekstPitanja
        }

        odgovoriLista = view.findViewById(R.id.odgovoriLista)
        var adapter : ArrayAdapter<String> = ArrayAdapter<String>(activity?.applicationContext!!,R.layout.spinner_item,pitanje!!.opcije);
        odgovoriLista.adapter = adapter
        odgovoriLista.onItemClickListener = object: AdapterView.OnItemClickListener {

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                // ovdje treba poslati upit
                fun OnSuccessOdgovor() {
                    val toast = Toast.makeText(context,"Odgovor zabilježen",Toast.LENGTH_SHORT)
                    toast.show();
                }
                fun onErrorOdgovor() {
                    val toast = Toast.makeText(context,"Odgovor nije zabilježen",Toast.LENGTH_SHORT)
                    toast.show();
                }
                /*
                val toast = Toast.makeText(context," "+ idPokusaja.toString()+" "+pitanje.id.toString()+" "+p2.toString(),Toast.LENGTH_SHORT)
                toast.show();

                 */
                // ovdje sada ne treba odmah rokat odgovori, nego postavljat u bazu odgovor
                //Toast.makeText(context,idPokusaja.toString()+" "+pitanje.toString()+" "+p2.toString(),Toast.LENGTH_LONG).show()
                //Toast.makeText(context,idKviza.toString(),Toast.LENGTH_LONG).show()

                fun dodanUBazu() {
                    Toast.makeText(context,"USPJEŠNO DODAN U BAZU!",Toast.LENGTH_LONG).show()
                    //dbModel.updateujOdgovor(idPokusaja,pitanje.id,idKviza)
                }
                dbModel.dodajOdgovorDB(idPokusaja,pitanje.id,p2,onSuccess = ::dodanUBazu, onError = {})

                //dbModel.updateujOdgovor(idPokusaja,pitanje.id,idKviza)


                // updatuj odgovor sa pitanje.id sa pokusaje


                //kvizTakenModel.odgovori(idPokusaja,pitanje.id,p2,onSuccess = ::OnSuccessOdgovor,onError = ::onErrorOdgovor)

                if(brojPokusaja == 0) {
                    if (p0 != null) {
                        //tekstPitanja.text = p0.getChildAt(p2).toString().toUpperCase()
                        var odgovor: View = p0.getChildAt(p2)
                        var tacan: View = p0.getChildAt(pitanje.tacan)
                        tacan.setBackgroundResource(R.color.zelena)
                        if (p2 != pitanje.tacan) {
                            // oboji menu item u crveno
                            odgovor.setBackgroundResource(R.color.crvena)
                        }
                        else {
                            // oboji menu item u zeleno
                        }
                        p0.isEnabled = false
                    }
                }

                brojPokusaja ++
            }
        }

        // daj odgovor iz baze gdje za idKviza
        fun PribavljeniOdgovorZaKlik(odgovor: List<Odgovor>) {
            //Toast.makeText(activity?.applicationContext!!,odgovor.toString(),Toast.LENGTH_LONG).show()
            if(odgovor.isNotEmpty()) {
                    odgovoriLista.performItemClick(odgovoriLista.getChildAt(odgovor[0].odgovoreno),odgovor[0].odgovoreno,odgovoriLista.getItemIdAtPosition(odgovor[0].odgovoreno))
            }
        }
        dbModel.dajOdgovoreZaKvizTakenAndPitanjeDB(idPokusaja,pitanje.id,onSuccess = ::PribavljeniOdgovorZaKlik,onError = {})




        //kvizTakenModel.dajOdgovore(idPokusaja,onSuccess = ::imaOdgovora,onError = ::nemaOdgovora)
        // ovdje simulirati klik


        return view
    }

    fun nemaOdgovora() {
        /*
        val toast = Toast.makeText(context,"NEMA ODGOVORA",Toast.LENGTH_SHORT)
        toast.show();

         */
    }

    fun imaOdgovora(odgovori: List<Odgovor>) {
        var clickOn = -1
        for(o in odgovori) {
            if (pitanje != null) {
                if (o.PitanjeId==pitanje.id && o.KvizTakenId==idPokusaja) {
                    clickOn = o.odgovoreno
                }
            }
        }

        // click on index
        if (pitanje != null && clickOn!=-1) {
            /*
            val toast = Toast.makeText(context,"IMA ODGOVORA"+ pitanje.tacan.toString(),Toast.LENGTH_SHORT)
            toast.show()

             */
            odgovoriLista.performItemClick(odgovoriLista.getChildAt(clickOn),clickOn,odgovoriLista.getItemIdAtPosition(clickOn))
            //odgovoriLista.adapter.getView(pitanje.tacan,null,null).performClick()
        }
        //odgovoriLista.performItemClick(pitanje.tacan)
    }

    companion object {
        fun newInstance(redniBrojPitanja: Int,pitanje: Pitanje?, idPokusaja: Int, idKviza: Int): FragmentPitanje {
            val fragmentPitanje = FragmentPitanje(pitanje,idPokusaja,idKviza).apply {
                arguments = Bundle().apply {
                    putInt("redniBrojPitanja",redniBrojPitanja)
                }
            }
            return fragmentPitanje
        }
    }
}