package org.jash.common.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import org.jash.common.utils.log
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.isSuperclassOf
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.jvmErasure

@Suppress("UNCHECKED_CAST")
open class BaseFragment<B: ViewDataBinding, VM:BaseViewModel>:Fragment() {
    private val types by lazy { (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments }
    val viewModel:VM by lazy { ViewModelProvider(this)[types[1] as Class<VM>] }
    lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val method =
            (types[0] as Class<B>).getDeclaredMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(null, inflater) as B
        val map = this::class.declaredFunctions.filter { it.parameters.size == 2 }
            .associateBy { it.parameters[1].type }
        viewModel::class.declaredMemberProperties.filter { LiveData::class.isSuperclassOf(it.returnType.jvmErasure) }
            .forEach {
                it.isAccessible = true
                val liveData = (it as KProperty1<VM, *>).get(viewModel) as LiveData<*>
                liveData.observe(viewLifecycleOwner) { data ->
                    map[it.returnType.arguments[0].type]?.call(this, data)
                }
            }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            error(it)
        }
        return binding.root
    }
    open fun error(msg:String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        log("error: $msg")
    }
}