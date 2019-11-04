package com.javiermarsicano.sportsfixture.views.results

import com.javiermarsicano.sportsfixture.TrampolineSchedulerRule
import com.javiermarsicano.sportsfixture.data.repository.ResultsRepository
import com.javiermarsicano.sportsfixture.views.fixtureslist.ResultsPresenter
import com.javiermarsicano.sportsfixture.views.fixtureslist.ResultsView
import com.javiermarsicano.sportsfixture.views.models.Fixture
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Matchers.anyString
import org.mockito.Mockito.*
import java.io.IOException

class ResultsPresenterTest {

    @get:Rule
    val schedulersRule = TrampolineSchedulerRule()

    private lateinit var repository: ResultsRepository
    private lateinit var view: ResultsView

    private lateinit var presenter: ResultsPresenter


    private val fixture = Fixture(id = 0)


    @Before
    fun setUp() {
        view = mock(ResultsView::class.java)
        repository = mock(ResultsRepository::class.java)

        presenter = ResultsPresenterImpl(repository)
        presenter.onBindView(view)
    }

    @Test
    fun `get and show Results data successful`() {
        //given
        val data = listOf(fixture)
        `when`(repository.getResults()).thenReturn(Single.just(data))

        //when
        presenter.getResults()

        //then
        verify(view).showResults(data)
        thenShowAndHideProgress()
    }

    @Test
    fun `some error occurs when asking for Results`() {
        //given
        `when`(repository.getResults()).thenReturn(Single.error(IOException()))

        //when
        presenter.getResults()

        //then
        verify(view).onError(anyString())
        thenShowAndHideProgress()
    }

    private fun thenShowAndHideProgress() {
        verify(view).showLoading()
        verify(view).hideLoading()
    }


}