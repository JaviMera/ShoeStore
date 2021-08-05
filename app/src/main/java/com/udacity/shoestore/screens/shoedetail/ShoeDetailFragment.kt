package com.udacity.shoestore.screens.shoedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeViewModel

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val shoeViewModel: ShoeViewModel by activityViewModels()
    var shoe = Shoe("", 0.0, "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.lifecycleOwner = this

        if (savedInstanceState != null) {
            if(savedInstanceState.containsKey(NEW_SHOE_KEY)){
                shoe = savedInstanceState.getParcelable(NEW_SHOE_KEY)!!
            }
        }
        
        binding.shoe = shoe

        binding.buttonCancelShoe.setOnClickListener {
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoedetailDestinationToShoelistDestination())
        }

        binding.buttonSaveShoe.setOnClickListener {
            when {
                shoe.name.isEmpty() -> {
                    showError(R.string.shoe_name_error)
                }
                shoe.company.isEmpty() -> {
                    showError(R.string.shoe_company_error)
                }
                shoe.size.equals(0.0) -> {
                    showError(R.string.shoe_size_error)
                }
                shoe.description.isEmpty() -> {
                    showError(R.string.shoe_description_error)
                }
                else -> {
                    shoeViewModel.addShoe(shoe)
                    findNavController().navigate(ShoeDetailFragmentDirections.actionShoedetailDestinationToShoelistDestination())
                }
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(NEW_SHOE_KEY, shoe)
        super.onSaveInstanceState(outState)
    }

    private fun showError(messageId: Int){
        Toast.makeText(context, getString(messageId), Toast.LENGTH_SHORT).show()
    }

    companion object{
        const val NEW_SHOE_KEY = "SHOE_VARIABLE"
    }
}