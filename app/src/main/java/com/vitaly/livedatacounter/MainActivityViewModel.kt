package com.vitaly.livedatacounter

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    private lateinit var timer: CountDownTimer

    private val _finished = MutableLiveData<Boolean>()
    fun finished(): LiveData<Boolean> {
        return _finished
    }

    private val _seconds = MutableLiveData<Int>()
    fun seconds(): LiveData<Int> {
        return _seconds
    }

    fun startTimer(millisInFuture: Long) {
        timer = object : CountDownTimer(millisInFuture, 1000){
            override fun onTick(p0: Long) {
                val timeLeft = p0/1000
                _seconds.value = timeLeft.toInt()
            }

            override fun onFinish() {
                _finished.value = true
            }

        }.start()
    }

    fun stopTimer() {
        timer.cancel()
    }

}