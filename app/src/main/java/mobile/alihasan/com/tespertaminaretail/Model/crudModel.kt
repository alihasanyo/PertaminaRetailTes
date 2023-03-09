package mobile.alihasan.com.tespertaminaretail.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class crudModel {

    lateinit var itemID: String
    lateinit var itemName: String
    lateinit var itemBarcode: String

    constructor(){

    }

    constructor(itemID: String, itemName: String, itemBarcode: String){
        this.itemID = itemID
        this.itemName = itemName
        this.itemBarcode = itemBarcode
    }
}