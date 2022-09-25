package dev.nmgalo.repo.domain.repo

import dev.nmgalo.repo.domain.search.model.RepoDetails
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class SaveToFavouritesUseCaseTest {

    private val repo = mockk<RepoRepository>()

    private val getLocalRepoDetailsUseCase: GetLocalRepoDetailsUseCase =
        GetLocalRepoDetailsUseCase(repo)

    private val fakeRepo = RepoDetails("", "", "")

    private val flowOfNull = flowOf(null)

    private val fakeRepoDetails = RepoDetails(
        owner = "nmgalo",
        avatar = "https://avatars.githubusercontent.com/u/33604954?v=4",
        repositoryName = "repo-app"
    )

    @Before
    fun setUp() {
        coEvery { repo.getDetails("", "") } returns fakeRepo
        coEvery { getLocalRepoDetailsUseCase.execute("test", "ninja") } returns flowOfNull
        coEvery {
            getLocalRepoDetailsUseCase.execute("nmgalo", "repo-app")
        } returns flowOf(fakeRepoDetails)
        coEvery { repo.saveToFavourites(fakeRepo) } returns Unit
    }

    @Test
    fun `should return flow of null if records not found in database`() = runTest {
        assertEquals(getLocalRepoDetailsUseCase.execute("test", "ninja"), flowOfNull)
    }

    @Test
    fun `should return valid repository if exists`() = runTest {
        getLocalRepoDetailsUseCase.execute("nmgalo", "repo-app").collect {
            assertEquals(
                it?.avatar,
                fakeRepoDetails.avatar
            )
        }
    }
}
