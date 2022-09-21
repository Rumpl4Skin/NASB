package by.ssrlab.NASB.ui.engineering

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.ssrlab.NASB.MainActivityViewModel

import by.ssrlab.NASB.databinding.FragmentEngineeringBinding

class EngineeringFragment : Fragment() {

    private var _binding: FragmentEngineeringBinding? = null

    protected lateinit var MModel: MainActivityViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val engineeringViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(EngineeringViewModel::class.java)

        _binding = FragmentEngineeringBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val model = activity?.let {
            ViewModelProviders.of(it).get(MainActivityViewModel::class.java)

            binding.recycler.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = engineeringViewModel.adapter
            }
            binding.swipeLayout.setOnRefreshListener {
                MModel.loadData()
                binding.swipeLayout.isRefreshing = false
            }

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
