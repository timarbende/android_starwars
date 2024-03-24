package com.timar.androidstarwars.ui.screen.listscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.timar.androidstarwars.ui.util.ContentType
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel(assistedFactory = ListScreenViewModel.ListScreenViewModelFactory::class)
class ListScreenViewModel @AssistedInject constructor(
    @Assisted val contentType: ContentType
): ViewModel() {
    private val _state = MutableStateFlow(ListScreenState())
    val state = _state.asStateFlow()

    @AssistedFactory
    interface ListScreenViewModelFactory{
        fun create(contentType: ContentType): ListScreenViewModel
    }

    init{
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        when(contentType){
            ContentType.Characters -> {
                _state.update {
                    it.copy(
                        isLoading = false,

                    )
                }
            }
            else -> {}
        }
    }
}