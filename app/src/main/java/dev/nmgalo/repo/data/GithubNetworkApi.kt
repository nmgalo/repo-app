package dev.nmgalo.repo.data

import dev.nmgalo.repo.data.model.search.RepoDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubNetworkApi {

    @GET("users/{owner}/repos")
    suspend fun userRepositories(
        @Path("owner") owner: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<RepoDetailDTO>

    @GET("repos/{owner}/{name}")
    suspend fun getRepo(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): RepoDetailDTO
}
