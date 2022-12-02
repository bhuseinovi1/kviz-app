package ba.etf.rma21.projekat.viewmodel2

import android.content.Context
import android.widget.Toast
import ba.etf.rma21.projekat.BuildConfig
import ba.etf.rma21.projekat.data.models.Account
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.repositories.AccountRepository
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class AccountModel {
    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun ispisi() {
        scope.launch {
            AccountRepository.obrisiAccPodatke()
        }
    }

    fun getStudentaZaId(hash: String,onSuccess: (student: Account) -> Unit,
                             onError: () -> Unit) {
        scope.launch {
            val result = AccountRepository.getStudentaZaId(hash) // ovo je tipa GetPredmetResponse

            when(result) {
                is Account -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }

    fun postaviKljuc(payload: String,context: Context, onSuccess: (payload: String) -> Unit,
                     onError: () -> Unit) {
        scope.launch {
            //AccountRepository.postaviHash(BuildConfig.API_KEY)
            //AccountRepository.postaviHash(string)
            try {
                AccountRepository.setContext(context)
                //AccountRepository.setStudent(student)
                var result = AccountRepository.postaviHash(payload)
                //AccountRepository.upisiAccUBazu(context, Account(1,"",string))
                if(result) onSuccess.invoke("TRUE")
                else onSuccess.invoke("FALSE")
            }
            catch (error: Exception) {
                onError.invoke()
            }

        }
    }
}