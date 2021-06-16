package com.dullyoung.basehttp.bean

import android.content.Context
import android.widget.Toast


/*
*  Created  in  2021/6/10
* 目的是为了避免忘了show
*/
class ToastCompat {
    companion object {
        fun show(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }
    }
}