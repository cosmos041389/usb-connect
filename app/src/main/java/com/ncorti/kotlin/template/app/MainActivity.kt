package com.ncorti.kotlin.template.app

import android.content.Intent
import android.hardware.usb.UsbConstants
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ncorti.kotlin.template.app.databinding.ActivityMainBinding
import com.ncorti.kotlin.template.library.FactorialCalculator
import com.ncorti.kotlin.template.library.android.ToastUtil
class MainActivity : AppCompatActivity() {

    // test
    private lateinit var binding: ActivityMainBinding
    private lateinit var usbManager: UsbManager
    private lateinit var buttonCheckUvcCamera: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usbManager = getSystemService(USB_SERVICE) as UsbManager
        buttonCheckUvcCamera = findViewById(R.id.button_check_uvc_camera)

        buttonCheckUvcCamera.setOnClickListener {
            val isConnected = isUvcCameraConnected()
            val message = if (isConnected) {
                "UVC camera is connected"
            } else {
                "UVC camera is not connected"
            }
            ToastUtil.showToast(this, message)
        }

        /*
        binding.buttonCompute.setOnClickListener {
            val message = if (binding.editTextFactorial.text.isNotEmpty()) {
                val input = binding.editTextFactorial.text.toString().toLong()
                val result = try {
                    FactorialCalculator.computeFactorial(input).toString()
                } catch (ex: IllegalStateException) {
                    "Error: ${ex.message}"
                }

                binding.textResult.text = result
                binding.textResult.visibility = View.VISIBLE
                getString(R.string.notification_title, result)
            } else {
                getString(R.string.please_enter_a_number)
            }
            ToastUtil.showToast(this, message)
        }*/

        /*
        binding.buttonAppcompose.setOnClickListener {
            val intent = Intent(it.context, ComposeActivity::class.java)
            startActivity(intent)
        }*/
    }

    private fun isUvcCameraConnected(): Boolean {
        val deviceList = usbManager.deviceList
        for (device in deviceList.values) {
            for (i in 0 until device.interfaceCount) {
                val usbInterface = device.getInterface(i)
                if (usbInterface.interfaceClass == UsbConstants.USB_CLASS_VIDEO &&
                    usbInterface.interfaceSubclass == 1) {
                    // UVC camera interface found
                    return true
                }
            }
        }
        // UVC camera not found
        return false
    }

}
