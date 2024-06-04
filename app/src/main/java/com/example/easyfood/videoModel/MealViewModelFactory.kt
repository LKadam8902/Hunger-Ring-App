package com.example.easyfood.videoModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MealViewModelFactory(
        ):ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun<T:ViewModel> create(modelClass: Class<T>):T{
                 return MealViewModel() as T
            }
}