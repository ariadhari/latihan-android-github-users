package com.example.githubusers.data.model

data class DetailUserResponse(
    val login : String,
    val name : String,
    val id : Int,
    val avatar_url : String,
    val bio : String,
)
