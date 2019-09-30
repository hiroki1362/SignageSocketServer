package infodex_dentsu.jp.signagesocketserver.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import infodex_dentsu.jp.signagesocketserver.MainActivity
import infodex_dentsu.jp.signagesocketserver.R
import infodex_dentsu.jp.signagesocketserver.databinding.FragmentShowSignageBinding
import kotlinx.android.synthetic.main.fragment_show_signage.image1
import kotlinx.android.synthetic.main.fragment_show_signage.image2
import kotlinx.android.synthetic.main.fragment_show_signage.image3
import kotlinx.android.synthetic.main.fragment_show_signage.initImage
import timber.log.Timber

class SignageFragment: Fragment() {
    companion object {
        fun newInstance() = SignageFragment()
    }

    private val durationTime = 700L
    private lateinit var viewDataBinding: FragmentShowSignageBinding
    private lateinit var mainActivity: MainActivity
    private var displayId = 0
    private lateinit var fadeIn: AlphaAnimation
    private lateinit var fadeout: AlphaAnimation


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_show_signage, container, false)
        mainActivity = activity as MainActivity
        viewDataBinding = FragmentShowSignageBinding.bind(root).apply {
            viewmodel = (activity as MainActivity).getSignageViewModel()
        }
        return root
    }

    override fun onResume() {
        setAnimationInit()
        viewDataBinding.viewmodel?.start()
        mainActivity.socketListener.message.observe(mainActivity, Observer {
            displayId = it!!.toInt()
            Timber.d("Fragment: GetMessage ${displayId}")
            viewDataBinding.viewmodel!!.setDisplayImage(displayId)
            startDisplayAnimation(displayId)
        })
        super.onResume()
    }

    private fun setAnimationInit() {
        fadeIn = AlphaAnimation(0.0f, 1.0f)
        fadeout = AlphaAnimation(1.0f, 0.0f)
        fadeIn.duration = durationTime
        fadeout.duration = durationTime
        fadeIn.fillAfter = true
        fadeout.fillAfter = true
    }

    private fun startDisplayAnimation(id: Int) {
        when (id) {
            0 -> {
                if (initImage != null) {
                    initImage.startAnimation(fadeout)
                    image1.startAnimation(fadeIn)
                }
            }
            1 -> {
                initImage.alpha = 0f
                image3.alpha = 0f
                image2.alpha = 0f
                image1.alpha = 1f
            }
            2 -> {
                initImage.alpha = 0f
                image1.alpha = 0f
                image2.alpha = 1f
                image3.alpha = 0f
            }
            3 -> {
                initImage.alpha = 0f
                image1.alpha = 0f
                image2.alpha = 0f
                image3.alpha = 1f
            }
        }

    }
}