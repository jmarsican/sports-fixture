package com.javiermarsicano.sportsfixture.views.fixtureslist

import com.javiermarsicano.sportsfixture.TrampolineSchedulerRule
import com.javiermarsicano.sportsfixture.data.repository.FixtureRepository
import com.javiermarsicano.sportsfixture.views.models.Fixture
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Matchers.anyString
import org.mockito.Mockito.*
import java.io.IOException

class FixturesListPresenterTest {

    @get:Rule
    val schedulersRule = TrampolineSchedulerRule()

    private lateinit var repository: FixtureRepository
    private lateinit var view: FixtureView

    private lateinit var presenter: FixturesListPresenter


    private val fixture = Fixture(id = 0)


    @Before
    fun setUp() {
        view = mock(FixtureView::class.java)
        repository = mock(FixtureRepository::class.java)

        presenter = FixturesListPresenterImpl(repository)
        presenter.onBindView(view)
    }

    @Test
    fun `get and show fixture data successful`() {
        //given
        val data = listOf(fixture)
        `when`(repository.getFixtures()).thenReturn(Single.just(data))

        //when
        presenter.getFixtures()

        //then
        verify(view).showFixtures(data)
        thenShowAndHideProgress()
    }

    @Test
    fun `some error occurs when asking for fixtures`() {
        //given
        `when`(repository.getFixtures()).thenReturn(Single.error(IOException()))

        //when
        presenter.getFixtures()

        //then
        verify(view).onError(anyString())
        thenShowAndHideProgress()
    }

    private fun thenShowAndHideProgress() {
        verify(view).showLoading()
        verify(view).hideLoading()
    }


}