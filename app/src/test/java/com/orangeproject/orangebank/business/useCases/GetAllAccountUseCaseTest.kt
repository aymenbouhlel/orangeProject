package com.orangeproject.orangebank.business.useCases

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.orangeproject.orangebank.business.models.OrangeAccount
import com.orangeproject.orangebank.repository.OrangeApi
import com.orangeproject.orangebank.repository.OrangeRepository
import com.orangeproject.utils.ResponseHTTP
import junit.framework.TestCase.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetAllAccountUseCaseTest {

    private val orangeApi: OrangeApi = mock()
    @Mock
    lateinit var orangeRepository: OrangeRepository

    private lateinit var getAllAccountUseCase: GetAllAccountUseCase
    private val account = OrangeAccount("1", "Aymen", "Active", "url")

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getAllAccountUseCase = GetAllAccountUseCase(orangeRepository)
    }

    @Test
    fun `GetAllAccountUseCase returns expected success response`() = runBlocking {
        getAllAccountUseCase().let {
            when (it) {
                is ResponseHTTP.Loading<*> -> assertTrue(it is ResponseHTTP.Loading<*> )
                is ResponseHTTP.Success<*>  -> { assertTrue(it is ResponseHTTP.Success<*> )
                    assertEquals(listOf(account), it.data)
                }
                is ResponseHTTP.Error<*>  -> fail("UseCase returned Error instead of Success")
            }
        }
    }

    @Test
    fun `GetAllAccountUseCase returns expected error response`() = runBlocking {
        // Given
        val expectedErrorMessage = "Error"
        try {
            doReturn(throw Exception(expectedErrorMessage)).`when`(orangeApi).getAllAccount()
        }catch (e:Exception){

        }
        // When
        val flow = getAllAccountUseCase.invoke()
        val response = flow.first()

        // Then
        Assert.assertFalse(response is ResponseHTTP.Error)
        Assert.assertEquals(null, response.message)
    }
    }