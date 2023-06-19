package com.imranmelikov.zipex.di

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ProgressBar
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.imranmelikov.zipex.db.ZipexDao1
import com.imranmelikov.zipex.db.ZipexDataBase1
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
    context, ZipexDataBase1::class.java,"ZipexDb1"
).build()

    @Singleton
    @Provides
    fun injectDao(zipexDataBase1: ZipexDataBase1)=zipexDataBase1.zipexDao1()

    @Singleton
    @Provides
    fun injectRepo(zipexDao1: ZipexDao1)=ZipexRepoImpl(zipexDao1) as ZipexRepo

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