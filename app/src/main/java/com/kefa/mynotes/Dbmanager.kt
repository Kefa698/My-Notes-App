package com.kefa.mynotes

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class Dbmanager {
val dbName="MYNOTEBOOK"
    val dbTable="NOTES"
    val colID="ID"
    val  colTitle="Title"
    val colDes="Description"
    val dbVersion=1
    //CREATE TABLE IT IT DOEST EXIST(ID INTEGER PRIMARY KEY ,title TEXT,Description TEXT)
val sqlCreateTable="CREATE TABLE IF NOT EXISTS "+ dbTable +"("+colID +"INTEGER PRIMARY KEY,"+
    colTitle + " TEXT, "+ colDes+ "TEXT);"
    var sqlDb:SQLiteDatabase?=null

    constructor(context: Context ){
var db=DatabaseNotesHelper(context)
        //init database
        sqlDb=db.writableDatabase
    }


    inner class DatabaseNotesHelper:SQLiteOpenHelper{
        //this code only creates the database if itsnot availlable
        var context:Context?=null
        constructor(context: Context):super(context,dbName,null,dbVersion){
this.context=context
        }
        //oncreate ;its responsible for creating the database
        override fun onCreate(db: SQLiteDatabase?) {
           db!!.execSQL(sqlCreateTable)
            Toast.makeText(this.context,"Database is created",Toast.LENGTH_LONG).show()

        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
           db!!.execSQL("Drop Table IF EXISTS " + dbTable)
        }

    }

    fun Insert(values: ContentValues):Long{
        val ID=sqlDb!!.insert(dbTable,"",values)
        return ID
    }

}