package com.dullyoung.basehttp.bean


/*
*  Created  in  2021/6/10
*/
data class ResultInfo<T>(var code: Int, var msg: String, var data: T?) {

}