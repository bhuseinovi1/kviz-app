package ba.etf.rma21.projekat.data.staticData

import ba.etf.rma21.projekat.data.modelsOLD.Pitanje
import ba.etf.rma21.projekat.data.modelsOLD.PitanjeKviz

fun pitanjaKviz() : List<PitanjeKviz> {
    return listOf(
            PitanjeKviz(Pitanje("Pitanje 1","Dio neurona koji je zadužen za primanje signala se zove :", arrayListOf("Akson","Dendrit","Soma"),1),"Kviz 2"),
            PitanjeKviz(Pitanje("Pitanje 2","Koja komponenta od navedenih nije bila prijedlog Alana Turinga 1950 :", arrayListOf("Logika","Znanje","Učenje"),0),"Kviz 2"),
            PitanjeKviz(Pitanje("Pitanje 3","Jedan od načina za mjerenje dvije distribucije vjerovatnoće :", arrayListOf("SVM","Taylorova divergencija","Kullback-Leiber divergencija"),2),"Kviz 2"),
            PitanjeKviz(Pitanje("Pitanje 4","Funkcija h(hipoteza) koja odgovara svim tačkama iz nekog skupa je :", arrayListOf("Konstantna hipoteza","Konzistentna hipoteza","Konektivistička hipoteza"),1),"Kviz 2"),
            PitanjeKviz(Pitanje("Pitanje 5","Za koju verziju industrijske revolucije kažemo da briše granice između fizičke digitalne i biološke sfere:", arrayListOf("4.0","5.0","3.0"),0),"Kviz 2")
    )
}