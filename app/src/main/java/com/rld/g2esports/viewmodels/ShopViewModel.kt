package com.rld.g2esports.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rld.g2esports.data.models.shop.ShopResponse
import com.rld.g2esports.repository.ShopItemsRepository
import com.rld.g2esports.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class ShopViewModel @ViewModelInject constructor(
    private val shopItemsRepository: ShopItemsRepository
) : ViewModel(){

    val shopItems: MutableLiveData<Resource<ShopResponse>> = MutableLiveData()
    val bestSellingItems: MutableLiveData<Resource<ShopResponse>> = MutableLiveData()

    init {
        getShoppingItems("latest")
        getBestSellingItems("best_selling")
    }


    private fun getShoppingItems(itemToSearch: String) = viewModelScope.launch {
    shopItems.postValue(Resource.Loading())
    val response = shopItemsRepository.getShopItems(itemToSearch)
    shopItems.postValue(shopItemsResponse(response))
}

    private fun getBestSellingItems(itemToSearch: String) = viewModelScope.launch {
        bestSellingItems.postValue(Resource.Loading())
        val response = shopItemsRepository.getShopItems(itemToSearch)
        bestSellingItems.postValue(shopItemsResponse(response))
    }

    private fun shopItemsResponse(response: Response<ShopResponse>): Resource<ShopResponse> {
        if(response.isSuccessful) {
            response.body()?.let {resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}