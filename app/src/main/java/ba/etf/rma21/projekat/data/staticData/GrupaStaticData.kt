package ba.etf.rma21.projekat.data.staticData

import ba.etf.rma21.projekat.data.modelsOLD.Grupa

fun grupe() : List<Grupa> {
    return listOf(
        // treca godina -> sesti semestar
        Grupa("Pon 9:30","VI"),
        Grupa("Pon 10:30","VI"),
        Grupa("Pon 17:30","VI"),
        Grupa("Uto 18:00","VI"),
        Grupa("Uto 12:00","VI"),
        Grupa("Uto 13:30","VI"),
        Grupa("Sri 12:00","VI"),
        Grupa("Čet 17:30","VI"),
        Grupa("Pet 10:30","VI"),
        Grupa("Pet 12:00","VI"),
        Grupa("Pet 15:00","VI"),

        Grupa("Pon 14:00","ARM"),
        Grupa("Uto 12:00","ARM"),
        Grupa("Uto 14:00","ARM"),
        Grupa("Čet 12:00","ARM"),

        Grupa("Pon 21:00","SI"),

        Grupa("Sri 17:00","PIS"),
        Grupa("Sri 17:15","PIS"),
        Grupa("Sri 17:30","PIS"),
        Grupa("Sri 17:45","PIS"),
        Grupa("Sri 18:00","PIS"),


        // druga godina -> četvrti semestar
        Grupa("Pon 9:00","RMA"),
        Grupa("Pon 10:30","RMA"),
        Grupa("Pon 14:00","RMA"),
        Grupa("Uto 12:00","RMA"),
        Grupa("Uto 13:30","RMA"),
        Grupa("Čet 13:00","RMA"),

        Grupa("Pon 10:00","OOAD"),
        Grupa("Pon 11:30","OOAD"),
        Grupa("Uto 12:00","OOAD"),
        Grupa("Sri 15:00","OOAD"),
        Grupa("Čet 12:00","OOAD"),

        Grupa("Čet 15:00","RA"),
        Grupa("Pet 12:00","RA"),

        Grupa("Uto 13:00","AFJ"),
        Grupa("Sri 15:00","AFJ"),
        Grupa("Pet 13:00","AFJ"),

        Grupa("Pon 18:00","ORM"),
        Grupa("Uto 18:00","ORM"),
        Grupa("Sri 18:00","ORM"),

        Grupa("Pon 15:00","ORM"),
        Grupa("Pon 16:00","ORM"),
        Grupa("Uto 13:30","ORM"),
        Grupa("Uto 15:00","ORM"),
        Grupa("Uto 18:00","ORM"),
        Grupa("Pet 9:00","ORM"),
        Grupa("Pet 10:30","ORM"),


        // prva godina -> drugi semestar
        Grupa("Pon 15:00","MLTI"),
        Grupa("Pon 16:00","MLTI"),
        Grupa("Uto 13:30","MLTI"),
        Grupa("Uto 15:00","MLTI"),
        Grupa("Uto 18:00","MLTI"),
        Grupa("Pet 9:00","MLTI"),
        Grupa("Pet 10:30","MLTI"),

        Grupa("Uto 14:00","VIS"),
        Grupa("Čet 14:00","VIS"),

        Grupa("Uto 22:00","IM2"),
        Grupa("Čet 23:00","IM2"),

        Grupa("Pon 9:00","TP"),
        Grupa("Pon 11:00","TP"),
        Grupa("Pon 13:00","TP"),
        Grupa("Pon 15:00","TP"),
        Grupa("Uto 11:00","TP"),
        Grupa("Uto 13:00","TP"),
        Grupa("Uto 15:00","TP"),
        Grupa("Sri 14:00","TP"),
        Grupa("Sri 15:00","TP"),
        Grupa("Sri 17:00","TP"),
        Grupa("Čet 14:00","TP"),
        Grupa("Čet 15:00","TP"),
        Grupa("Pet 12:00","TP"),
        Grupa("Pet 13:00","TP"),
        Grupa("Pet 14:00","TP"),

        Grupa("Pon 9:00","OS"),
        Grupa("Pon 15:00","OS"),
        Grupa("Pon 17:00","OS"),
        Grupa("Uto 13:30","OS"),
        Grupa("Uto 15:00","OS"),
        Grupa("Sri 17:00","OS")
        /*
        Grupa("Grupa 12:00","DM"),
        Grupa("Grupa 13:00","DM"),
        Grupa("Grupa 14:00","DM"),
        Grupa("Grupa 11:00","OBP"),
        Grupa("Grupa 19:00","OBP"),
        Grupa("Grupa 9:00","WT"),
        Grupa("Grupa 12:00","WT"),
        Grupa("Grupa 15:00","WT"),
        Grupa("Grupa 16:00","WT"),
        Grupa("Grupa 17:00","WT"),
        Grupa("Grupa 15:30","IM1"),
        Grupa("Grupa 17:00","IM1"),
        Grupa("Grupa 10:00","UUP"),
        Grupa("Grupa 17:00","TP"),
        Grupa("Grupa 17:00","LAG")

         */
    )
}