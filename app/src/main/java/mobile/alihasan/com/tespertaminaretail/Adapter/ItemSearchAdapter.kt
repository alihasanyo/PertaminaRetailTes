package mobile.alihasan.com.tespertaminaretail.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mobile.alihasan.com.tespertaminaretail.Model.crudModel
import mobile.alihasan.com.tespertaminaretail.R
import mobile.alihasan.com.tespertaminaretail.Utils.DatabaseHandler

class ItemSearchAdapter(private var context: Context, data: ArrayList<crudModel>) : RecyclerView.Adapter<ItemSearchAdapter.Itemholder>()
{

    var mData: ArrayList<crudModel>
    var dbMaster: DatabaseHandler = DatabaseHandler(context)

    init {
        this.mData = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Itemholder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_search_layout, parent, false)
        return Itemholder(view)
    }

    override fun onBindViewHolder(holder: Itemholder, position: Int) {
        val m: crudModel = mData[position]

        holder.idItemsearch.setText(m.itemID)
        holder.nameItemsearch.setText(m.itemName)
        holder.codeItemsearch.setText(m.itemBarcode)

    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class Itemholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var idItemsearch: TextView
        var nameItemsearch: TextView
        var codeItemsearch: TextView

        init {
            idItemsearch = itemView.findViewById(R.id.tv_item_search)
            nameItemsearch = itemView.findViewById(R.id.tv_itemname_search)
            codeItemsearch = itemView.findViewById(R.id.tv_barcode_search)
        }
    }

}