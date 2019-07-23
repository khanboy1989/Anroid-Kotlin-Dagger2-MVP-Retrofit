package net.cocooncreations.dimvparchitecture.di.modules

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import net.cocooncreations.dimvparchitecture.network.PostApi
import net.cocooncreations.dimvparchitecture.utils.Constants
import net.cocooncreations.dimvparchitecture.utils.Feeds
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetModule {


    /*
     Header Initialization
     */

    @Provides
    fun getHeaders():HashMap<String,String>{
        val params = HashMap<String,String>()
        params.put("Content-Type", "application/json")
        return params
    }

    @Provides
    protected fun provideHttpLoggingInterceptor():HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return  interceptor
    }

    @Provides
    fun provideRetrofit(gson: Gson,okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(Feeds.BASE_URL)
            .build()
    }

    @Provides
    fun provideNetworkService(retrofit: Retrofit):PostApi{
        return retrofit.create(PostApi::class.java)
    }

    @Provides
    fun getTimeOut():Int{
        return 30
    }

    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }


    @Provides
    fun provideOkHttpClientDefault(interceptor: HttpLoggingInterceptor,headers: HashMap<String,String>,timeOut:Int):OkHttpClient{

        val okBuilder = OkHttpClient.Builder()
        okBuilder.addInterceptor(interceptor)
        okBuilder.addInterceptor {
            chain ->
            val request = chain.request()
            val builder = request.newBuilder()

            if(headers != null && headers.size > 0){
                for ((key, value) in headers) {
                    builder.addHeader(key, value)
                    Log.e(key, value)
                }
            }

            chain.proceed(builder.build())
        }

        okBuilder.connectTimeout(timeOut.toLong(),TimeUnit.SECONDS)
        okBuilder.readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
        okBuilder.writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)

        return okBuilder.build()
    }

}