package mobile.alihasan.com.tespertaminaretail.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import mobile.alihasan.com.tespertaminaretail.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var lay_item: LinearLayout
    private lateinit var lay_search: LinearLayout

    private lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity = this

        lay_item = findViewById(R.id.lay_item)
        lay_search = findViewById(R.id.lay_search)

        lay_item.setOnClickListener(this)
        lay_search.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.lay_item -> {
                val pageDetailItem = Intent(this, ItemActivity::class.java)
                startActivity(pageDetailItem)
            }

            R.id.lay_search -> {
                val pageDetailSearch = Intent(this, SearchItemActivity::class.java)
                startActivity(pageDetailSearch)
            }

            else-> {}
        }
    }
}