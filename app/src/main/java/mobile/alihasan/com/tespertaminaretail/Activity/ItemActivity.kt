package mobile.alihasan.com.tespertaminaretail.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mobile.alihasan.com.tespertaminaretail.Adapter.ItemAdapter
import mobile.alihasan.com.tespertaminaretail.Model.crudModel
import mobile.alihasan.com.tespertaminaretail.R
import mobile.alihasan.com.tespertaminaretail.Utils.DatabaseHandler
import java.util.ArrayList

class ItemActivity : AppCompatActivity() {

    private lateinit var et_itemID: EditText
    private lateinit var et_itemName: EditText
    private lateinit var et_barcode: EditText
    private lateinit var lay_Save: LinearLayout
    private lateinit var lay_data_item: LinearLayout
    private lateinit var rv_result: RecyclerView
    private lateinit var iv_back_item: ImageView
    private lateinit var load_dataitem: ProgressBar

    private lateinit var activity: Activity
    private lateinit var dbMaster: DatabaseHandler
    private lateinit var mList: ArrayList<crudModel>
    private lateinit var mAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        activity = this
        dbMaster = DatabaseHandler(this)

        et_itemID = findViewById(R.id.et_itemid)
        et_itemName = findViewById(R.id.et_itemname)
        et_barcode = findViewById(R.id.et_barcode)
        lay_Save = findViewById(R.id.lay_save)
        lay_data_item = findViewById(R.id.lay_data_item)
        rv_result = findViewById(R.id.rv_data_crud)
        iv_back_item = findViewById(R.id.iv_back_item)
        load_dataitem = findViewById(R.id.load_dataitem)

        lay_Save.setOnClickListener {
            if (et_itemID.text.toString().isEmpty()){
                Toast.makeText(this, "You should this fill column", Toast.LENGTH_SHORT).show()
                et_itemID.requestFocus()
            } else if (et_itemName.text.toString().isEmpty()){
                Toast.makeText(this, "You should this fill column", Toast.LENGTH_SHORT).show()
                et_itemName.requestFocus()
            } else if (et_barcode.text.toString().isEmpty()){
                Toast.makeText(this, "You should this fill column", Toast.LENGTH_SHORT).show()
                et_barcode.requestFocus()
            } else {
                var item = crudModel(et_itemID.text.toString(), et_itemName.text.toString(), et_barcode.text.toString())
                dbMaster.insertData(item)

                getListData()
            }

        }

        rv_result.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rv_result.setNestedScrollingEnabled(true)
        rv_result.setFocusable(true)
        linearLayoutManager.isAutoMeasureEnabled = true
        linearLayoutManager.isSmoothScrollbarEnabled = true
        rv_result.layoutManager = linearLayoutManager

        iv_back_item.setOnClickListener {
            val back = Intent(activity, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(back)
        }

        getListData()
    }

    override fun onResume() {
        super.onResume()
        getListData()
    }

    fun getListData(){
        mList = ArrayList()
        mList.clear()
        mList = dbMaster.readData()

        load_dataitem.visibility = View.VISIBLE
        rv_result.visibility = View.GONE
        Handler().postDelayed(Runnable {
            load_dataitem.visibility = View.GONE

            mAdapter = ItemAdapter(activity, mList)
            rv_result.adapter = mAdapter
            mAdapter.notifyDataSetChanged()

            rv_result.visibility = View.VISIBLE }
            , 1000 * 2)
    }
}