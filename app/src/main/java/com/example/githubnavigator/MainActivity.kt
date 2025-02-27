package com.example.githubnavigator

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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.githubnavigator.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.presentation.profile.R as ProfileR
import com.example.presentation.user.repos.R as UserReposR
import com.example.presentation_all_users.R as AllUsersR
import com.example.presentation.login.R as LoginR


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition { false }
            setOnExitAnimationListener { screen -> exitAnimation(screen) }
        }
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setUpNav()
    }

    private fun setUpNav() {
        val navView: BottomNavigationView = binding.navView

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        lifecycleScope.launch {
            val navGraph = navController.navInflater.inflate(R.navigation.mobile_navigation)

            val userLoggedIn = viewModel.isUserLoggedIn()

            navGraph.setStartDestination(
                if (userLoggedIn) {
                    UserReposR.id.user_repos_navigation
                } else {
                    LoginR.id.login_navigation_graph
                }
            )
            navController.graph = navGraph
        }

        //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
        navView.setupWithNavController(navController)

        val navOptions = NavOptions.Builder()
            .setRestoreState(true)
            .setPopUpTo(navController.graph.startDestinationId, true) // Pop up to the start destination, inclusive
            .setLaunchSingleTop(true)
            .build()

        navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_profile_fragment -> {
                    navController.navigate(ProfileR.id.profile_navigation, null, navOptions)
                    true
                }
                R.id.navigation_all_users -> {
                    navController.navigate(AllUsersR.id.all_users_nav, null, navOptions)
                    true
                }
                R.id.navigation_user_repositories_fragment -> {
                    navController.navigate(UserReposR.id.user_repos_navigation, null, navOptions)
                    true
                }
                else -> false
            }
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(
                AllUsersR.id.navigation_all_users,
                UserReposR.id.navigation_user_repositories_fragment,
                ProfileR.id.navigation_profile_fragment,
            )
        )
        //todo
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == LoginR.id.login_screen_fragment
            ) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
        }
    }



    private fun exitAnimation(screen: SplashScreenViewProvider) {
        val zoomX = ObjectAnimator.ofFloat(
            screen.iconView, View.SCALE_X, 0.4f, 0.0f
        )
        zoomX.interpolator = OvershootInterpolator()
        zoomX.duration = 500L
        zoomX.doOnEnd { screen.remove() }

        val zoomY = ObjectAnimator.ofFloat(
            screen.iconView, View.SCALE_Y, 0.4f, 0.0f
        )
        zoomY.interpolator = OvershootInterpolator()
        zoomY.duration = 500L
        zoomY.doOnEnd { screen.remove() }

        zoomX.start()
        zoomY.start()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.popBackStack() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}