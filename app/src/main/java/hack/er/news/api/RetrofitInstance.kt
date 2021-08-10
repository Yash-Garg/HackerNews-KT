package hack.er.news.api

import com.squareup.moshi.Moshi
import dev.zacsweers.moshix.reflect.MetadataKotlinJsonAdapterFactory
import hack.er.news.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    private val moshi = Moshi.Builder().add(MetadataKotlinJsonAdapterFactory()).build()

    private val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

    val api: HNService by lazy { retrofit.create(HNService::class.java) }
}
