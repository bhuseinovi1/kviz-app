package ba.etf.rma21.projekat.data.staticData

import ba.etf.rma21.projekat.data.modelsOLD.Kviz
import java.util.*

fun mojiKvizovi() : List<Kviz> {
    return kvizovi().filter { (it.nazivPredmeta == "VI" && it.naziv=="Kviz 2") }
}

fun kvizovi() : List<Kviz> {

    return listOf(
        Kviz("Kviz 7","VI",Date(2021-1900,4,20), Date(2021-1900,4,20),null,5,"Pon 10:30",null),
        Kviz("Kviz 6","VI",Date(2021-1900,4,12),Date(2021-1900,4,12),null,5,"Pon 10:30",null),
        Kviz("Kviz 5","VI",Date(2021-1900,3,22),Date(2021-1900,3,22),Date(2021-1900,3,22),5,"Pon 10:30",2.1F),
        Kviz("Kviz 4","VI",Date(2021-1900,3,15),Date(2021-1900,3,15),Date(2021-1900,3,15),5,"Pon 10:30",1.8F),
        Kviz("Kviz 3","VI",Date(2021-1900,3,8),Date(2021-1900,3,8),Date(2021-1900,3,8),5,"Pon 10:30",2.1F),
        Kviz("Kviz 2","VI",Date(2021-1900,3,1),Date(2021-1900,3,1),Date(2021-1900,3,1),5,"Pon 10:30",1.5F),
        Kviz("Kviz 1","VI",Date(2021-1900,2,25),Date(2021-1900,2,25),Date(2021-1900,2,25),5,"Pon 10:30",2F),

        Kviz("Kviz 6","ARM",Date(2021-1900,4,20), Date(2021-1900,4,20),null,5,"Pon 14:00",null),
        Kviz("Kviz 5","ARM",Date(2021-1900,4,12),Date(2021-1900,4,12),Date(2021-1900,4,12),5,"Pon 14:00",1F),
        Kviz("Kviz 4","ARM",Date(2021-1900,3,22),Date(2021-1900,3,22),Date(2021-1900,3,22),5,"Pon 14:00",0.6F),
        Kviz("Kviz 3","ARM",Date(2021-1900,3,15),Date(2021-1900,3,15),null,5,"Pon 14:00",null),
        Kviz("Kviz 2","ARM",Date(2021-1900,3,8),Date(2021-1900,3,8),Date(2021-1900,3,8),5,"Pon 14:00",0.8F),
        Kviz("Kviz 1","ARM",Date(2021-1900,3,1),Date(2021-1900,3,1),Date(2021-1900,3,1),5,"Pon 14:00",1F),

        Kviz("Projekat MWA","SI",Date(2021-1900,2,1),Date(2021-1900,5,7),Date(2021-1900,5,6),300,"Pon 21:00",26F),
        Kviz("Projekat 2","SI",Date(2021-1900,2,1),Date(2021-1900,5,7),null,200,"Pon 21:00",null),

        Kviz("Zadaća 1","RA",Date(2021-1900,2,24),Date(2021-1900,2,24),Date(2021-1900,2,24),30,"Čet 15:00",5F),
        Kviz("Zadaća 2","RA",Date(2021-1900,2,31),Date(2021-1900,2,31),Date(2021-1900,2,31),30,"Čet 15:00",4.5F),

        // u toku, urađen -> plava -> rad -> init. početni kvizovi
        Kviz("Kviz: JSON","RPR", Date(2021-1900,4,14),Date(2021-1900,5,18),Date(2021-1900,5,16),2,"Grupa1",3.5F),

        // u toku, urađen -> plava -> rad
        Kviz("Kviz 2","RMA", Date(2021-1900,4,14),Date(2021-1900,5,18),Date(2021-1900,5,16),2,"Grupa1",3.5F),
        // prošao, urađen -> plava -> rad
        Kviz("Kviz 3","RMA", Date(2019-1900,4,14),Date(2019-1900,5,16),Date(2019-1900,7,12),2,"Grupa1",2.1F),
        /*
        Kviz("Kviz 4","RMA", Date(2018-1900,4,14),Date(2021-1900,5,16),Date(2021,5,16),2,"Grupa1",null),
        Kviz("Kviz 5","RMA", Date(2022-1900,4,14),Date(2021-1900,5,16),Date(2021,5,16),2,"Grupa1",null),
        Kviz("Kviz 1","DM", Date(2000-1900,4,14),Date(2021-1900,5,16),Date(2021,5,16),2,"Grupa1",null),

         */
        // u budućnosti, neaktivan, nije urađen -> žuta -> pocetak
        Kviz("Kviz 2","DM", Date(2024-1900,4,14),Date(2024-1900,5,16),null,2,"Grupa1",null),
        /*
        Kviz("Kviz 3","DM", Date(2016-1900,4,14),Date(2021-1900,5,16),Date(2021,5,16),2,"Grupa1",null),
        Kviz("Kviz 4","DM", Date(2017-1900,4,14),Date(2021-1900,5,16),Date(2021,5,16),2,"Grupa1",null),
        Kviz("Kviz 1","ARM", Date(2016-1900,4,14),Date(2021-1900,5,16),Date(2021,5,16),2,"Grupa1",null),
        Kviz("Kviz 2","ARM", Date(2015-1900,4,14),Date(2021-1900,5,16),Date(2021,5,16),2,"Grupa1",null),
        Kviz("Kviz 1","OBP", Date(2017-1900,4,14),Date(2021-1900,5,16),Date(2021,5,16),2,"Grupa1",null),
        Kviz("Kviz 2","OBP", Date(2018-1900,4,14),Date(2021-1900,5,16),Date(2021,5,16),2,"Grupa1",null),

         */
        // prošao, neurađen -> crvena -> kraj
        Kviz("Kviz 3","OBP", Date(2002-1900,4,14),Date(2002-1900,5,16),null,2,"Grupa1",null),
        // u toku, nije urađen -> zelena -> kraj
        Kviz("Kviz 1","RMA", Date(2021-1900,4,14),Date(2021-1900,4,17),null,2,"Grupa 12:00",null),
        Kviz("Kviz proba","RMA", Date(2021-1900,4,14),Date(2021-1900,4,17),null,2,"Grupa 12:00",null)
    )
}