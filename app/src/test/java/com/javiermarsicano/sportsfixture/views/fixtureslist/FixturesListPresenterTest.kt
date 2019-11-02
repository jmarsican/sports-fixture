package com.javiermarsicano.sportsfixture.views.fixtureslist

import com.javiermarsicano.sportsfixture.data.FixtureRepository
import com.javiermarsicano.sportsfixture.data.models.Fixture
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito.*

class FixturesListPresenterTest {

    private val fixture = Fixture(null, null, "", null, 0, "", "", null)

    @Test
    fun `test nothing`() {
        val data = listOf(fixture)
        val view = mock(FixtureView::class.java)
        val repository = mock(FixtureRepository::class.java)

        val presenter = FixturesListPresenterImpl(repository)
        presenter.onBindView(view)

        `when`(repository.getFixtures()).thenReturn(Single.just(data))

        presenter.getFixtures()

        verify(view).showFixtures(data)
    }
}