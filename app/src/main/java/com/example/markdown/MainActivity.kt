package com.example.markdown

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import io.noties.markwon.Markwon
import io.noties.markwon.linkify.LinkifyPlugin


class MainActivity : AppCompatActivity() {

    lateinit var markwon: Markwon
    var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //markwon = Markwon.create(this)

        markwon = Markwon.builder(this).usePlugin(LinkifyPlugin.create()).build()

        /*markwon = Markwon.builder(this)
            .usePlugin(object : AbstractMarkwonPlugin() {
                override fun configureConfiguration(builder: MarkwonConfiguration.Builder) {
                    builder.linkResolver { view, link ->
                        Log.d("MINA", link)
                        showToast(link)
                    }
                    builder.linkResolver(object : LinkResolverDef() {
                        override fun resolve(view: View, link: String) {
                            Log.d("MINA", link)
                            showToast(link)
                            super.resolve(view, link)
                        }
                    })
                    super.configureConfiguration(builder)
                }
            })
            .build()*/

        val tv = findViewById<MaterialTextView>(R.id.tv)
        tv.movementMethod =
            LinkMovementMethod.getInstance() // to enable select and click on the link at the same time

        val txt =
            "**Hello there!** [Click here to renew your ID](https://www.google.com) https://www.facebook.com"

        markwon.setMarkdown(tv, txt)
    }

    @SuppressLint("ShowToast")
    fun showToast(txt: String) {
        toast?.cancel()
        toast = Toast.makeText(this, txt, Toast.LENGTH_LONG)
        toast?.show()
    }
}