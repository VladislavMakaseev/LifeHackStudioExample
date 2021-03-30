package com.vi.lifehackstudioexample.presentation.companies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.vi.lifehackstudioexample.R
import com.vi.lifehackstudioexample.base.event.EventObserver
import com.vi.lifehackstudioexample.databinding.FragmentCompaniesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class CompaniesFragment : Fragment() {

    private var _binding: FragmentCompaniesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CompaniesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompaniesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViews() {
        // TODO: Impl
    }

    private fun setupViewModel() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner, { isLoading ->
            showOrHideLoading(isLoading)
        })
        viewModel.companiesLiveData.observe(viewLifecycleOwner, { items ->
           Timber.d("kek:: items = $items")
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, EventObserver { message ->
            showError(message)
        })
    }

    private fun showError(message: String?) {
        val errorMessage = message ?: getString(R.string.error_unknown)
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun showOrHideLoading(isLoading: Boolean, @IdRes resId: Int = R.id.progressBar) {
        val progressBar = view?.findViewById<ProgressBar>(resId)
        if (isLoading) {
            progressBar?.show()
        } else {
            progressBar?.hide()
        }
    }

}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}