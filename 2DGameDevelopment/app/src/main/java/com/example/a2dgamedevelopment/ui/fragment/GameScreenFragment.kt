package com.example.a2dgamedevelopment.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.a2dgamedevelopment.R
import com.example.a2dgamedevelopment.databinding.FragmentGameScreenBinding

class GameScreenFragment : Fragment() {
    private lateinit var binding: FragmentGameScreenBinding
    private lateinit var navController: NavController
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_screen, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*navController = findNavController()
        binding.imageViewAnaKarakter.setOnClickListener {
            navController.navigate(R.id.action_gameScreenFragment_to_resultScreenFragment)
        }*/

        Log.e("Yükseklik",(binding.cl.height).toString())
        Log.e("Genişlik",(binding.cl.width).toString())

        //Ekrana dokunup dokunmadığını ele alıyoruz.
        binding.cl.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                /*****Ekran ve karakterlerle ilgili bilgi almak istiyorsak bu metot içeriisnde yapmalıyız.******/
                Log.e("Yükseklik2",(binding.cl.height).toString())
                Log.e("Genişlik2",(binding.cl.width).toString())

                if( event?.action == MotionEvent.ACTION_DOWN ) { //Ekrana dokunuyorsa
                    Log.d("Merhaba","ACTION_DOWN: Ekrana Dokundu.")
                }

                if( event?.action == MotionEvent.ACTION_UP ) { //Ekranı bıraktığımızda
                    Log.d("Merhaba", "ACTION_UP: Ekranı bıraktı.")
                }

                return true
            }

        })
    }
}