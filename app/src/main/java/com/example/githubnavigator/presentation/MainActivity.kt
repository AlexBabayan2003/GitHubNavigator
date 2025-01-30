package com.example.githubnavigator.presentation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.splashscreen.SplashScreenViewProvider
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.githubnavigator.R
import com.example.githubnavigator.databinding.ActivityMainBinding
import com.example.githubnavigator.presentation.profile.ProfileViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    private lateinit var navController: NavController
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                profileViewModel.isLoading.value
            }
            setOnExitAnimationListener { screen ->
                exitAnimation(screen)
            }
        }

        setContentView(
            ActivityMainBinding.inflate(layoutInflater).also { _binding = it }.root
        )
        enableEdgeToEdge()
        val navView: BottomNavigationView = binding.navView

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        // Set up the BottomNavigationView with the NavController
        navView.setupWithNavController(navController)

        // Set up the ActionBar with the NavController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_profile,
                R.id.navigation_user_repositories,
                R.id.navigation_all_users
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Add the destination change listener AFTER setting up the BottomNavigationView
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.login_screen_fragment) {
                navView.visibility = View.GONE // скрыть BottomNavigationView
            } else {
                navView.visibility = View.VISIBLE // показать BottomNavigationView
            }
        }
    }

    private fun exitAnimation(screen: SplashScreenViewProvider) {
        val zoomX = ObjectAnimator.ofFloat(
            screen.iconView,
            View.SCALE_X,
            0.4f,
            0.0f
        )
        zoomX.interpolator = OvershootInterpolator()
        zoomX.duration = 500L
        zoomX.doOnEnd { screen.remove() }

        val zoomY = ObjectAnimator.ofFloat(
            screen.iconView,
            View.SCALE_Y,
            0.4f,
            0.0f
        )
        zoomY.interpolator = OvershootInterpolator()
        zoomY.duration = 500L
        zoomY.doOnEnd { screen.remove() }

        zoomX.start()
        zoomY.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}