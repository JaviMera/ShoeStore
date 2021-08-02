package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MarginLayoutParamsCompat
import androidx.core.view.marginTop
import androidx.core.view.updateMargins
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_list, container, false )
        binding.fab.setOnClickListener{

            findNavController().navigate(ShoeListFragmentDirections.actionShoelistDestinationToShoeDetailFragment())
            /*val linearLayout = binding.shoeList
            val view = LayoutInflater.from(context).inflate(R.layout.shoe_detail, linearLayout, false)
            val shoeDescription = view.findViewById<TextView>(R.id.shoe_description)
            shoeDescription.text = "A nice shoe"
            linearLayout.addView(view)
            */
        }
        return binding.root
    }
}