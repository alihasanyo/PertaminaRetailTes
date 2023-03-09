package mobile.alihasan.com.tespertaminaretail.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mobile.alihasan.com.tespertaminaretail.Adapter.ItemAdapter
import mobile.alihasan.com.tespertaminaretail.Adapter.ItemSearchAdapter
import mobile.alihasan.com.tespertaminaretail.Model.crudModel
import mobile.alihasan.com.tespertaminaretail.R
import mobile.alihasan.com.tespertaminaretail.Utils.DatabaseHandler
import java.util.ArrayList

class SearchItemActivity : AppCompatActivity() {

    private lateinit var et_search: AutoCompleteTextView
    private lateinit var iv_clear: ImageView
    private lateinit var rv_searchData: RecyclerView
    private lateinit var iv_back_item: ImageView

    private lateinit var activity: Activity
    private lateinit var getDataItem: ArrayList<crudModel>
    private lateinit var listFilter: ArrayList<crudModel>
    private lateinit var mAdapter: ItemSearchAdapter
    private lateinit var dbMaster: DatabaseHandler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_item)
        activity = this
        dbMaster = DatabaseHandler(this)

        et_search = findViewById(R.id.et_search)
        iv_clear = findViewById(R.id.iv_clear)
        rv_searchData = findViewById(R.id.rv_searchData)
        iv_back_item = findViewById(R.id.iv_back_item)

        rv_searchData.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rv_searchData.setNestedScrollingEnabled(true)
        rv_searchData.setFocusable(true)
        linearLayoutManager.isAutoMeasureEnabled = true
        linearLayoutManager.isSmoothScrollbarEnabled = true
        rv_searchData.layoutManager = linearLayoutManager

        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // TODO Auto-generated method stub
            }

            override fun onTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                listFilter = ArrayList()

                for (m: crudModel in getDataItem) {
                    if (m.itemID.lowercase().contains(s) or m.itemName.lowercase().contains(s) or m.itemBarcode.lowercase().contains(s)) {
                        listFilter.add(m)
                    }
                }

                val adapter = ItemSearchAdapter(activity, listFilter)
                rv_searchData.adapter = adapter
            }

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {
                    iv_clear.visibility = View.VISIBLE
                } else {
                    iv_clear.visibility = View.GONE
                }
            }
        })

        iv_clear.setOnClickListener{
            et_search.setText("")
            et_search.requestFocus()
        }

        iv_back_item.setOnClickListener {
            val back = Intent(activity, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(back)
        }

        getList()

    }

    override fun onResume() {
        super.onResume()
        getList()
    }

    private fun getList() {
        getDataItem = ArrayList()
        getDataItem.clear()
        getDataItem = dbMaster.readData()

        rv_searchData.visibility = View.GONE
        Handler().postDelayed(Runnable {

            mAdapter = ItemSearchAdapter(activity, getDataItem)
            rv_searchData.adapter = mAdapter
            mAdapter.notifyDataSetChanged()

            rv_searchData.visibility = View.VISIBLE }
            , 1000 * 2)

    }
}