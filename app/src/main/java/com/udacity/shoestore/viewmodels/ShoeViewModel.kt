package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    var newShoe = Shoe(description = "", name = "", size = 0.0, company = "")

    private val _shoeList: MutableList<Shoe> = mutableListOf()
    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    fun addShoe(shoe: Shoe){
        _shoeList.add(shoe)
        _shoes.value = _shoeList
    }
}