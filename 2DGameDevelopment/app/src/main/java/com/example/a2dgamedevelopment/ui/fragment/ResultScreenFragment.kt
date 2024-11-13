package com.example.a2dgamedevelopment.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.a2dgamedevelopment.R
import com.example.a2dgamedevelopment.databinding.FragmentResultScreenBinding

class ResultScreenFragment : Fragment() {
    private lateinit var binding: FragmentResultScreenBinding
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result_screen, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        binding.buttonTekrarDene.setOnClickListener {
            navController.navigate(R.id.action_resultScreenFragment_to_mainFragment)
        }
    }
}