package com.example.cryptochallenge.repository

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
abstract class CryptoModule {

    @Binds
    abstract fun providesCryptoRepository(cryptoRepositoryImpl: CryptoRepositoryImp): CryptoRepositoryInterface

    companion object{

        @Provides
        fun provideBaseUrl(): String = "https://api.bitso.com/v3/"

        @Provides
        fun providesOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val client: OkHttpClient = OkHttpClient.Builder()
                        .addNetworkInterceptor(HttpLoggingInterceptor().also {
                            it.setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                        ).build()

                    val original = chain.request()
                    val request: Request = original.newBuilder()
                        .header("User-Agent", original.url.host)
                        .method(original.method, original.body)
                        .build()

                    client.newCall(request).execute()
                }.build()

        @Provides
        fun providesRetrofitInstance(client: OkHttpClient, baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        fun providesApiService(retrofit: Retrofit) = retrofit.create(ApiServiceInterface::class.java)

    }

}