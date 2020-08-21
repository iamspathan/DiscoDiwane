package org.gdgnagpur.cityvibe.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

abstract class BaseActivity : AppCompatActivity(), BaseLifecycleComponent {

    override val thisLifecycleOwner: LifecycleOwner
        get() = this

    init {
        lifecycle.addObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(this)
    }




}