package com.example.core.ui.utility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <T : ViewBinding> ViewGroup.inflateAdapterItem(
    crossinline bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T
) = bindingInflater(LayoutInflater.from(this.context), this, false)



@MainThread
fun <T : ViewBinding> Fragment.fragmentViewBinding(
    viewBinder: (View) -> T
): FragmentViewBindingProperty<T> = FragmentViewBindingProperty(viewBinder)

class FragmentViewBindingProperty<T : ViewBinding>(private val viewBinder: (View) -> T) :
    ViewBindingProperty<T>(), ReadOnlyProperty<Fragment, T> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return binding ?: run {
            thisRef.viewLifecycleOwnerLiveData.observe(thisRef) {
                it.lifecycle.let {
                    it.addObserver(this)
                    lifecycle = it
                }
            }
            thisRef.requireView()
            viewBinder.invoke(thisRef.requireView()).also {
                binding = it
//                if (binding is ViewDataBinding) {
//                    (binding as ViewDataBinding).lifecycleOwner = thisRef.viewLifecycleOwner
//                }
            }
        }
    }
}

open class ViewBindingProperty<T : ViewBinding> : DefaultLifecycleObserver {
    protected var binding: T? = null
    protected var lifecycle: Lifecycle? = null

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        lifecycle?.removeObserver(this)
        lifecycle = null
        binding = null
    }
}