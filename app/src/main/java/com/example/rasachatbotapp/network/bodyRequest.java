package com.example.rasachatbotapp.network;

import com.google.gson.annotations.SerializedName;

public class bodyRequest {
    @SerializedName("message")
    private String message;

    public bodyRequest(String message) {
        this.message = message;
    }
    // Getter and setter methods for 'message'
}
