package dev.nmgalo.repo.presentation.common

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dev.nmgalo.repo.presentation.common.navigation.NavigationCommand
import dev.nmgalo.repo.presentation.utils.dialog.AlertErrorDialog
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel>(@LayoutRes layout: Int) : Fragment(layout) {
    protected abstract val viewModel: VM

    @Inject
    lateinit var errorDialog: AlertErrorDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.navigationCommand.collectLatest {
                when (it) {
                    is NavigationCommand.To -> findNavController().navigate(it.destination)
                    is NavigationCommand.Back -> findNavController().popBackStack()
                }
            }
        }
    }
}
