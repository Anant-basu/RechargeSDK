package com.example.recharge

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class SimpleToast {

    companion object {

        fun showToast(msg: String?, context: Context?) {

            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun calculateHmac(payload: String, secret: String): String {
            val keySpec = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
            val mac: Mac
            try {
                mac = Mac.getInstance("HmacSHA256")
                mac.init(keySpec)
                val result = mac.doFinal(payload.toByteArray())
                return Base64.getEncoder().encodeToString(result)
            } catch (e: NoSuchAlgorithmException) {
                throw RuntimeException("Exception hashing payload", e)
            } catch (e: InvalidKeyException) {
                throw RuntimeException("Exception hashing payload", e)
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getToken() {
            val secret = "38d8c8e209ea4a958be36d9e6c15c216"
            val mid = "5e5f5d15c44341b4"
            val ac = "a49f11d1d6"
            try {
                for (i in 0..1) {
                    val ts = System.currentTimeMillis().toString()
                    val requestBody = "$mid|$ac|$ts"
                    val hashValue = calculateHmac(requestBody, secret)
                    println("paylod > $requestBody")
                    println("Hash > $hashValue")
                    println("Ts>  $ts\n")
                    Thread.sleep(50)
                }
            } catch (th: Throwable) {
                th.printStackTrace()
            }
        }



    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateHmac(payload: String, secret: String): String {
        val keySpec = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
        val mac: Mac
        try {
            mac = Mac.getInstance("HmacSHA256")
            mac.init(keySpec)
            val result = mac.doFinal(payload.toByteArray())
            return Base64.getEncoder().encodeToString(result)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Exception hashing payload", e)
        } catch (e: InvalidKeyException) {
            throw RuntimeException("Exception hashing payload", e)
        }
    }



}