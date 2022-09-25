package dev.nmgalo.repo.domain.repo

import dev.nmgalo.repo.domain.search.model.RepoDetails
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class SaveToFavouritesUseCaseTest {

    private val repo = mockk<RepoRepository>()

    private val getLocalRepoDetailsUseCase: GetLocalRepoDetailsUseCase =
        GetLocalRepoDetailsUseCase(repo)

    private val fakeRepo = RepoDetails("", "", "")

    fun setUp() {
        coEvery { repo.getDetails("", "") } returns fakeRepo
        coEvery { getLocalRepoDetailsUseCase.execute("", "") } returns flowOf(null)
        coEvery { repo.saveToFavourites(fakeRepo) } returns Unit
    }

    @Test
    fun `should write in database when there isn't existing record`() = runTest {
        getLocalRepoDetailsUseCase.execute("", "")
    }

}
