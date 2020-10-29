package com.crusbel.examen.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.crusbel.examen.R
import com.crusbel.examen.adapters.PagerAdapter
import com.crusbel.examen.fragments.ReptilFragment
import com.crusbel.examen.fragments.AveFragment
import com.crusbel.examen.fragments.MamiferoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var prevMenu: MenuItem? = null

    lateinit var tv:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = mainBar as Toolbar
        tv.title = getString(R.string.mamiferos)

        setPager(getPA())
        setNav()
    }

    private fun getPA():PagerAdapter{
        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addFrag(MamiferoFragment())
        adapter.addFrag(AveFragment())
        adapter.addFrag(ReptilFragment())
        return adapter
    }

    private fun setPager(adapter:PagerAdapter){
        vPager.adapter = adapter
        vPager.offscreenPageLimit = adapter.count
        vPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            //DESLIZAR
            override fun onPageSelected(position: Int) {
                prevMenu?.let { prevMenu!!.isChecked = false }
                        ?:run { nav.menu.getItem(0).isChecked = false }
                nav.menu.getItem(position).isChecked = true
                when(position){
                    0->tv.title = getString(R.string.mamiferos)
                    1->tv.title = getString(R.string.aves)
                    2->tv.title = getString(R.string.reptiles)
                }
                prevMenu = nav.menu.getItem(position)
            }
        })
    }

    //OPRIMIR BOTON
    private fun setNav(){
        nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menuMamiferos -> {
                    vPager.currentItem = 0
                    tv.title=getString(R.string.mamiferos)
                    true
                }
                R.id.menuAves -> {
                    vPager.currentItem = 1
                    tv.title=getString(R.string.aves)
                    true
                }
                R.id.menuReptiles -> {
                    vPager.currentItem = 2
                    tv.title=getString(R.string.reptiles)
                    true
                }
                else -> false
            }
        }
    }
}