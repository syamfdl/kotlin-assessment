package me.syamfdl.myapplication


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import me.syamfdl.myapplication.databinding.FragmentMenuBinding

/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMenuBinding>(inflater, R.layout.fragment_menu, container, false)

        binding.btnPersegi.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_menuFragment_to_persegiFragment)
        }

        binding.btnSegitiga.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_menuFragment_to_segitigaFragment)
        }

        binding.btnKalkulator.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_menuFragment_to_kalkulatorFragment)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  NavigationUI.onNavDestinationSelected(item,
            view!!.findNavController()) || super.onOptionsItemSelected(item)
    }


}
