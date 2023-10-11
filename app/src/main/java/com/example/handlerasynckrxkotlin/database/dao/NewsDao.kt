package com.example.handlerasynckrxkotlin.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.handlerasynckrxkotlin.database.entity.NewsEntity
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.selects.select

@Dao
interface NewsDao {

    @Insert
    fun addNews(newsEntity: NewsEntity)

    @Query("select * from newsentity")
    fun getAllNews(): Flowable<List<NewsEntity>>
    //rx ning flowable tipi esa databasedagi ozgarishlarni secret
    // yetkazib beradi.yani addNews qilganimizda flowable eshitib turadi
}