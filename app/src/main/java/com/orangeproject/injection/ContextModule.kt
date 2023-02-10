package com.orangeproject.injection

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ContextModule {
    @Singleton
    @Provides
    internal fun provideContext(fdjInjectionApplication: Application): Context =
        fdjInjectionApplication.applicationContext
}