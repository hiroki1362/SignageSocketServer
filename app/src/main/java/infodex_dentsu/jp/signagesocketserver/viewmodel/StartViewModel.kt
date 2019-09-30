package infodex_dentsu.jp.signagesocketserver.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField

class StartViewModel(context: Application): AndroidViewModel(context) {

    var ipAddress = ObservableField<String>()
    private val initIp = "IPアドレス取得中"
    private val mutableActionDone: MutableLiveData<VMActionDone> = MutableLiveData()
    val actionDone: LiveData<VMActionDone> = mutableActionDone

    fun start() {
        ipAddress.set(initIp)
        mutableActionDone.postValue(SetViewModelDone)

    }

    fun setIpAddress(address:String) {
        ipAddress.set(address)
    }

    fun onStartBtnClick() {
        mutableActionDone.postValue(StartButtonDone)
    }

}

sealed class VMActionDone
object SetViewModelDone: VMActionDone()
object StartButtonDone: VMActionDone()