package xyz.mazuninky.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.Uri


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callBtn.setOnClickListener { makeCall() }
        mapBtn.setOnClickListener { viewMap() }
    }

    fun makeCall() {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "Your Phone_number"))
        startActivity(intent)
    }

    fun viewMap() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll=46.414382,10.013988"))
        startActivity(intent)
    }

    fun sendMail() {

    }

}
