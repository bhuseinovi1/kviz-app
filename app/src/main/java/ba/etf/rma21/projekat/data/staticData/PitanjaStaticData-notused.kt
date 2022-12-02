package ba.etf.rma21.projekat.data.staticData

import ba.etf.rma21.projekat.data.modelsOLD.Pitanje


fun pitanja() : List<Pitanje> {
    return listOf(
        Pitanje("Pitanje 1","Dio neurona koji je zadužen za primanje signala se zove:", arrayListOf("Akson","Dendrit","Soma"),1)
    )
}