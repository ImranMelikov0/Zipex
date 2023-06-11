package com.imranmelikov.zipex.di

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ProgressBar
import androidx.room.Dao
import androidx.room.R
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.imranmelikov.zipex.db.ZipexDao
import com.imranmelikov.zipex.db.ZipexDataBase
import com.imranmelikov.zipex.repo.ZipexRepo
import com.imranmelikov.zipex.repo.ZipexRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

@Singleton
@Provides
fun injectDatabase(@ApplicationContext context:Context)=Room.databaseBuilder(
    context,ZipexDataBase::class.java,"ZipexDb"
).build()

    @Singleton
    @Provides
    fun injectDao(zipexDataBase: ZipexDataBase)=zipexDataBase.zipexDao()

    @Singleton
    @Provides
    fun injectRepo(zipexDao: ZipexDao)=ZipexRepoImpl(zipexDao) as ZipexRepo

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context):RequestManager {
        val progressBar = ProgressBar(context)
        val progressBarDrawable: Drawable = progressBar.indeterminateDrawable
        val requestOptions = RequestOptions()
            .placeholder(progressBarDrawable)
            .error(progressBarDrawable)
      return  Glide.with(context).setDefaultRequestOptions(requestOptions)
    }
    }