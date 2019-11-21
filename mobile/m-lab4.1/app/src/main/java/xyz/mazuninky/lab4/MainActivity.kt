package xyz.mazuninky.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent

import android.net.Uri
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callBtn.setOnClickListener { makeCall() }
        mapBtn.setOnClickListener { viewMap() }
        webBtn.setOnClickListener { openWebPage() }
        sendBtn.setOnClickListener { sendMail() }
    }

    fun intentIsSafe(intent: Intent): Boolean {
        val pm = packageManager
        return intent.resolveActivity(pm) != null
    }

    fun makeCall() {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "89959989448"))
        if (intentIsSafe(intent))
            startActivity(intent)
        else {
            Toast.makeText(this, "Your phone have no app can dial", Toast.LENGTH_LONG).show()
        }
    }

    fun viewMap() {
        val gmmIntentUri = Uri.parse("geo:37.7749,-122.4194")
        val intent =
            Intent(Intent.ACTION_VIEW, gmmIntentUri)
        intent.setPackage("com.google.android.apps.maps")

        if (intentIsSafe(intent))
            startActivity(intent)
        else {
            Toast.makeText(this, "Your phone have no app can view map", Toast.LENGTH_LONG).show()
        }
    }

    fun sendMail() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("mazuninky@gmail.com", "mazuninky@yandex.ru"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Email subject")
        intent.putExtra(Intent.EXTRA_TEXT, "Email message text")
        intent.type = "text/plain"

        if (intentIsSafe(intent))
            startActivity(intent)
        else {
            Toast.makeText(this, "Your phone have no app can send email", Toast.LENGTH_LONG).show()
        }
    }

    fun openWebPage() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("http://www.ifmo.ru/ru/")

        if (intentIsSafe(intent))
            startActivity(intent)
        else {
            Toast.makeText(this, "Your phone have no app can open web page", Toast.LENGTH_LONG)
                .show()
        }
    }

}
