package dev.nmgalo.repo.presentation.utils

import androidx.lifecycle.SavedStateHandle

fun <T> SavedStateHandle.getValue(key: String) = get<T>(key) ?: error("Key $key not found!")
