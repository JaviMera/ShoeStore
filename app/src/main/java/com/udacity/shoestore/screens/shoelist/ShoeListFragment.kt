package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
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

        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_list, container, false )

        shoeViewModel.shoes.observe(viewLifecycleOwner, { shoes ->

            shoes.map { shoe ->
                val view = LayoutInflater.from(context).inflate(R.layout.shoe_detail, binding.shoeList, false)
                val shoeDescription = view.findViewById<TextView>(R.id.shoe_detail_description_value)
                val shoeName = view.findViewById<TextView>(R.id.shoe_detail_name_value)
                val shoeSize = view.findViewById<TextView>(R.id.shoe_detail_size_value)
                val shoeCompany = view.findViewById<TextView>(R.id.shoe_detail_company_value)

                shoeDescription.text = shoe.description
                shoeName.text = shoe.name
                shoeSize.text = shoe.size.toString()
                shoeCompany.text = shoe.company

                binding.shoeList.addView(view)
            }
        })

        binding.fab.setOnClickListener{

            findNavController().navigate(ShoeListFragmentDirections.actionShoelistDestinationToShoeDetailFragment())
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoe_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.login_destination -> findNavController().navigate(ShoeListFragmentDirections.actionShoelistDestinationToLoginDestination())
        }

        return super.onOptionsItemSelected(item)
    }
}