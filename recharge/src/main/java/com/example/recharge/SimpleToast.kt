package com.example.recharge

import android.content.Context
import android.widget.Toast

open class SimpleToast {
    companion object{
        fun showToast(msg:String?,context: Context?){
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
        }
    }
}