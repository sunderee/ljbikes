package com.peteralexbizjak.ljbikes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.peteralexbizjak.ljbikes.databinding.FragmentLoadingBinding

internal class LoadingFragment : Fragment() {
    private var bindingInstance: FragmentLoadingBinding? = null
    private val binding: FragmentLoadingBinding get() = bindingInstance!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingInstance = FragmentLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingInstance = null
    }
}