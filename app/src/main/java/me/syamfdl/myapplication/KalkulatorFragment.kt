package me.syamfdl.myapplication


import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_kalkulator.*
import me.syamfdl.myapplication.databinding.FragmentKalkulatorBinding

/**
 * A simple [Fragment] subclass.
 */
class KalkulatorFragment : Fragment() {

    private var nilaiAkhir = 0
    private var indeks = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentKalkulatorBinding>(inflater, R.layout.fragment_kalkulator, container, false)

        binding.tvNilaiAkhir.visibility = View.GONE
        binding.ivMood.visibility = View.GONE
        binding.tvIndex.visibility = View.GONE
        binding.btnShare.visibility = View.GONE

        if (savedInstanceState != null) {
            nilaiAkhir = savedInstanceState.getInt("nilaiakhir" , 0)
            indeks = savedInstanceState.getString("indek", "")
            binding.tvNilaiAkhir.visibility = View.VISIBLE
            binding.ivMood.visibility = View.VISIBLE
            binding.tvIndex.visibility = View.VISIBLE
            binding.btnShare.visibility = View.VISIBLE
        }



        binding.nilaiakhir = nilaiAkhir
        binding.indeks = indeks

        binding.btnHitung.setOnClickListener {
            binding.tvNilaiAkhir.visibility = View.VISIBLE
            binding.ivMood.visibility = View.VISIBLE
            binding.tvIndex.visibility = View.VISIBLE
            binding.btnShare.visibility = View.VISIBLE

            if  (!TextUtils.isEmpty(etNama.text.toString())
                && !TextUtils.isEmpty(etPraktikum.text.toString())
                && !TextUtils.isEmpty(etAssessment1.text.toString())
                && !TextUtils.isEmpty(etAssessment2.text.toString())
                && isRange(etPraktikum.text.toString().toFloat())
                && isRange(etAssessment1.text.toString().toFloat())
                && isRange(etAssessment2.text.toString().toFloat()))
                {
                nilaiAkhir = ((0.3 * etPraktikum.text.toString().toFloat()) + (0.3 * etAssessment1.text.toString().toFloat())).toInt()

                if  (binding.rbYa.isChecked){
                    nilaiAkhir += (0.4 * etAssessment2.text.toString().toFloat()).toInt()
                }


                if  (nilaiAkhir <= 40){
                    indeks = "AB"
                } else if (nilaiAkhir <= 50) {
                    indeks = "D"
                } else if (nilaiAkhir <= 60) {
                    indeks = "C"
                } else if (nilaiAkhir <= 65) {
                    indeks = "BC"
                } else if (nilaiAkhir <= 70) {
                    indeks = "B"
                } else if (nilaiAkhir <= 80){
                    indeks = "AB"
                } else if (nilaiAkhir <= 100) {
                    indeks = "A"
                }


                if (nilaiAkhir < 50 ){
                    binding.ivMood.setImageResource(R.drawable.ic_mood_bad)
                    binding.tvIndex.setTextColor(Color.parseColor("#FFFF4081"))
                } else {
                    binding.ivMood.setImageResource(R.drawable.ic_mood)
                    binding.tvIndex.setTextColor(Color.parseColor("#FF3F51B5"))
                }

            } else {
                Toast.makeText(context, "Harap isi semua form", Toast.LENGTH_LONG).show()
            }
            binding.nilaiakhir = nilaiAkhir
            binding.indeks = indeks
        }

        binding.btnRiset.setOnClickListener {
            binding.etNama.text.clear()
            binding.etPraktikum.text.clear()
            binding.etAssessment1.text.clear()
            binding.etAssessment2.text.clear()
        }

        binding.btnShare.setOnClickListener {
            val mIntent = Intent(Intent.ACTION_SENDTO)
            mIntent.data = Uri.parse("mailto:")

            mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("syamfdl.lvlup@gmail.com"))
            mIntent.putExtra(Intent.EXTRA_SUBJECT, "Nilai Mahasiswa")
            mIntent.putExtra(Intent.EXTRA_TEXT, "Nilai akhir kamu adalah = $nilaiAkhir dengan index = $indeks")

            startActivity(Intent.createChooser(mIntent, "https://gmail.com"))
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("nilaiakhir", nilaiAkhir)
        outState.putString("indek", indeks)
        super.onSaveInstanceState(outState)
    }


    fun isRange(a : Float) : Boolean {
        return a >= 0 && a <= 100
    }

}
