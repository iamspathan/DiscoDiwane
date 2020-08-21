package org.gdgnagpur.cityvibe.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

abstract class BaseFragment : Fragment(), BaseLifecycleComponent {

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