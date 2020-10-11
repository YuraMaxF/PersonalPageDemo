package com.yuramax.personalpagedemo

import android.content.Context

/**
 * author : weijun
 * e-mail : 1301892339@qq.com
 * time   : 2019/12/03
 * desc   :
 * version: 1.0
 */
object DisplayUtil {
    fun px2dp(context: Context, pxValue: Float): Int
            = (pxValue / (context.resources.displayMetrics.density) + 0.5f).toInt()
    fun dp2px(context: Context, dpValue: Float): Int
            = (dpValue * (context.resources.displayMetrics.density) + 0.5f).toInt()
}