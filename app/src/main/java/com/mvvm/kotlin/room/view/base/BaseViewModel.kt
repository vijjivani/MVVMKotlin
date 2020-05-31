package com.mvvm.kotlin.room.view.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
    }

    fun getCompositeDisposable(): CompositeDisposable {
        return mCompositeDisposable
    }

}