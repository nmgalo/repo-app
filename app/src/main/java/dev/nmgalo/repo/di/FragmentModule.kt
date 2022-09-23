package dev.nmgalo.repo.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped
import dev.nmgalo.repo.presentation.utils.dialog.AlertErrorDialog
import dev.nmgalo.repo.presentation.utils.dialog.AlertErrorDialogImpl

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    @Provides
    @FragmentScoped
    fun providesAlertErrorDialog(@ActivityContext context: Context): AlertErrorDialog =
        AlertErrorDialogImpl(context)
}
