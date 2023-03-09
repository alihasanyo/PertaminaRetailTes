package mobile.alihasan.com.tespertaminaretail.Utils

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import mobile.alihasan.com.tespertaminaretail.Model.crudModel
import java.lang.Exception
import java.util.ArrayList

val database_name = "CRUD_pertamina"
val table_name = "item"
val col_id = "id"
val item_id = "item_id"
val item_namee = "item_name"
val barcode = "barcode"

class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, database_name, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + table_name + "(" +
              col_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
              item_id + " TEXT UNIQUE," + item_namee + " TEXT," +
              barcode + " TEXT)";

        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(item: crudModel) {
        try {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(item_id, item.itemID)
            contentValues.put(item_namee, item.itemName)
            contentValues.put(barcode, item.itemBarcode)

            val rowid = db.insert(table_name, null, contentValues)
            if (rowid == -1.toLong()){
                Toast.makeText(context, "Data can't insert", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Data insert", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun readData(): ArrayList<crudModel> {
        val array_list: ArrayList<crudModel> = ArrayList()

        val db = this.readableDatabase
        val res = db.rawQuery("select * from $table_name order by id", null)
        res.moveToFirst()

        while (res.isAfterLast == false) {
            var model = crudModel()
            model.itemID =  res.getString(1)
            model.itemName = (res.getString(2))
            model.itemBarcode = (res.getString(3))

            array_list.add(model)
            res.moveToNext()
        }

        res.close()
        db.close()
        return array_list
    }

    fun deleteData(itemid: String) {

        val db = this.writableDatabase
        try {
            db.delete(table_name, "item_id = ? ", arrayOf(itemid))
        } catch (e: Exception){

        }
        db.close()
    }

    fun updateData(data: crudModel) {
        try {
            val db = this.writableDatabase
            val cv = ContentValues()
            cv.put(item_id, data.itemID)
            cv.put(item_namee, data.itemName)
            cv.put(barcode, data.itemBarcode)

            db.update(table_name, cv, "item_id = ?", arrayOf(data.itemID))
            db.close()
        } catch (e: Exception){
            e.printStackTrace()
        }

    }

}