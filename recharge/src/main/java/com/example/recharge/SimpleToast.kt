package com.example.recharge

import ApiResponse
import Payload
import android.content.Context
import android.os.Build
import android.widget.Toast
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.annotation.RequiresApi
import com.example.recharge.retrofit.ApiClient
import com.example.recharge.retrofit.ApiService
import org.json.JSONException
import org.json.JSONObject
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class SimpleToast {

    companion object {
        val mutableMap = mutableMapOf<String, String>()

        @RequiresApi(Build.VERSION_CODES.O)
        fun getTokenByAPI(){
            mutableMap.clear()
            getToken()

            val service = ApiClient.instance.create(ApiService::class.java)
            val requestData = mutableMap
            println(mutableMap)
            val call = service.createSession(requestData)
            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val apiResponse = response.body()

                        val token = extractSecondTokenFromApiResponse(apiResponse)
                        println("Token: $token")
                    } else {
                        val statusCode = response.code()
                        println("Error ${response.errorBody()?.string()} (Status code: $statusCode)")

                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })

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
                for (i in 0..0) {
                    mutableMap.clear()
                    val ts = System.currentTimeMillis().toString()
                    val requestBody = "$mid|$ac|$ts"
                    val hashValue = calculateHmac(requestBody, secret)
                    mutableMap.put("mid", mid)
                    mutableMap.put("cs", hashValue)
                    mutableMap.put("ts", ts)
                    mutableMap.put("agentcode", ac)
                    mutableMap.put("serviceType", "Recharge")
                    println("paylod > $requestBody")
                    println("Hash > $hashValue")
                    println("Ts>  $ts\n")
                    println("agentcode > $ac")
                    println("serviceType  $ts\n")

                    Thread.sleep(50)
                }
            } catch (th: Throwable) {
                th.printStackTrace()
            }
        }

        fun extractSecondTokenFromApiResponse(apiResponse: ApiResponse?): String? {
            apiResponse ?: return null

            try {
                val jsonObject = JSONObject(apiResponse.data)

                return jsonObject.getString("token")
            } catch (e: JSONException) {
                e.printStackTrace()
                return null
            }
        }

    }
}