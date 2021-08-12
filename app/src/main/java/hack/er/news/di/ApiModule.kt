package hack.er.news.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.zacsweers.moshix.reflect.MetadataKotlinJsonAdapterFactory
import hack.er.news.api.HNService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder().baseUrl(HNService.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(MetadataKotlinJsonAdapterFactory()).build()

    @Provides
    fun provideApi(retrofit: Retrofit): HNService = retrofit.create(HNService::class.java)
}
