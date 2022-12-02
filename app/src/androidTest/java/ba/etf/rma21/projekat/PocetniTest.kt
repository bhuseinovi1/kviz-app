@file:Suppress("PackageDirectoryMismatch")
package ba.etf.rma21.projekat

import androidx.test.ext.junit.runners.AndroidJUnit4

/*
import ba.etf.rma21.projekat.UtilTestClass.Companion.hasItemCount
import ba.etf.rma21.projekat.UtilTestClass.Companion.itemTest
import ba.etf.rma21.projekat.UtilTestClass.Companion.itemTestNotVisited

 */
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PocetniTest {

    /*
    @get:Rule
    val intentsTestRule = IntentsTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun postojiSveNaPocetnoj() {

        onView(withId(R.id.filterKvizova)).check(matches(isDisplayed()))
        onView(withId(R.id.listaKvizova)).check(matches(isDisplayed()))
        onView(withId(R.id.upisDugme)).check(matches(isDisplayed()))

        var listaOdabira = listOf<String>(
            "Svi moji kvizovi",
            "Svi kvizovi",
            "Urađeni kvizovi",
            "Budući kvizovi",
            "Prošli kvizovi"
        )

        for (odabir in listaOdabira) {
            onView(withId(R.id.filterKvizova)).perform(click())
            onData(allOf(Is(instanceOf(String::class.java)), Is(odabir))).perform(click())
        }

    }

    @Test
    fun popuniKvizoveGetDone() {

        onView(withId(R.id.filterKvizova)).perform(click())
        onData(allOf(Is(instanceOf(String::class.java)), Is("Svi moji kvizovi"))).perform(click())
        val kvizovi = KvizRepository.getMyKvizes()
        onView(withId(R.id.listaKvizova)).check(hasItemCount(kvizovi.size))
        for (kviz in kvizovi) {
            itemTest(R.id.listaKvizova, kviz)
        }

    }

    @Test
    fun godineTest() {
        onView(withId(R.id.upisDugme)).perform(click())
        var listaOdabira = listOf<String>("1", "2", "3", "4", "5")
        for (odabir in listaOdabira) {
            onView(withId(R.id.odabirGodina)).perform(click())
            onData(allOf(Is(instanceOf(String::class.java)), Is(odabir))).perform(click())
        }
    }

    @Test
    fun filtriranjeTest(){
        var listaOdabira = listOf<String>(
            "Svi moji kvizovi",
            "Svi kvizovi",
            "Urađeni kvizovi",
            "Budući kvizovi",
            "Prošli kvizovi"
        )
        var kolikoKvizova = 0
        for (odabir in listaOdabira) {
            onView(withId(R.id.filterKvizova)).perform(click())
            onData(allOf(Is(instanceOf(String::class.java)), Is(odabir))).perform(click())
            var kvizovi = emptyList<Kviz>()
            when(odabir){
                "Svi moji kvizovi" -> kvizovi=KvizRepository.getMyKvizes()
                "Svi kvizovi" -> kvizovi=KvizRepository.getAll()
                "Urađeni kvizovi" -> kvizovi=KvizRepository.getDone()
                "Budući kvizovi" -> kvizovi=KvizRepository.getFuture()
                "Prošli kvizovi" -> kvizovi=KvizRepository.getNotTaken()
            }
            kolikoKvizova+=kvizovi.size
            onView(withId(R.id.listaKvizova)).check(hasItemCount(kvizovi.size))
            var posjeceni:MutableList<Int> = mutableListOf()
            for (kviz in kvizovi) {
                itemTestNotVisited(R.id.listaKvizova, kviz,posjeceni)
            }
        }
        val ukupno = KvizRepository.getAll().size
        kolikoKvizova-=ukupno
        assertThat(kolikoKvizova, allOf(Is(greaterThan(0))))
    }


     */
}