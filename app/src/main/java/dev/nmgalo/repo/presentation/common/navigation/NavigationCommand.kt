package dev.nmgalo.repo.presentation.common.navigation

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class To(val destination: NavDirections) : NavigationCommand()

    object Back : NavigationCommand()
}
