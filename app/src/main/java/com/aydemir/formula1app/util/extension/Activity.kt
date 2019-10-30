package com.aydemir.formula1app.util.extension

import androidx.appcompat.app.AppCompatActivity
import com.aydemir.formula1app.R
import com.aydemir.formula1app.base.BaseFragment
import com.google.gson.reflect.TypeToken

fun AppCompatActivity.pushFragmentToContainer(
    fragment: BaseFragment<*>, addToStack: Boolean, animated: Boolean
) {
    val transaction = supportFragmentManager.beginTransaction()
    if (animated) {
        transaction.setCustomAnimations(
            R.anim.slide_in_right,
            0,
            0,
            R.anim.slide_out_right
        )
    }
    transaction.add(R.id.frame_container, fragment, "fragment")
    if (addToStack) {
        transaction.addToBackStack(null)
    }
    transaction.commit()
}
inline fun <reified T> genericType() = object: TypeToken<T>(){}