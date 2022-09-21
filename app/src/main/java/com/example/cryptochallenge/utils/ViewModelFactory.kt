package com.example.cryptochallenge.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory<UC>(private val useCaseClass:UC):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(useCaseClass!!::class.java)
            .newInstance(useCaseClass)
    }
}