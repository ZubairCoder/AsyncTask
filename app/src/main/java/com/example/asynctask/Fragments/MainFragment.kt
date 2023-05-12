package com.example.asynctask.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.asynctask.R
import com.example.asynctask.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var binding : FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentMainBinding.inflate(LayoutInflater.from(context),container,false)
        // Inflate the layout for this fragment
        binding?.btnsimple?.setOnClickListener {
            findNavController().navigate(R.id.simpleFragment)
        }
        binding?.btnAsync?.setOnClickListener {
            findNavController().navigate(R.id.asyncFragment)
        }
        return binding?.root
    }

}