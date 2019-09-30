package infodex_dentsu.jp.signagesocketserver.utils

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.VisibleForTesting
import infodex_dentsu.jp.signagesocketserver.viewmodel.SignageViewModel
import infodex_dentsu.jp.signagesocketserver.viewmodel.StartViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory private constructor(private val application: Application)
    :ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(SignageViewModel::class.java) ->
                    SignageViewModel(application)
                isAssignableFrom(StartViewModel::class.java) ->
                    StartViewModel(application)
                else -> throw IllegalArgumentException("このViewModelはできそこないだ : ${modelClass.name}")
            }
    } as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(application)
                    .also { INSTANCE = it }
            }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}