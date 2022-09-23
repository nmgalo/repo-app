package dev.nmgalo.repo.data

import dev.nmgalo.repo.data.model.search.SearchReposResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubNetworkApi {

    @GET("users/{userName}/repos")
    suspend fun userRepositories(
        @Path("userName") userName: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<SearchReposResponse>

    @GET("repos/{owner}/{name}")
    suspend fun getRepos(@Path("owner") owner: String, @Path("name") name: String): Nothing
}
