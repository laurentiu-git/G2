package com.rld.g2esports.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rld.g2esports.data.models.news.NewsResponse
import com.rld.g2esports.repository.ShopItemsRepository
import com.rld.g2esports.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel @ViewModelInject constructor(
    private val shopItemsRepository: ShopItemsRepository
) : ViewModel(){

    val newsItem: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    init {
        getNewsItems()
    }


    private fun getNewsItems() = viewModelScope.launch {
        newsItem.postValue(Resource.Loading())
         val response = shopItemsRepository.getNews()
        newsItem.postValue(newsItemsResponse(response))
}

    private  fun newsItemsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if    (response.isSuccessful) {
            response.body()?.let {resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}