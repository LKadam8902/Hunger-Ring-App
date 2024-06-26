package com.example.easyfood.retrofit

import com.example.easyfood.pojo.CategoryList
import com.example.easyfood.pojo.MealList
import com.example.easyfood.pojo.MealsByCategoryList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("lookup.php")
    fun getMealDetails(@Query("i") id: String): Call<MealList>

    @GET("filter.php")
    fun getPopularItem(@Query("c") categoryName: String): Call<MealsByCategoryList>

    @GET("categories.php")
    fun getCategoryList(): Call<CategoryList>

    @GET("filter.php")
    fun getMealsByCategory(@Query("c") categoryName: String): Call<MealsByCategoryList>

    @GET("search.php?")
    fun searchMeals(@Query("f") searchQuery: String): Call<MealList>

}