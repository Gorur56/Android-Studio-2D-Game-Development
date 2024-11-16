package com.example.a2dgamedevelopment.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.a2dgamedevelopment.R
import com.example.a2dgamedevelopment.databinding.FragmentGameScreenBinding
import java.util.Timer
import kotlin.concurrent.schedule
import kotlin.math.floor

class GameScreenFragment : Fragment() {
    private lateinit var binding: FragmentGameScreenBinding
    private lateinit var navController: NavController

    //Pozisyonlar
    private var anaKarakterX = 0.0f
    private var anaKarakterY = 0.0f
    private var siyahKareX = 0.0f
    private var siyahKareY = 0.0f
    private var sariDaireX = 0.0f
    private var sariDaireY = 0.0f
    private var kirmiziUcgenX = 0.0f
    private var kirmiziUcgenY = 0.0f

    //Boyutlar
    private var ekranGenisligi = 0
    private var ekranYuksekligi = 0
    private var anaKarakterGenisligi = 0
    private var anaKarakterYuksekligi = 0

    //Kontroller
    private var dokunmaKontrol = true
    private var baslangicKontrol = false

    private val timer = Timer()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_screen, container, false)

       binding.imageViewBlackSquare.x = -800.0f
       binding.imageViewBlackSquare.y = -800.0f

        binding.imageViewYellowCircle.x = -800.0f
        binding.imageViewYellowCircle.y = -800.0f

        binding.imageViewRedTriangle.x = -800.0f
        binding.imageViewRedTriangle.y = -800.0f

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*navController = findNavController()
        binding.imageViewAnaKarakter.setOnClickListener {
            navController.navigate(R.id.action_gameScreenFragment_to_resultScreenFragment)
        }*/

        //Ekrana dokunup dokunmadığını ele alıyoruz.

        binding.cl.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                /*****Ekran ve karakterlerle ilgili bilgi almak istiyorsak bu metot içeriisnde yapmalıyız.
                Log.e("Yükseklik2",(binding.cl.height).toString())
                Log.e("Genişlik2",(binding.cl.width).toString()) ******/

                if( baslangicKontrol ) {
                    if( event?.action == MotionEvent.ACTION_DOWN ) { //Ekrana dokunuyorsa
                        Log.d("MotionEvent","ACTION_DOWN: Ekrana Dokundu.")
                        dokunmaKontrol = true
                    }

                    if( event?.action == MotionEvent.ACTION_UP ) { //Ekranı bıraktığımızda
                        Log.d("MotionEvent", "ACTION_UP: Ekranı bıraktı.")
                        dokunmaKontrol = false
                    }
                } else {
                    //if else ile timer 'ı bir defa çalışmasını sağlıyoruz.baslangicKontrol bir kere true olduğuktan sonra hep if koşulu çalışacak artık.
                    baslangicKontrol = true

                    binding.textViewOyunBasla.visibility = View.INVISIBLE

                    anaKarakterX = binding.imageViewAnaKarakter.x
                    anaKarakterY = binding.imageViewAnaKarakter.y

                    anaKarakterGenisligi = binding.imageViewAnaKarakter.width
                    anaKarakterYuksekligi = binding.imageViewAnaKarakter.height

                    ekranGenisligi = binding.cl.width
                    ekranYuksekligi = binding.cl.height

                    timer.schedule(0,20){ //0: gecikme, 20: Çalışma aralığı
                        Handler(Looper.getMainLooper()).post {
                            anaKarakterHareketEttirme()
                            cisimleriHaraketEttirme()
                        }
                    }
                }
                return true
            }
        })
    }

    private  fun anaKarakterHareketEttirme() {
        //ana karakter ekran büyüklüğüne göre ayrı hizda çalışması için bunu dinamik
        val anaKarakterHiz = ekranYuksekligi / 60.0f

        if(dokunmaKontrol) {
            anaKarakterY -= anaKarakterHiz // Anakarakteri yukarı kaydırma
        } else {
            anaKarakterY += anaKarakterHiz // AnaKarakteri aşağı kaydırma
        }

        if( anaKarakterY <= 0.0f ) {
            anaKarakterY = 0.0f
        }

        if( anaKarakterY >= ekranYuksekligi - anaKarakterYuksekligi ) {
            anaKarakterY = (ekranYuksekligi - anaKarakterYuksekligi).toFloat()
        }
        binding.imageViewAnaKarakter.y = anaKarakterY //En son değeri Anakarakter nesnemize atayıp konumunu değiştiriyoruz.
    }

    private fun cisimleriHaraketEttirme() {
        siyahKareX -= ekranGenisligi / 44.0f //değişme hızı 25 ne kadar büyük olursa o kadar hızlı olur.
        sariDaireX -= ekranGenisligi / 54.0f
        kirmiziUcgenX -= ekranGenisligi / 34.0f

        if( siyahKareX < 0.0f ) { //Sola doğru ekranın dışına kaymasını engelliyoruz.
            siyahKareX = ekranYuksekligi + 20.0f

            siyahKareY = floor(Math.random() * ekranYuksekligi).toFloat() // floor yuvarlama işlemi yapar. Ekran yükseliği arasında rastgele bir değer üretiyoruz.
        }

        binding.imageViewBlackSquare.x = siyahKareX
        //binding.imageViewBlackSquare.y = ekranYuksekligi / 2.0f
        binding.imageViewBlackSquare.y = siyahKareY

        if( sariDaireX < 0.0f ) {
            sariDaireX = ekranYuksekligi + 20.0f
            sariDaireY = floor( Math.random() * ekranYuksekligi).toFloat()
        }

        binding.imageViewYellowCircle.x = sariDaireX
        binding.imageViewYellowCircle.y = sariDaireY

        if( kirmiziUcgenX < 0.0f ) {
            kirmiziUcgenX = ekranYuksekligi + 20.0f
            kirmiziUcgenY = floor(Math.random() * ekranYuksekligi).toFloat()
        }

        binding.imageViewRedTriangle.x = kirmiziUcgenX
        binding.imageViewRedTriangle.y = kirmiziUcgenY

    }
}