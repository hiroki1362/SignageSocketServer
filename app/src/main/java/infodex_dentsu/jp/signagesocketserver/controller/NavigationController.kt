package infodex_dentsu.jp.signagesocketserver.controller

import infodex_dentsu.jp.signagesocketserver.MainActivity
import infodex_dentsu.jp.signagesocketserver.R
import infodex_dentsu.jp.signagesocketserver.fragment.SignageFragment
import infodex_dentsu.jp.signagesocketserver.fragment.StartFragment

class NavigationController constructor(mainActivity: MainActivity) {
    private val containerId = R.id.content_frame
    private val fragmentManager = mainActivity.supportFragmentManager

    fun navigateToStartDisplay() {
        StartFragment.newInstance()?.let {
            fragmentManager.beginTransaction().replace(containerId, it).commit()
        }
    }

    fun navigateToSignageDisplay() {
        SignageFragment.newInstance()?.let {
            fragmentManager.beginTransaction().replace(containerId, it).commit()
        }
    }

}