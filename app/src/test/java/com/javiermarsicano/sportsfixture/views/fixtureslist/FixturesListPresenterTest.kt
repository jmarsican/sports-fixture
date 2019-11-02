package com.javiermarsicano.sportsfixture.views.fixtureslist

import com.javiermarsicano.sportsfixture.data.FixtureRepository
import com.javiermarsicano.sportsfixture.data.models.Fixture
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Matchers.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.io.IOException

class FixturesListPresenterTest {

    @get:Rule
    val schedulersRule = TrampolineSchedulerRule()

    private lateinit var repository: FixtureRepository
    private lateinit var view: FixtureView

    private lateinit var presenter: FixturesListPresenter


    private val fixture = Fixture(null,
            null, "",
            null,
            0,
            "",
            "",
            null)


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