package com.peteralexbizjak.ljbikes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.peteralexbizjak.ljbikes.databinding.FragmentSearchBinding
import com.peteralexbizjak.ljbikes.ui.adapters.SearchFragmentAdapter
import com.peteralexbizjak.ljbikes.ui.viewmodels.SearchFragmentViewModel

internal class SearchFragment : Fragment() {
    private var bindingInstance: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = bindingInstance!!

    private val navArguments by navArgs<SearchFragmentArgs>()
    private val searchFragmentViewModel by viewModels<SearchFragmentViewModel>()

    private lateinit var searchFragmentAdapter: SearchFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchFragmentViewModel.setInitialList(navArguments.stations.toList())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingInstance = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentSearchToolbarLayout.setStartIconOnClickListener { findNavController().popBackStack() }
        searchFragmentAdapter = SearchFragmentAdapter(findNavController())
        binding.fragmentSearchRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchFragmentAdapter
        }
        binding.fragmentSearchToolbarInput.doOnTextChanged { text, _, _, _ ->
            searchFragmentViewModel.applyFilter(text.toString())
        }

        searchFragmentViewModel.stationsObservable.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                searchFragmentAdapter.setNewStationsList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingInstance = null
    }
}