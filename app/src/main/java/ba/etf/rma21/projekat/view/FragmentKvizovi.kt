package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.KvizListAdapter
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.repositories.DBRepository
import ba.etf.rma21.projekat.viewmodel2.*

class FragmentKvizovi :Fragment() {

    private lateinit var kvizoviRecycler: RecyclerView
    private lateinit var kvizoviAdapter: KvizListAdapter
    private lateinit var filterKvizova: Spinner
    //private lateinit var upisDugme: FloatingActionButton
    private var lastSelectedYear : String = "2"

    private lateinit var kvizListViewModel : KvizListViewModel
    private lateinit var grupaSpinnerModel : GrupaSpinnerModel
    private lateinit var pitanjaModel: PitanjaModel
    private lateinit var kvizTakenModel: KvizTakenModel
    private lateinit var DBModel: DBModel

    private var idPokusaja = 0

    fun onSuccessPokusaj(pitanja: List<Pitanje>,kvizTakenId: Int,kvizId: Int,zapocet: Boolean = false) {

        // ako je zapocet, provjeri je li predan
        fun KvizJePredan(predan: String) {
            val toast = Toast.makeText(context,"Pokusaj broj:" + kvizTakenId.toString() + " pokrenut.",Toast.LENGTH_SHORT)
            toast.show();
            idPokusaja = kvizTakenId

            // VEOMA BITNO, NE SLATI NOVI idPokusaja ukoliko se otvara kviz koji je već otvoren, ovo se može ispitati pomocu student/{id}/kviztaken

            var pokusajFragment : FragmentPokusaj = FragmentPokusaj.newInstance(pitanja,idPokusaja,kvizId,predan) // PitanjeKvizRepository.getPitanja(kviz.naziv,kviz.nazivPredmeta))
            openFragment(pokusajFragment)
        }

        // bukvalno sam ponovo pristupio bazi iako sam imao kviz vec tu, al nema veze
        kvizListViewModel.getKvizById(kvizId,onSuccess = ::KvizJePredan,onError=  {})


        /*
        val toast = Toast.makeText(context,"Pokusaj " + kvizTakenId.toString() + " pokrenut",Toast.LENGTH_SHORT)
        toast.show();
        idPokusaja = kvizTakenId

        // VEOMA BITNO, NE SLATI NOVI idPokusaja ukoliko se otvara kviz koji je već otvoren, ovo se može ispitati pomocu student/{id}/kviztaken

        var pokusajFragment : FragmentPokusaj = FragmentPokusaj.newInstance(pitanja,idPokusaja,kvizId) // PitanjeKvizRepository.getPitanja(kviz.naziv,kviz.nazivPredmeta))
        openFragment(pokusajFragment)

         */
    }

    fun onErrorPokusaj() {
        val toast = Toast.makeText(context,"Nismo uspjeli pokrenuti pokusaj",Toast.LENGTH_SHORT)
        toast.show();
    }


    fun zapocet(pitanja: List<Pitanje>, idKviza:Int,idPokusaja: Int) {
        val toast = Toast.makeText(context,"Dobrodošli nazad na ovaj kviz",Toast.LENGTH_SHORT)
        toast.show();
        // kviz je bio zapocet, i ne treba mijenjati idPokusaja
        onSuccessPokusaj(pitanja,idPokusaja,idKviza,true)
    }

    fun nijeZapocet(pitanja: List<Pitanje>, idKviza:Int) {
        val toast = Toast.makeText(context,"Dobrodošli u pokušaj rješavanja ovog kviza",Toast.LENGTH_SHORT)
        toast.show();
        // kviz nije zapocet, te se treba generisati novi idPokusaja
        kvizTakenModel.zapocni(pitanja,idKviza,onSuccess = ::onSuccessPokusaj, onError = ::onErrorPokusaj)
    }

    fun onSuccessPitanja(kvizId: Int,pitanja:List<Pitanje>) {
        // u biti, ovdje se treba ispitati da li je pokrenut neki pokusaj za kviz sa id-om id
        kvizTakenModel.provjeriJeLiZapocet(pitanja,kvizId,onSuccess = ::zapocet, onError = ::nijeZapocet)

        //kvizTakenModel.zapocni(pitanja,kvizId,onSuccess = ::onSuccessPokusaj, onError = ::onErrorPokusaj)

        //val toast = Toast.makeText(context,"Pitanja uspješno dobavljena",Toast.LENGTH_SHORT)
        //toast.show();


        /*var pokusajFragment : FragmentPokusaj = FragmentPokusaj.newInstance(pitanja,idPokusaja) // PitanjeKvizRepository.getPitanja(kviz.naziv,kviz.nazivPredmeta))
        openFragment(pokusajFragment)

         */
    }

    fun onErrorPitanja() {
        val toast = Toast.makeText(context,"Nismo uspjeli dobaviti pitanja",Toast.LENGTH_SHORT)
        toast.show();
    }

    private fun showKvizDetails(kviz: Kviz) {
        //val toast = Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT)
        //toast.show()
        MainActivity.trenutniKviz = kviz // ovo je iskljucivo zbog ČUVANJA KVIZA I PRIKAZA STATISTIKE

        pitanjaModel.search(kviz.id,onSuccess = ::onSuccessPitanja,onError = ::onErrorPitanja)

        /*
        var pokusajFragment : FragmentPokusaj = FragmentPokusaj.newInstance(PitanjeKvizRepository.getPitanja(kviz.id)) // PitanjeKvizRepository.getPitanja(kviz.naziv,kviz.nazivPredmeta))
        openFragment(pokusajFragment)


         */


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


    private fun configureAdapter(view: View) {
        kvizoviRecycler = view.findViewById(R.id.listaKvizova);
        kvizoviRecycler.layoutManager = GridLayoutManager(activity, 2) // podešavanje Layouta

        kvizoviAdapter = KvizListAdapter(arrayListOf()) {
                kviz -> showKvizDetails(kviz)
        }

        kvizoviRecycler.adapter = kvizoviAdapter;

        /*
        kvizoviAdapter.updateKvizovi(KvizRepository.getMyKvizes());

         */
    }




    fun onSuccess(kvizovi:List<Kviz>) {
        // ovdje treba sada dobiti sve podatke koji nam nisu trenutno dostupni, a to su naziv predmeta, datum rada i naziv grupe

        /*
        var kvizoviZaDodati : MutableList<ba.etf.rma21.projekat.data.models.Kviz> = MutableList()

        for (k in kvizovi) {
            grupaSpinnerModel.searchGroupsByKvizId(k.id,onSuccess = onSuccessGrupa(),onError = onErrorGrupa())
        }


         */
        kvizoviAdapter.updateKvizovi(kvizovi)
    }

    fun onError() {
        val toast = Toast.makeText(context,"Nismo uspjeli dobaviti kvizove",Toast.LENGTH_SHORT)
        toast.show();
    }

    private fun configureSpinner(view: View) {
        val arraySpinner = arrayOf(
            "Svi moji kvizovi", "Svi kvizovi", "Urađeni kvizovi", "Budući kvizovi", "Prošli kvizovi (neurađeni)"
        )
        filterKvizova = view.findViewById(R.id.filterKvizova)

        //var adapter : ArrayAdapter<String> = ArrayAdapter<String>(activity?.applicationContext!!,android.R.layout.simple_spinner_dropdown_item,arraySpinner);
        var adapter : ArrayAdapter<String> = ArrayAdapter<String>(activity?.applicationContext!!,R.layout.spinner_item,arraySpinner);

        filterKvizova.adapter = adapter;
        filterKvizova.setSelection(1)

        filterKvizova.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (filterKvizova.selectedItem == "Svi kvizovi") {
                    //DBModel.updateuj()
                    kvizListViewModel.search(1,onSuccess = ::onSuccess, onError = ::onError)
                    //kvizoviAdapter.updateKvizovi(KvizRepository.getAll());
                }
                else if(filterKvizova.selectedItem == "Urađeni kvizovi") {
                    //Toast.makeText(context,"UPDAT-OVANJE",Toast.LENGTH_LONG).show()
                    DBModel.updateuj()
                    kvizListViewModel.searchDB(2,onSuccess = ::onSuccess, onError = ::onError)
                    //kvizListViewModel.search(2,onSuccess = ::onSuccess, onError = ::onError)
                    //kvizoviAdapter.updateKvizovi(KvizRepository.getDone());
                }
                else if(filterKvizova.selectedItem == "Budući kvizovi") {
                    //Toast.makeText(context,"UPDAT-OVANJE",Toast.LENGTH_LONG).show()
                    DBModel.updateuj()
                    kvizListViewModel.searchDB(3,onSuccess = ::onSuccess, onError = ::onError)
                    //kvizListViewModel.search(3,onSuccess = ::onSuccess, onError = ::onError)
                    //kvizoviAdapter.updateKvizovi(KvizRepository.getFuture());
                }
                else if(filterKvizova.selectedItem == "Prošli kvizovi (neurađeni)") {
                    DBModel.updateuj()
                    //kvizListViewModel.search(4,onSuccess = ::onSuccess, onError = ::onError)
                    //kvizoviAdapter.updateKvizovi(KvizRepository.getNotTaken());
                }
                else {
                    //Toast.makeText(context,"UPDAT-OVANJE",Toast.LENGTH_LONG).show()
                    DBModel.updateuj()
                    kvizListViewModel.searchDB(5,onSuccess = ::onSuccess, onError = ::onError)
                    //kvizListViewModel.search(5,onSuccess = ::onSuccess, onError = ::onError)
                    //kvizoviAdapter.updateKvizovi(KvizRepository.getMyKvizes());
                }
            }

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        MainActivity.trenutniKviz = null


        var view =  inflater.inflate(R.layout.kvizovi_fragment, container, false)
        kvizListViewModel = KvizListViewModel()
        pitanjaModel = PitanjaModel()
        kvizTakenModel = KvizTakenModel()
        DBModel = DBModel()

        configureAdapter(view)
        configureSpinner(view)
        //filterKvizova.setSelection(1)
        /*
        favoriteMovies = view.findViewById(R.id.favoriteMovies) // inicijalizacija RecyclerView
        favoriteMovies.layoutManager = GridLayoutManager(activity, 2) // podešavanje Layouta
        favoriteMoviesAdapter = MovieListAdapter(arrayListOf()) { movie -> showMovieDetails(movie) } // deklaracija adaptera
        favoriteMovies.adapter=favoriteMoviesAdapter // podešavanje adaptera
        favoriteMoviesAdapter.updateMovies(movieListViewModel.getFavoriteMovies())

         */
        return view;
    }
    companion object {
        fun newInstance(): FragmentKvizovi = FragmentKvizovi()
    }
}