package seki.com.re.hatenarssreader.presenter

import android.content.Intent

interface Navigator {
    fun goTo(intent: Intent)
}