package ba.etf.rma21.projekat.data.modelsOLD;

import java.util.List;

import ba.etf.rma21.projekat.data.staticData.KvizStaticDataKt;

public class Korisnik {
    private static List<Kviz> kvizovi = KvizStaticDataKt.mojiKvizovi();
    /*public Korisnik_v2() {
        kvizovi.addAll(KvizRepository.Companion.getMyKvizes());
    }

     */
    public static void dodajKviz(Kviz kviz) {
        if(!kvizovi.contains(kviz))
            kvizovi.add(kviz);
    }
    public static void dodajKvizove(List<Kviz> Kvizovi) {
        for(Kviz k : Kvizovi) {
            if(!kvizovi.contains(k)) {
                kvizovi.add(k);
            }
        }
        //kvizovi.addAll(Kvizovi);
    }
    public static List<Kviz> dajKvizove() {
        return kvizovi;
    }
}
