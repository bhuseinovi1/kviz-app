package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.R

class FragmentPoruka : Fragment() {

    private lateinit var tvPoruka : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.poruka_fragment, container, false)
        tvPoruka = view.findViewById(R.id.tvPoruka)



        var novi_tekst = tvPoruka.text.toString().replace("grupa.naziv",arguments?.getString("grupa").toString()).replace("predmet.naziv",arguments?.getString("predmet").toString())
        if(arguments?.getString("predmet") == "") {
            novi_tekst = "Završili ste "+arguments?.getString("kviz").toString()+" sa tačnosti "+ arguments?.getString("grupa")!!.toFloat()
        }

        tvPoruka.text = novi_tekst;

        return view
    }

    companion object {
        fun newInstance(predmet:String,grupa:String,kvizName: String = ""): FragmentPoruka = FragmentPoruka().apply {
            arguments = Bundle().apply {
                putString("predmet", predmet)
                putString("grupa",grupa)
                putString("kviz",kvizName)
            }
        }
    }

}