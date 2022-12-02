@file:Suppress("PackageDirectoryMismatch")
package ba.etf.rma21.projekat

class UtilTestClass {
/*
    companion object {
        fun hasItemCount(n: Int) = object : ViewAssertion {
            override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }
                Assert.assertTrue("View nije tipa RecyclerView", view is RecyclerView)
                var rv: RecyclerView = view as RecyclerView
                ViewMatchers.assertThat(
                    "GetItemCount RecyclerView broj elementa: ",
                    rv.adapter?.itemCount,
                    CoreMatchers.`is`(n)
                )
            }

        }
        fun withDrawable(@DrawableRes id: Int) = object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("ImageView with drawable same as drawable with id $id")
            }

            override fun matchesSafely(view: View): Boolean {
                val context = view.context
                val expectedBitmap = context.getDrawable(id)?.toBitmap()

                return view is ImageView && view.drawable.toBitmap().sameAs(expectedBitmap)
            }
        }
        fun itemTest(id: Int, k: Kviz) {
            Espresso.onView(ViewMatchers.withId(R.id.listaKvizova)).perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    CoreMatchers.allOf(
                        ViewMatchers.hasDescendant(ViewMatchers.withText(k.naziv)),
                        ViewMatchers.hasDescendant(ViewMatchers.withText(k.nazivPredmeta))
                    )
                )

            )
        }
        fun itemTestNotVisited(id: Int, k: Kviz,posjeceni:MutableList<Int>) {
            Espresso.onView(ViewMatchers.withId(R.id.listaKvizova)).perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    firstNotVisited(posjeceni,
                    CoreMatchers.allOf(
                        ViewMatchers.hasDescendant(ViewMatchers.withText(k.naziv)),
                        ViewMatchers.hasDescendant(ViewMatchers.withText(k.nazivPredmeta))
                    )
                )
                )
            )
        }
        fun firstNotVisited(posjeceni: MutableList<Int>,matcher: Matcher<View>) = object: TypeSafeMatcher<View>(){
            override fun describeTo(description: Description) {
                description.appendText("Više puta machan isti objekat")
            }
            override fun matchesSafely(item: View): Boolean {
                val uqCode = System.identityHashCode(item)
                val posjecen = posjeceni.indexOf(uqCode)>-1
                if (!posjecen && matcher.matches(item)) {
                    posjeceni.add(uqCode)
                    return true;
                }
                return false
            }

        }
        fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {
            checkNotNull(itemMatcher)
            return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("has item at position $position: ")
                    itemMatcher.describeTo(description)
                }

                override fun matchesSafely(view: RecyclerView): Boolean {
                    val viewHolder = view.findViewHolderForAdapterPosition(position)
                            ?: // has no item on such position
                            return false
                    return itemMatcher.matches(viewHolder.itemView)
                }
            }
        }
    }

     */
}