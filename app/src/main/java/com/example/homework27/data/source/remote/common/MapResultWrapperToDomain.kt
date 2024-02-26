package com.example.homework27.data.source.remote.common

import com.example.homework27.domain.result_wrapper.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



fun <Dto:Any, DomainEntity :Any> Flow<ResultWrapper<Dto>>.mapResultWrapper(mapper: (Dto) -> DomainEntity):Flow<ResultWrapper<DomainEntity>>{
    return this.map{ resultWrapper ->
        when(resultWrapper){
            is ResultWrapper.Success ->{
                ResultWrapper.Success(data = mapper(resultWrapper.data!!)) // this will never be null because in HandleResponse we never let null body response be wrapped in successful
            }
            is ResultWrapper.Error -> {
                ResultWrapper.Error(errorMessage = resultWrapper.errorMessage?:"EmptyError")
            }
            is ResultWrapper.Loading ->{
                ResultWrapper.Loading(loading = resultWrapper.loading)
            }
        }
    }
}