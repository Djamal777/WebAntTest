package com.example.webanttes.domain.models

import java.io.Serializable
import java.util.Calendar

data class Photo(
    val id: String?,
    val createdAt: Calendar?,
    val description: String?,
    val photo: String?,
    val userName: String?,
    val name: String?
):Serializable
