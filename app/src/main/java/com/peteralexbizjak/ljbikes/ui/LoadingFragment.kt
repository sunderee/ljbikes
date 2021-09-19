package com.peteralexbizjak.ljbikes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.peteralexbizjak.ljbikes.R
import com.peteralexbizjak.ljbikes.api.models.ApiResponseModel
import com.peteralexbizjak.ljbikes.databinding.FragmentLoadingBinding
import com.peteralexbizjak.ljbikes.ui.viewmodels.LoadingFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class LoadingFragment : Fragment() {
    private var bindingInstance: FragmentLoadingBinding? = null
    private val binding: FragmentLoadingBinding get() = bindingInstance!!

    private val loadingFragmentViewModel by viewModel<LoadingFragmentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingFragmentViewModel.launchInitialConfiguration()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingInstance = FragmentLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingFragmentViewModel.stationsObservable.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponseModel.Success -> {
                    findNavController().navigate(
                        LoadingFragmentDirections.actionLoadingFragmentToMainFragment(
                            it.value.toTypedArray()
                        )
                    )
                }
                is ApiResponseModel.Failure -> {
                    Snackbar.make(
                        binding.root,
                        it.throwable?.localizedMessage ?: "Fatal application error",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                is ApiResponseModel.Loading -> {
                    binding.loadingStatus = getString(R.string.loading)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingInstance = null
    }
}