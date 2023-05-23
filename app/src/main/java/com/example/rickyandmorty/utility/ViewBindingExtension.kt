package com.example.rickyandmorty.utility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
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
            thisRef.viewLifecycleOwner.lifecycle.let {
                it.addObserver(this)
                lifecycle = it
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

open class ViewBindingProperty<T : ViewBinding> : LifecycleObserver {
    protected var binding: T? = null
    protected var lifecycle: Lifecycle? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        lifecycle?.removeObserver(this)
        lifecycle = null
        binding = null
    }
}