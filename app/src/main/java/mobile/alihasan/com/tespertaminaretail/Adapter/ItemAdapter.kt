package mobile.alihasan.com.tespertaminaretail.Adapter

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import mobile.alihasan.com.tespertaminaretail.Activity.ItemActivity
import mobile.alihasan.com.tespertaminaretail.Model.crudModel
import mobile.alihasan.com.tespertaminaretail.R
import mobile.alihasan.com.tespertaminaretail.Utils.DatabaseHandler

class ItemAdapter(private var context: Context, data: ArrayList<crudModel>) : RecyclerView.Adapter<ItemAdapter.Itemholder>()
{

    var mData: ArrayList<crudModel>
    var dbMaster: DatabaseHandler = DatabaseHandler(context)

    init {
        this.mData = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Itemholder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return Itemholder(view)
    }

    override fun onBindViewHolder(holder: Itemholder, position: Int) {
        val m: crudModel = mData[position]

        holder.idItem.setText(m.itemID)
        holder.nameItem.setText(m.itemName)
        holder.codeItem.setText(m.itemBarcode)
        holder.idItem.isEnabled = false
        holder.nameItem.isEnabled = false
        holder.codeItem.isEnabled = false

        holder.laydel.setOnClickListener {
            dbMaster.deleteData(m.itemID)
            var load = Intent(context, ItemActivity::class.java)
            context.startActivity(load)
        }

        holder.layedit.setOnClickListener {
            holder.idItem.isEnabled = false
            holder.nameItem.isEnabled = true
            holder.codeItem.isEnabled = true
            holder.layedittosave.visibility = View.VISIBLE
            holder.layedit.visibility = View.GONE

            Log.e("nameedit: ", m.itemName)
            Log.e("baredit: ", m.itemBarcode)
        }

        holder.layedittosave.setOnClickListener{
            var item = crudModel(holder.idItem.text.toString().trim(), holder.nameItem.text.toString().trim(), holder.codeItem.text.toString().trim())
            dbMaster.updateData(item)

            var load = Intent(context, ItemActivity::class.java)
            context.startActivity(load)

            holder.layedittosave.visibility = View.GONE
            holder.layedit.visibility = View.VISIBLE

            Log.e("nameup: ", item.itemName)
            Log.e("barup: ", item.itemBarcode)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class Itemholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var idItem: TextView
        var nameItem: TextView
        var codeItem: TextView
        var layedit: LinearLayout
        var laydel: LinearLayout
        var laydetailitem: LinearLayout
        var layedittosave: LinearLayout

        init {
            idItem = itemView.findViewById(R.id.tv_itemid)
            nameItem = itemView.findViewById(R.id.tv_itemname)
            codeItem = itemView.findViewById(R.id.tv_barcode)
            layedit = itemView.findViewById(R.id.lay_edit)
            laydel = itemView.findViewById(R.id.lay_delete)
            laydetailitem = itemView.findViewById(R.id.lay_detail_item)
            layedittosave = itemView.findViewById(R.id.lay_saveedit)
        }
    }

}