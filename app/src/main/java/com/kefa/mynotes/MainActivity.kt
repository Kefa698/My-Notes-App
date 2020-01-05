package com.kefa.mynotes

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfNotes=ArrayList<Notes>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Add dummy Data
        listOfNotes.add(Notes(1,"lingard","Lingard (born 31 October 1997) is an English professional footballer who plays as a forward for Premier League club Manchester United and the England national team."))
        listOfNotes.add(Notes(2,"Rashford","Marcus Rashford (born 31 October 1997) is an English professional footballer who plays as a forward for Premier League club Manchester United and the England national team."))
   listOfNotes.add(Notes(3,"Martial","Martial(born 31 October 1997) is an English professional footballer who plays as a forward for Premier League club Manchester United and the England national team."))


        //adapter setup
        var myNotesAdapter=MyNotesAdapter(listOfNotes)
        lvNotes.adapter=myNotesAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //showing on the activity
        menuInflater.inflate(R.menu.main_menu,menu)
///use menu to access to the item
        val sv=menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val sm=getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext,query,Toast.LENGTH_LONG).show()
                //TODO :search data base
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item!=null){
            when(item.itemId){
                //going to notes page
                R.id.addnote->{
                var intent=Intent(this,AddNotes::class.java)
                    startActivity(intent)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }


    inner class MyNotesAdapter:BaseAdapter{
        var listOfNotesAdapter=ArrayList<Notes>()
        constructor(listOfNotesAdapter:ArrayList<Notes>):super(){
this.listOfNotesAdapter=listOfNotesAdapter
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
          var myView=layoutInflater.inflate(R.layout.ticket,null)
            var myNotes=listOfNotesAdapter[position]
            myView.tvTitle.text=myNotes.noteTitle
            myView.tvdes.text=myNotes.noteDes
            return myView
        }

        override fun getItem(position: Int): Any {
           return listOfNotesAdapter[position]
        }

        override fun getItemId(position: Int): Long {
          return position.toLong()
                  }

        override fun getCount(): Int {
            return listOfNotesAdapter.size
        }
    }
}
