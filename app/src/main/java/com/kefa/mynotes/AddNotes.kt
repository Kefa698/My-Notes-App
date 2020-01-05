package com.kefa.mynotes

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes.*

class AddNotes : AppCompatActivity() {
    val dbTable="NOTES"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
    }

    fun buAdd(view: View){
        //created instance from the database
        var dbmanager=Dbmanager(this)
        var values=ContentValues()
        values.put("Title",edTitle.text.toString())
        values.put("Description",edNotes.text.toString() )
      val ID= dbmanager.Insert(values)
        if (ID>0){
            Toast.makeText(this,"notes is added",Toast.LENGTH_LONG).show()
            finish()

        }else{
            Toast.makeText(this," notes cannot be added",Toast.LENGTH_LONG).show()
            finish()

        }
    }
}
