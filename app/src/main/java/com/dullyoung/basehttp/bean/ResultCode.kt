package com.dullyoung.basehttp.bean


/*
*  Created  in  2021/6/10
*/
class ResultCode() {
    companion object {
        const val FAILED = 0;
        const val SUCCESS = 1;
        const val SERVER_ERROR = 500;
        const val NOT_FOUND = 404;
    }
}
