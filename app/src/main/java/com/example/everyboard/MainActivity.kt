package com.example.everyboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.everyboard.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    companion object {
        val HOME_FRAGMENT = "HomeFragment"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            mainBottomNavigation.run {
                NavigationBarView.OnItemSelectedListener {
                    when(it.itemId) {
                        R.id.itemHome -> {
                            replaceFragment(HOME_FRAGMENT, true)
                            true
                        }
                        R.id.itemTimeTable -> {
                            true
                        }
                        R.id.itemBoard -> {
                            true
                        }
                        R.id.itemNotification -> {
                            true
                        }
                        R.id.itemMyInfo -> {
                            true
                        }

                        else -> false
                    }
                }

            }
        }
    }

    // 지정한 프래그먼트로 교체한다.
    fun replaceFragment(name:String, addToBackStack:Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var newFragment = when(name) {
            HOME_FRAGMENT -> {
                HomeFragment()
            }

            else -> {
                Fragment()
            }
        }

        if(newFragment != null) {
            // 프래그먼트 교체
            fragmentTransaction.replace(R.id.mainFragmentContainerView, newFragment)

            if(addToBackStack) {
                fragmentTransaction.addToBackStack(name)
            }

            // 교체명령
            fragmentTransaction.commit()
        }
    }

    // 지정한 프래그먼트의 백스택을 삭제한다.
    fun removeFragment(name:String) {
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}