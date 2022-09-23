package dev.nmgalo.repo.presentation.common

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import dev.nmgalo.repo.presentation.common.navigation.NavigationCommand
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel : ViewModel() {
    private val _navigationCommand = Channel<NavigationCommand>()
    val navigationCommand = _navigationCommand.receiveAsFlow()

    fun NavDirections.navigate() {
        _navigationCommand.trySend(NavigationCommand.To(this))
    }

    fun popBackStack() = _navigationCommand.trySend(NavigationCommand.Back)
}
