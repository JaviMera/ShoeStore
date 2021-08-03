package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import android.util.Log
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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.viewmodels.ShoeViewModel

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val shoeViewModel: ShoeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_list, container, false )

        shoeViewModel.shoes.observe(viewLifecycleOwner, { shoes ->

            shoes.map { shoe ->
                val view = LayoutInflater.from(context).inflate(R.layout.shoe_detail, binding.shoeList, false)
                val shoeDescription = view.findViewById<TextView>(R.id.shoe_description)
                shoeDescription.text = shoe.description
                binding.shoeList.addView(view)
            }
        })

        binding.fab.setOnClickListener{

            findNavController().navigate(ShoeListFragmentDirections.actionShoelistDestinationToShoeDetailFragment())
        }
        return binding.root
    }
}