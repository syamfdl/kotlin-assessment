package me.syamfdl.myapplication


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_persegi.*
import me.syamfdl.myapplication.databinding.FragmentPersegiBinding

/**
 * A simple [Fragment] subclass.
 */
class PersegiFragment : Fragment() {

    var luasPersegi = 0
    var kelilingPersegi = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPersegiBinding>(inflater, R.layout.fragment_persegi, container, false)

        if (savedInstanceState != null){
            luasPersegi = savedInstanceState.getInt("luas")
            kelilingPersegi = savedInstanceState.getInt("keliling")
        }

        binding.luasp = luasPersegi
        binding.kelilingp = kelilingPersegi

        binding.btnHitung.setOnClickListener {
            val panjangPersegi = etPanjang.text.toString().toDouble()
            val lebarPersegi = etLebar.text.toString().toDouble()

            luasPersegi = (panjangPersegi * lebarPersegi).toInt()
            kelilingPersegi = (2*(panjangPersegi + lebarPersegi)).toInt()

            binding.luasp = luasPersegi
            binding.kelilingp = kelilingPersegi
        }


        binding.btnShare.setOnClickListener {
            getShareIntent()
        }

        return binding.root
    }

    private fun getShareIntent(){
        val mIntent = Intent(Intent.ACTION_SENDTO)
        mIntent.data = Uri.parse("mailto:")

        mIntent.putExtra(Intent.EXTRA_EMAIL, "syamfdl.lvlup@gmail.com")
        mIntent.putExtra(Intent.EXTRA_SUBJECT, "Hasil Perhitungan")
        mIntent.putExtra(Intent.EXTRA_TEXT,
            "Luas persegi = " + luasPersegi +
                    " Keliling persegi = " + kelilingPersegi)

        try {
            startActivity(Intent.createChooser(mIntent,"www.gmail.com"))
        }
        catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("luas", luasPersegi)
        outState.putInt("keliling", kelilingPersegi)
        super.onSaveInstanceState(outState)
    }

}
