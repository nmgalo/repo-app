package dev.nmgalo.repo.presentation.utils

import android.view.View
import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce

@OptIn(FlowPreview::class)
fun SearchView.textChanges(debounceTimeOut: Long = 800) = callbackFlow {
    setOnQueryTextListener(
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                trySend(newText)
                return true
            }
        }
    )
    awaitClose { setOnQueryTextListener(null) }
}.debounce(debounceTimeOut)


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}
