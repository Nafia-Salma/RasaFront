package com.example.rasachatbotapp

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rasachatbotapp.network.Message
import com.example.rasachatbotapp.network.bodyRequest
import com.example.rasachatbotapp.network.rasaApiService
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import java.util.*

class MainActivityViewModel : ViewModel() {
    private val message_list: MutableList<Message> = mutableStateListOf()
    val messages: List<Message> = message_list

    private val connectivityState = mutableStateOf(true)
    val _connectivityState = connectivityState

    val username = "default"

    fun addMessage(message: Message) {
        message_list.add(0, message)
    }

    fun sendMessagetoRasa(message: Message, msg: bodyRequest) {
        addMessage(message)
        viewModelScope.launch {
            val response = rasaApiService.sendMessage(msg)
            Log.e("Message", response.toString())
            if (response.code() == 200 && response.body() != null) {
                if (response.body()!!.isEmpty()) {
                    addMessage(
                        Message(
                            "Please rephrase your sentence",
                            "bot",
                            Calendar.getInstance().time
                        )
                    )
                } else {
                    response.body()!!.forEach {
                        it.time = Calendar.getInstance().time
                        addMessage(it)
                    }
                }
            } else {
                addMessage(
                    Message(
                        "${response.code()} error occurred",
                        "bot",
                        Calendar.getInstance().time
                    )
                )
            }
        }
    }

}