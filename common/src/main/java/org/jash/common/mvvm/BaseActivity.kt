package org.jash.common.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import org.jash.common.utils.log
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.isSuperclassOf
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.jvmErasure

@Suppress("UNCHECKED_CAST")
open class BaseActivity<B:ViewDataBinding, VM:BaseViewModel>:AppCompatActivity() {
    private val types by lazy { (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments }
    val viewModel:VM by lazy { ViewModelProvider(this)[types[1] as Class<VM>] }
    val binding: B by lazy {
        val method =
            (types[0] as Class<B>).getDeclaredMethod("inflate", LayoutInflater::class.java)
        method.invoke(null, layoutInflater) as B
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val map = this::class.declaredFunctions.filter { it.parameters.size == 2 }
            .associateBy { it.parameters[1].type }
        viewModel::class.declaredMemberProperties.filter { LiveData::class.isSuperclassOf(it.returnType.jvmErasure) }
            .forEach {
                it.isAccessible = true
                val liveData = (it as KProperty1<VM, *>).get(viewModel) as LiveData<*>
                liveData.observe(this) { data ->
                    map[it.returnType.arguments[0].type]?.call(this, data)
                }
            }
        viewModel.errorLiveData.observe(this) {
            error(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
    open fun error(msg:String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        log("error: $msg")
    }
}