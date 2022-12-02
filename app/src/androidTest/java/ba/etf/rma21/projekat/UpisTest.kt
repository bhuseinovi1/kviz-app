@file:Suppress("PackageDirectoryMismatch")
package ba.etf.rma21.projekat

import androidx.test.ext.junit.runners.AndroidJUnit4
//import ba.etf.rma21.projekat.UtilTestClass.Companion.hasItemCount
//import ba.etf.rma21.projekat.UtilTestClass.Companion.itemTest
import org.junit.FixMethodOrder
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class UpisTest {

    /*
    @get:Rule
    val intentsTestRule = IntentsTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun A_upisTest() {

        Espresso.onView(ViewMatchers.withId(R.id.filterKvizova)).perform(ViewActions.click())
        Espresso.onData(
            CoreMatchers.allOf(
                CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                CoreMatchers.`is`("Svi moji kvizovi")
            )
        ).perform(ViewActions.click())
        val kvizoviPrije = KvizRepository.getMyKvizes()
        val kvizoviPrijeSize = kvizoviPrije.size
        Espresso.onView(ViewMatchers.withId(R.id.listaKvizova))
            .check(hasItemCount(kvizoviPrije.size))
        for (kviz in kvizoviPrije) {
            itemTest(R.id.listaKvizova, kviz)
        }
        Espresso.onView(ViewMatchers.withId(R.id.upisDugme)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.odabirGodina)).perform(ViewActions.click())
        val nedodjeljeniKvizovi = KvizRepository.getAll().minus(KvizRepository.getMyKvizes())
        val nedodjeljeniPredmeti = PredmetRepository.getAll().minus(PredmetRepository.getUpisani())

        var grupaVrijednost = ""
        var predmetNaziv = ""
        var godinaVrijednost = -1
        for (nk in nedodjeljeniKvizovi) {
            for (np in nedodjeljeniPredmeti) {
                if (nk.nazivPredmeta == np.naziv) {
                    grupaVrijednost = nk.nazivGrupe
                    godinaVrijednost = np.godina
                    predmetNaziv = np.naziv

                }
            }
        }
        ViewMatchers.assertThat(
            "Nema neupisanih predmeta sa kvizovima",
            godinaVrijednost,
            CoreMatchers.not(CoreMatchers.`is`(-1))
        )

        Espresso.onData(
            CoreMatchers.allOf(
                CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                CoreMatchers.`is`(godinaVrijednost.toString())
            )
        ).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.odabirPredmet)).perform(ViewActions.click())
        Espresso.onData(
            CoreMatchers.allOf(
                CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                CoreMatchers.`is`(predmetNaziv)
            )
        ).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.odabirGrupa)).perform(ViewActions.click())
        Espresso.onData(
            CoreMatchers.allOf(
                CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                CoreMatchers.`is`(grupaVrijednost)
            )
        ).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.dodajPredmetDugme)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.filterKvizova)).perform(ViewActions.click())
        Espresso.onData(
            CoreMatchers.allOf(
                CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                CoreMatchers.`is`("Svi moji kvizovi")
            )
        ).perform(ViewActions.click())
        val kvizoviPoslije = KvizRepository.getMyKvizes()

        Espresso.onView(ViewMatchers.withId(R.id.listaKvizova))
            .check(hasItemCount(kvizoviPoslije.size))
        for (kviz in kvizoviPoslije) {
            itemTest(R.id.listaKvizova, kviz)
        }

        ViewMatchers.assertThat(
            "Nije dodan kviz nakon upisanog predmeta ",
            kvizoviPrijeSize,
            CoreMatchers.`is`(Matchers.lessThan(kvizoviPoslije.size))
        )

    }

    @Test
    fun B_upisTest(){
        A_upisTest()
    }

     */
}