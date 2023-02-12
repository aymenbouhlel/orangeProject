package com.orangeproject.orangebank.business.useCases

import com.nhaarman.mockitokotlin2.whenever
import com.orangeproject.orangebank.business.models.OrangeAccount
import com.orangeproject.orangebank.repository.OrangeRepository
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetAllAccountUseCaseTest {

    @Mock
    lateinit var myRepository: OrangeRepository

    private lateinit var gtAllAccountUseCase: GetAllAccountUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        gtAllAccountUseCase = GetAllAccountUseCase(myRepository)
    }



    @Test
    fun `should Return list of OrangeAccount when succes`() = runBlockingTest {
        val resutSuccess =
            listOf(
                OrangeAccount(
                    accountId = "123",
                    nickname = "Aymen",
                    status = "Enabled",
                    transactionsUrl = "UrlListTransaction"
                )
            )
        whenever(myRepository.getAllAccount() ).thenReturn(resutSuccess)

        gtAllAccountUseCase.invoke()
      //      { response  ->

        //    (response as ResponseHTTP.Success<List<OrangeAccount>>(resutSuccess)){


     //   }


   //     }
    }

    //   whenever(
    //            newsRepository.getAllUsers()
    //        ).thenReturn(BaseResponse.Success(resutSuccess))
    //
    //        getAllUsersUseCase.run(BaseUseCase.None()).let { useCaseResponse ->
    //            if (useCaseResponse is BaseResponse.Success<List<UsersResponse>?>) {
    //                Assert.assertEquals(BaseResponse.Success(resutSuccess).obj, useCaseResponse.obj)
    //            }
    //        }

}