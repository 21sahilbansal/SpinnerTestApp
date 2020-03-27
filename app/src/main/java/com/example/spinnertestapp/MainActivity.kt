package com.example.spinnertestapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() , AdapterView.OnItemSelectedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val country =
            arrayOf("India", "USA", "China", "Japan", "Other")
        val aa: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this,R.layout.item1, country)
        aa.setDropDownViewResource(R.layout.item2)
        spinner.setAdapter(aa)
        spinner1.adapter = aa


    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
