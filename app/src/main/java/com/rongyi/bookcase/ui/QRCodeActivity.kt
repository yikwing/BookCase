package com.rongyi.bookcase.ui

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import cn.bingoogolapple.qrcode.core.QRCodeView
import com.rongyi.bookcase.R
import kotlinx.android.synthetic.main.activity_qrcode.*


/**
 * 二维码界面
 *
 * @author yikwing
 * @date 2017/12/20
 */
class QRCodeActivity : AppCompatActivity(), QRCodeView.Delegate {

    override fun onScanQRCodeSuccess(result: String) {

        val vibrator = getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(300)

        setResult(RESULT_OK, Intent().putExtra("qrcode", result))
        finish()
    }

    override fun onScanQRCodeOpenCameraError() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)

        zxingview.setDelegate(this)
        zxingview.startSpotDelay(500)
    }

}