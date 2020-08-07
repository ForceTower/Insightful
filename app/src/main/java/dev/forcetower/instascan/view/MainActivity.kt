package dev.forcetower.instascan.view

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.forcetower.instascan.R
import dev.forcetower.instascan.databinding.ActivityMainBinding
import dev.forcetower.toolkit.components.BaseActivity
import eightbitlab.com.blurview.RenderScriptBlur
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private val navController
        get() = findNavController(R.id.fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            binding.root.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        }

//        val decorView = window.decorView
//        val rootView = decorView.findViewById<ViewGroup>(android.R.id.content)
//        val windowBackground = rootView.background
//        binding.blurView
//            .setupWith(rootView)
//            .setFrameClearDrawable(windowBackground)
//            .setBlurAlgorithm(RenderScriptBlur(this))
//            .setBlurRadius(2f)
//            .setHasFixedTransformationMatrix(true)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun showSnack(string: String, duration: Int) {
        getSnackInstance(string, duration)?.show()
    }

    override fun getSnackInstance(string: String, duration: Int): Snackbar? {
        return Snackbar.make(binding.root, string, duration)
    }
}