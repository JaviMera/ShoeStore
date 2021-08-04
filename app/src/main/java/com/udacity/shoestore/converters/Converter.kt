package com.udacity.shoestore.converters

import androidx.databinding.InverseMethod

object Converter {

    @InverseMethod("stringToSize")
    @JvmStatic
    fun sizeToString(size: Double) : String{

        if(size.equals(0.0))
            return ""

        return size.toString()
    }

    @JvmStatic
    fun stringToSize(size: String) : Double{

        if(size.isEmpty())
            return 0.0

        return size.toDouble()
    }
}