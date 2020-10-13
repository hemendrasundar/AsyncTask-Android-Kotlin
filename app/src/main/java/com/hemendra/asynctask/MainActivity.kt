package com.hemendra.asynctask

import android.app.ProgressDialog
import android.content.Context
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var policy = StrictMode.ThreadPolicy.Builder().detectAll().build()
        StrictMode.setThreadPolicy(policy)

        btn.setOnClickListener {

            var mtask = Mytask(et.text.toString(),iv,this@MainActivity)
            mtask.execute()

        }
    }

    class Mytask(var Image_URL:String,var iv:ImageView,ctx:Context) : AsyncTask<Unit,Unit,Unit>()
    {
        var pDialog:ProgressDialog = ProgressDialog(ctx)
        lateinit var isr:InputStream
        override fun onPreExecute() {
            super.onPreExecute()
            pDialog.setTitle("Loading")
            pDialog.setMessage("image")
            pDialog.show()

        }
        override fun doInBackground(vararg p0: Unit?) {

            var  url = URL(Image_URL)
            isr = url.openStream()
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
              pDialog.dismiss()
              var bmp = BitmapFactory.decodeStream(isr)
              iv.setImageBitmap(bmp)

        }
    }
}