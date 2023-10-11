package com.example.handlerasynckrxkotlin

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Adapter
import com.example.handlerasynckrxkotlin.database.adapters.NewsAdapter
import com.example.handlerasynckrxkotlin.database.entity.AppDatabase
import com.example.handlerasynckrxkotlin.database.entity.NewsEntity
import com.example.handlerasynckrxkotlin.databinding.ActivityMainBinding
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.ScheduledFuture

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)
        newsAdapter = NewsAdapter()

        binding.apply {
            rv.adapter = newsAdapter

            //bu yerda newsdao getAllnews orqali xamma malumotlarni oladi
            //io threadni ozida orqa fonda bajarib bizga malumotni keltirib beradi
            //subscribe esa malumotni oliib chiqib beradi

            appDatabase.newsDao()
                .getAllNews()
                .subscribeOn(Schedulers.io())
                .subscribe{
                    newsAdapter.submitList(it)
                }

            saveBtn.setOnClickListener {
                val title = titleEt.text.toString()
                val desc = descEt.text.toString()

                //databasega shunaqa qilinib malumot qoshiladi
                appDatabase.newsDao().addNews(NewsEntity(title = title, description = desc))

            }
        }

    }
}