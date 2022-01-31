package com.example.mvvmglobalnewsapp.ui.articleContent

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mvvmglobalnewsapp.R
import com.example.mvvmglobalnewsapp.ui.home.ARTICLE_CONTENT
import com.example.mvvmglobalnewsapp.ui.home.ARTICLE_TITLE
import com.example.mvvmglobalnewsapp.ui.home.ARTICLE_URL_TO_IMAGE
import org.w3c.dom.Text

class ArticleContentActivity : AppCompatActivity() {

    private lateinit var textViewTitle: TextView
    private lateinit var textViewContent: TextView
    private lateinit var imageViewUrlToImage: ImageView

    private lateinit var urlToImage: String
    private lateinit var title: String
    private lateinit var content: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_content)

        init()
        retriveArgs()

        textViewTitle.text = title
        textViewContent.text = content
        Glide.with(this).load(urlToImage).into(imageViewUrlToImage)

    }

    private fun init(){
        textViewTitle = this.findViewById(R.id.textView_article_title)
        textViewContent = this.findViewById(R.id.textView_article_content)
        imageViewUrlToImage = this.findViewById(R.id.imageView_article_urlToImage)
    }

    private fun retriveArgs() {
        val pageIntent = intent
        urlToImage = pageIntent.getStringExtra(ARTICLE_URL_TO_IMAGE).toString()
        title = pageIntent.getStringExtra(ARTICLE_TITLE).toString()
        content = pageIntent.getStringExtra(ARTICLE_CONTENT).toString()
    }
}


























