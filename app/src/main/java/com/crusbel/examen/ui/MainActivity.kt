package com.crusbel.examen.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.crusbel.examen.R
import com.crusbel.examen.adapters.AnimalAdapter
import com.crusbel.examen.adapters.PagerAdapter
import com.crusbel.examen.db.entity.AnimalEntity
import com.crusbel.examen.fragments.ReptilFragment
import com.crusbel.examen.fragments.AveFragment
import com.crusbel.examen.fragments.MamiferoFragment
import com.crusbel.examen.model.AnimalViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var prevMenu: MenuItem? = null

    private lateinit var animalViewModel: AnimalViewModel
    lateinit var tv:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = mainBar as Toolbar
        tv.title = getString(R.string.mamiferos)

        /*desde aqui*/

        /*val recycler = this.findViewById(R.id.recyclerview) as RecyclerView
        val adapter = AnimalAdapter(this)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)*/

        animalViewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)
        animalViewModel.animales.observe(this, Observer { animales ->
            animales?.let { AnimalAdapter(this).setAnimales(animales) }
        })

        setPager(getPA())
        setNav()

        btAdd.setOnClickListener {
            val intent = Intent(this,NewAnimalActivity::class.java)
            startActivityForResult(intent,100)
        }
        val observer = Observer<List<AnimalEntity>> { animales ->
            if (animales != null) {
                var text = ""
                for (animal in animales) {
                    text += animal.nombre + " " + animal.tipo + " - " + animal.descripcion + "\n"
                }
                textprueba.text = text
            }
        }
        animalViewModel.animales.observe(this, observer)
        /*fin*/
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        lateinit var nombre:String
        lateinit var tipo:String
        lateinit var descripcion:String

        if(requestCode==100 && resultCode == Activity.RESULT_OK){
            data?.getStringExtra("nombre")?.let { nombre = it}
            data?.getStringExtra("tipo")?.let { tipo = it}
            data?.getStringExtra("descripcion")?.let { descripcion = it}

            animalViewModel.insertAnimal(AnimalEntity(nombre = nombre,tipo = tipo,descripcion = descripcion))
        }
    }
}