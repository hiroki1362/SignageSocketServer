package infodex_dentsu.jp.signagesocketserver.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import infodex_dentsu.jp.signagesocketserver.MainActivity
import infodex_dentsu.jp.signagesocketserver.R
import infodex_dentsu.jp.signagesocketserver.controller.NavigationController
import infodex_dentsu.jp.signagesocketserver.databinding.FragmentTopBinding
import infodex_dentsu.jp.signagesocketserver.lifecycle.observeNonNull
import infodex_dentsu.jp.signagesocketserver.viewmodel.SetViewModelDone
import infodex_dentsu.jp.signagesocketserver.viewmodel.StartButtonDone

class StartFragment: Fragment() {

    companion object {
        fun newInstance() = StartFragment()
    }

    private lateinit var viewDataBinding: FragmentTopBinding
    private lateinit var navigationController: NavigationController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_top, container, false)
        viewDataBinding = FragmentTopBinding.bind(root).apply {
            viewmodel = (activity as MainActivity).getStartViewModel()
        }

        return viewDataBinding.root
    }

    override fun onResume() {
        navigationController = NavigationController(activity as MainActivity)
        viewDataBinding.viewmodel?.start()
        viewDataBinding.viewmodel!!.actionDone.observeNonNull(this) {
            when(it) {
                is SetViewModelDone -> callIpAddress()
                is StartButtonDone -> navigationController.navigateToSignageDisplay()
            }
        }
        super.onResume()
    }

    private fun callIpAddress() {
        val address = (activity as MainActivity).getIpAddressFromSocketListener()
        viewDataBinding.viewmodel?.setIpAddress(address)
    }

}