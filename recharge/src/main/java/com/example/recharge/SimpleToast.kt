package com.example.recharge

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SimpleToast {

    companion object {

        fun showToast(msg: String?, context: Context?) {

            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        fun doRecharge(amount: String?) {

        }

    }

}