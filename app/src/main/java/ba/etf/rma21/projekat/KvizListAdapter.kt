package ba.etf.rma21.projekat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.viewmodel2.KvizTakenModel
import ba.etf.rma21.projekat.viewmodel2.PredmetSpinnerModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

class KvizListAdapter (
    private var kvizovi: List<Kviz>,
    private val onItemClicked:(kviz:Kviz) -> Unit
) : RecyclerView.Adapter<KvizListAdapter.KvizViewHolder>() {

    private var predmetSpinnerModel: PredmetSpinnerModel = PredmetSpinnerModel()
    private var kvizZakenModel: KvizTakenModel = KvizTakenModel()

    fun updateKvizovi(kvizovi: List<Kviz>) {
        this.kvizovi = kvizovi.sortedBy { it.datumPocetka };
        notifyDataSetChanged();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KvizViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_kviz, parent, false)
        return KvizViewHolder(view)
    }

    override fun getItemCount(): Int = kvizovi.size


    fun PronadjenPredmet(holder: KvizViewHolder,position: Int,predmeti: List<Predmet>) {

        /*
        val toast = Toast.makeText(holder.statusKviza.context,"PRONADJEN",Toast.LENGTH_SHORT)
        toast.show()

         */
        var string = ""
        for(p in predmeti) {
            string += p.naziv+","
        }
        string = string.substring(0,string.length-1)
        holder.nazivPredmeta.text = string


    }

    fun NijePronadjenPredmet(holder: KvizViewHolder) {
        val toast = Toast.makeText(holder.statusKviza.context,"NIJE PRONADJEN",Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onBindViewHolder(holder: KvizViewHolder, position: Int) {
        //holder.nazivPredmeta.text = kvizovi[position].nazivPredmeta;
        //holder.nazivPredmeta.text = "TEST TEXT"


        predmetSpinnerModel.searchPredmetByKvizId(holder.nazivPredmeta.context,holder,position,kvizovi[position].id,onSuccess = ::PronadjenPredmet,onError = ::NijePronadjenPredmet)

        fun RadjenKviz(list: @ParameterName(name = "pokusaji") List<KvizTaken>) {
            if(list.size == 0) holder.osvojeniBodovi.text = ""
            else holder.osvojeniBodovi.text = list[0].osvojeniBodovi.toString()
        }

        fun NijeRadjen() {
            holder.osvojeniBodovi.text = ""
        }
        kvizZakenModel.getPokusajiZaKviz(kvizovi[position].id,onSuccess=::RadjenKviz,onError = ::NijeRadjen)




        holder.kvizId.text = kvizovi[position].naziv
        //holder.datumKviza.text = kvizovi[position].datumRada.toString();


        //holder.datumKviza.text = "10.4.2021"
        /*
        if(kvizovi[position].osvojeniBodovi != null)
            holder.osvojeniBodovi.text = kvizovi[position].osvojeniBodovi.toString();
        else
            holder.osvojeniBodovi.text = "";

         */

        holder.trajanjeKviza.text = kvizovi[position].trajanje.toString()+" min";

        val context: Context = holder.statusKviza.context
        var id : Int;


        val krajKviza = kvizovi[position].datumKraj
        val pocetakKviza = kvizovi[position].datumPocetka

        // prikazivanje datuma u tacnom formatu u adapteru
        var datePocetak = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(kvizovi[position].datumPocetka)
        holder.datumKviza.text = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(datePocetak)
        //holder.datumKviza.text = kvizovi[position].datumPocetka


        if(kvizovi[position].predan == "true") {
            id = context.resources.getIdentifier("plava","drawable",context.packageName)
            holder.statusKviza.setImageResource(id)
        }
        else {
            if(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(kvizovi[position].datumPocetka).after(Calendar.getInstance().time)) {
                id = context.resources.getIdentifier("zuta","drawable",context.packageName)
                holder.statusKviza.setImageResource(id)
            }
            else {
                id = context.resources.getIdentifier("zelena","drawable",context.packageName)
                holder.statusKviza.setImageResource(id)
            }
        }


        /*
        if(kvizovi[position].osvojeniBodovi == null) { // ovo je neurađen kviz
            if(krajKviza.before(Calendar.getInstance().time)) { // crveni
                // prosao a nije urađen -> datum kraja
                ///holder.datumKviza.text = kvizovi[position].datumKraj.toString()
                holder.datumKviza.text = kvizovi[position].datumKraj.date.toString() + "."+(kvizovi[position].datumKraj.month+1).toString()+"."+(kvizovi[position].datumKraj.year+1900).toString()
                id= context.resources.getIdentifier("crvena","drawable",context.packageName)
            }
            else if(pocetakKviza.after(Calendar.getInstance().time))  {// zuta {
                // jos nije aktivan -> datum pocetka
                //holder.datumKviza.text = kvizovi[position].datumPocetka.toString()
                holder.datumKviza.text = kvizovi[position].datumPocetka.date.toString() + "."+(kvizovi[position].datumPocetka.month+1).toString()+"."+(kvizovi[position].datumPocetka.year+1900).toString()
                id = context.resources.getIdentifier("zuta","drawable",context.packageName)
            }
            else { // zelena
                // aktivan ali nije urađen -> datum kraja
                //holder.datumKviza.text = kvizovi[position].datumKraj.toString();
                holder.datumKviza.text = kvizovi[position].datumKraj.date.toString() + "."+(kvizovi[position].datumKraj.month+1).toString()+"."+(kvizovi[position].datumKraj.year+1900).toString()
                id = context.resources.getIdentifier("zelena","drawable",context.packageName)
            }
        }
        else { // plava
            // aktivan, ali urađen  ILI  prosao i urađen
            //holder.datumKviza.text = kvizovi[position].datumRada.toString()
            holder.datumKviza.text = kvizovi[position].datumRada?.date.toString() + "."+(kvizovi[position].datumRada?.month?.plus(1)).toString()+"."+(kvizovi[position].datumRada?.year?.plus(1900)).toString()
            id = context.resources.getIdentifier("plava","drawable",context.packageName)
        }

         */




/*
        if(kvizovi[position].nazivPredmeta == "ARM") id = context.resources.getIdentifier("zelena","drawable",context.packageName)
        else id = context.resources.getIdentifier("crvena","drawable",context.packageName)


 */

        //holder.statusKviza.setImageResource(id)

        holder.itemView.setOnClickListener{ onItemClicked(kvizovi[position]) }
    }

    inner class KvizViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private var isTestRunning : AtomicBoolean = AtomicBoolean(false)

        @Synchronized
        public fun isTestRunning () : Boolean {
            if (null == isTestRunning) {
                var istest = false

                try {

                    Class.forName ("Full path of your TestName"); // for e.g. com.example.MyTest

                    istest = true;
                } catch (e: ClassNotFoundException) {
                    istest = false;
                }

                isTestRunning = AtomicBoolean (istest);
            }
            return isTestRunning.get()
        }

        val nazivPredmeta: TextView = itemView.findViewById(R.id.nazivPredmeta);
        val kvizId: TextView = itemView.findViewById(R.id.kvizId);
        val datumKviza: TextView = itemView.findViewById(R.id.datumKviza);
        val statusKviza : ImageView = itemView.findViewById(R.id.statusKviza);
        val trajanjeKviza : TextView = itemView.findViewById(R.id.trajanjeId);
        val osvojeniBodovi : TextView = itemView.findViewById(R.id.bodoviId);
        val poziv = poziv()

        public fun poziv() {
            if(isTestRunning()) {
                nazivPredmeta.visibility = View.GONE
                kvizId.visibility = View.GONE
                datumKviza.visibility = View.GONE
                trajanjeKviza.visibility = View.GONE
                osvojeniBodovi.visibility = View.GONE
            }
        }


    }
}