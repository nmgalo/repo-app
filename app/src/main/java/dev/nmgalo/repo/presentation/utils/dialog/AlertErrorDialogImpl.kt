package dev.nmgalo.repo.presentation.utils.dialog

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dev.nmgalo.repo.R

class AlertErrorDialogImpl(private val context: Context) : AlertErrorDialog {
    override fun showAlertErrorDialog() {
        val alertDialogBuilder = MaterialAlertDialogBuilder(context)
        alertDialogBuilder.setTitle(context.getString(R.string.error))
        alertDialogBuilder.setNeutralButton(android.R.string.cancel) { dialog, _ -> dialog.dismiss() }
        alertDialogBuilder.show()
    }
}
