package com.example.testingexample.model

import com.example.testingexample.R

object Books {

    val SHERLOCK_HOLMES = Data(
        0,
        "THe Adventures of Sherlock Holmes",
        R.drawable.sh,
        arrayListOf("Sir Arthur Conan Doyle"),
        "The first story in the collection The Adventures of Sherlock Holmes, this may not be Doyle's longest tale, but it has left quite a lasting impression as the only piece to reference “The Woman” Irene Adler."
    )

    val GOOD_OMENS = Data(
        1,
        "Good Omens",
        R.drawable.gm,
        arrayListOf("Neil Gaiman, Terry Pratchett"),
        "the funniest book ever written about Armageddon. Desperate to save humanity and the world they love, a snarky demon and a fastidious angel decide to join forces and stop the upcoming Rapture—in between their witty quips about pop culture."
    )
}