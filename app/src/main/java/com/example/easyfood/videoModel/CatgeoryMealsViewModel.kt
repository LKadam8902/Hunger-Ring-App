package com.example.easyfood.videoModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.pojo.MealsByCategory
import com.example.easyfood.pojo.MealsByCategoryList
import com.example.easyfood.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel:ViewModel() {
    val mealsLiveData=MutableLiveData<List<MealsByCategory>>()
    fun getMealsByCategory(categoryName:String)
    {
        RetrofitInstance.api.getMealsByCategory(categoryName).enqueue(object:Callback<MealsByCategoryList>{
            override fun onResponse(
                call: Call<MealsByCategoryList>,
                response: Response<MealsByCategoryList>
            ) {
              response.body()?.let { mealsList->
                  mealsLiveData.postValue(mealsList.meals)

              }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
              Log.d("CategoryMealsViewModel",t.message.toString())
            }

        })
    }

    fun observeMealsListData():LiveData<List<MealsByCategory>>{
        return mealsLiveData
    }
}