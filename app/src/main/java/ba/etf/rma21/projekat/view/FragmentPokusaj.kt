package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje
import com.google.android.material.navigation.NavigationView

class FragmentPokusaj(val pitanja: List<Pitanje>?, val idPokusaja: Int, val idKviza: Int,val predan: String) : Fragment() {

    private lateinit var navigacijaPitanja: NavigationView
    private lateinit var framePitanje: FrameLayout

    private fun openFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if (transaction != null) {
            transaction.replace(R.id.framePitanje, fragment)
        }
        if (transaction != null) {
            transaction.addToBackStack(null)
        }
        if (transaction != null) {
            transaction.commit()
        }
    }

    /*
    private val mOnItemClickListener = MenuItem.OnMenuItemClickListener {
        menuItem ->

    }

     */

    /*
    private val mOnNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.pitanje1 -> {
                val pitanjeFragment = FragmentPitanje.newInstance(pitanja?.get(0),idPokusaja)
                openFragment(pitanjeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.pitanje2 -> {
                val pitanjeFragment = FragmentPitanje.newInstance(pitanja?.get(1),idPokusaja)
                openFragment(pitanjeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.pitanje3 -> {
                val pitanjeFragment = FragmentPitanje.newInstance(pitanja?.get(2),idPokusaja)
                openFragment(pitanjeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.pitanje4 -> {
                val pitanjeFragment = FragmentPitanje.newInstance(pitanja?.get(3),idPokusaja)
                openFragment(pitanjeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.pitanje5 -> {
                val pitanjeFragment = FragmentPitanje.newInstance(pitanja?.get(4),idPokusaja)
                openFragment(pitanjeFragment)
                return@OnNavigationItemSelectedListener true
            }
            /*
            R.id. -> {
                var toast = Toast.makeText(context,"NOVI ITEM DODAN",Toast.LENGTH_LONG)
                toast.show()
            }

             */


        }
        false
    }

     */



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        /*val toast = Toast.makeText(context,"POKUSAAAAj" + idPokusaja.toString(), Toast.LENGTH_SHORT)
        toast.show();

         */
        var view: View = inflater.inflate(R.layout.pokusaj_fragment, container, false)

        navigacijaPitanja = view.findViewById(R.id.navigacijaPitanja)

        // proba
        //navigacijaPitanja.menu.add(0, Menu.FIRST,Menu.FIRST,"nova").setTitle("8")
        //navigacijaPitanja.menu.add(0,8,0,"nova")
        if (pitanja != null) {
            for (i in 1..(pitanja.size)) {
                navigacijaPitanja.menu.add(0,Menu.FIRST,Menu.FIRST,i.toString()).setOnMenuItemClickListener {
                    val pitanjeFragment = FragmentPitanje.newInstance(i-1,pitanja?.get(i-1),idPokusaja,idKviza)
                    openFragment(pitanjeFragment)
                    false
                }
            }
        }


        //navigacijaPitanja.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        framePitanje = view.findViewById(R.id.framePitanje)

        val pitanjeFragment = FragmentPitanje.newInstance(0,pitanja?.get(0),idPokusaja,idKviza)
        openFragment(pitanjeFragment)
        /*
        var nazivPredmeta = arguments?.getString("nazivPredmeta").toString()
        var nazivGrupe = arguments?.getString("nazivGrupe").toString()
        var naziv = arguments?.getString("naziv").toString()


         */

        return view
    }

    companion object {
        fun newInstance(pitanja: List<Pitanje>?,idPokusaja: Int,idKviza:Int, predan: String): FragmentPokusaj {

            val fragmentPokusaj = FragmentPokusaj(pitanja,idPokusaja,idKviza,predan)
            return fragmentPokusaj
        }
    }

    /*
    companion object {
        fun newInstance(kviz: Kviz): FragmentPokusaj = FragmentPokusaj(pitanja).apply {
            arguments = Bundle().apply {
                putString("naziv", kviz.naziv)
                putString("nazivGrupe",kviz.nazivGrupe)
                putString("nazivPredmeta",kviz.nazivPredmeta)
            }
        }
    }

     */
}