package dev.nmgalo.repo.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.nmgalo.repo.BuildConfig
import dev.nmgalo.repo.data.model.search.RepoDetailDTO
import dev.nmgalo.repo.domain.search.model.SearchReposRequest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubNetworkClient @Inject constructor(json: Json) : GithubNetworkDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    private val networkApi = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .addInterceptor { chain ->
                    chain.proceed(
                        chain
                            .request()
                            .newBuilder()
                            .addHeader("Authorization", "Bearer ${BuildConfig.GITHUB_API_KEY}")
                            .build()
                    )
                }
                .build()
        )
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(GithubNetworkApi::class.java)

    override suspend fun getUserRepositories(searchReposRequest: SearchReposRequest): List<RepoDetailDTO> {
        return networkApi.userRepositories(
            owner = searchReposRequest.owner,
            page = searchReposRequest.page,
            perPage = searchReposRequest.perPage
        )
    }

    override suspend fun getRepositoryDetails(owner: String, name: String): RepoDetailDTO {
        return networkApi.getRepo(owner, name)
    }
}
