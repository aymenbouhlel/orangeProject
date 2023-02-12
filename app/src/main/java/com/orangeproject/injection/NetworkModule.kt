package com.orangeproject.injection

import com.orangeproject.orangebank.repository.OrangeApi
import com.orangeproject.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideMarvelApiInterface(retrofit: Retrofit): OrangeApi {
        return retrofit.create(OrangeApi::class.java)
    }

    @Singleton
    @Provides
    internal fun provideRetrofitInterface(httpClient: OkHttpClient): Retrofit {
        return getRetrofitInterface(httpClient)
    }

    /**
     * Retrofit instance only for marvel Api , we can create other
     * named instance for other Api and use it with other Module like Marvel Module
     */
    private fun getRetrofitInterface(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Singleton
    @Provides
    internal fun provideOkHttpClient(
    ): OkHttpClient {
        return getOkHttpClient()
    }

    private fun getOkHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(createLoggingInterceptor())

        return builder.build()
    }

    private fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


}