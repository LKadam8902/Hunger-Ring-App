package com.example.easyfood.videoModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.pojo.Category
import com.example.easyfood.pojo.CategoryList
import com.example.easyfood.pojo.Meal
import com.example.easyfood.pojo.MealList
import com.example.easyfood.pojo.MealsByCategory
import com.example.easyfood.pojo.MealsByCategoryList
import com.example.easyfood.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
) : ViewModel() {

    private var randomMealLiveData = MutableLiveData<Meal>()
    private var categoriesLiveData = MutableLiveData<List<Category>>()
    private var bottomSheetMealLiveData = MutableLiveData<Meal>()
    private val searchedMealsLiveData = MutableLiveData<List<Meal>>()
    private val popularItemsLiveData = MutableLiveData<List<MealsByCategory>>()


    fun getRandomMeal() {
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                }
                return
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("getRandom", "Not working")
            }

        })
    }

    fun getPopularItems() {
        RetrofitInstance.api.getPopularItem("Seafood").enqueue(object : Callback<MealsByCategoryList> {
            override fun onResponse(call: Call<MealsByCategoryList>, response: Response<MealsByCategoryList>) {
                if (response.body() != null) {
                    popularItemsLiveData.value = response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.d("getPopular", "Not Working")
            }

        })
    }

    fun getCategories() {
        RetrofitInstance.api.getCategoryList().enqueue(object : Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if (response.body() != null) {
                    categoriesLiveData.value = response.body()!!.categories
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })
    }

    fun getMealById(id: String) {
        RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                val meal = response.body()?.meals?.first()
                meal?.let { meal ->
                    bottomSheetMealLiveData.postValue(meal)
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HomeViewModel", t.message.toString())
            }
        })
    }


    fun searchMeals(searchQuery: String) {
        RetrofitInstance.api.searchMeals(searchQuery).enqueue(
            object : Callback<MealList> {
                override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                    val mealsList = response.body()?.meals
                    mealsList?.let {
                        searchedMealsLiveData.postValue(it)
                    }
                }

                override fun onFailure(call: Call<MealList>, t: Throwable) {
                    Log.d("HomeViewModel", t.message.toString())
                }

            }
        )
    }

    fun observeSearchMealsLiveData(): LiveData<List<Meal>> {
        return searchedMealsLiveData
    }

    fun observeRandomMealLiveData(): LiveData<Meal> {
        return randomMealLiveData
    }

    fun observePopularLiveData(): LiveData<List<MealsByCategory>> {
        return popularItemsLiveData
    }

    fun observeCategoriesLiveData(): LiveData<List<Category>> {
        return categoriesLiveData
    }

    /*fun observeFavouritesMealsLiveData(): LiveData<List<Meal>> {
        return favouritesMealsLiveData
    }*/

    fun observeBottomSheetMeal(): LiveData<Meal> {
        return bottomSheetMealLiveData
    }

}



