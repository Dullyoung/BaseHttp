package com.dullyoung.basehttp.engine

import android.util.Log
import com.dullyoung.basehttp.bean.ResultInfo

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.lang.reflect.Type


/*
*  Created  in  2021/6/10
*/
open class BaseEngine<T> {
    private val TAG = "HttpEngine"

    companion object {
        private var defaultParams: Map<String, String> = HashMap()
    }

    fun setDefaultParams(defaultParams: Map<String, String>) {
        BaseEngine.defaultParams = defaultParams
    }

    private fun addDefaultParams(targetParams: HashMap<String, String>) {
        for (s in defaultParams.keys) {
            targetParams[s] = defaultParams[s]!!
        }
    }

    //sync post
    open fun post(
        url: String, type: Type,
        params: HashMap<String, String>?
    ): ResultInfo<T> {
        var allParams = params;
        if (allParams == null) {
            allParams = HashMap()
        }
        addDefaultParams(allParams)
        Log.i(TAG, "客户端请求地址-------->$url")
        Log.i(TAG, "客户端请求数据-------->" + Gson().toJson(allParams))
        var resultInfo: ResultInfo<T>?
        try {
            val builder = FormBody.Builder()
            for (key in allParams.keys) {
                builder.add(key, allParams[key]!!)
            }
            val requestBody: RequestBody = builder.build();
            val request: Request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()
            val okHttpClient = OkHttpClient()
            val response = okHttpClient.newCall(request).execute()
            var string = ""
            if (response.isSuccessful) {
                string = response.body?.string()!!
            }
            resultInfo = getResultInfo(string, type)
            Log.i(TAG, "服务器返回数据-------->: ${resultInfo.toString()}")
        } catch (e: Exception) {
            val body =
                "{\"code\":500, \"msg\":\"" + e.message!!.replace("\"".toRegex(), "'") + "\"}"
            resultInfo = getResultInfo(body, type)
            Log.i(TAG, "异常->$e")
        }
        return resultInfo!!
    }

    private fun getResultInfo(body: String, type: Type?): ResultInfo<T> {
        val gson = Gson()
        val resultInfo: ResultInfo<T> = if (type != null) {
            gson.fromJson(body, type)
        } else {
            gson.fromJson(body, object : TypeToken<ResultInfo<T>>() {}.type)
        }
        return resultInfo
    }


}