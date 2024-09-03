package com.example.webanttes.domain.converters

import com.example.webanttes.data.remote.response.ResponsePhoto
import com.example.webanttes.domain.models.Photo
import com.example.webanttes.utils.stringDateToCalendar

fun ResponsePhoto.toPhoto() = Photo(
    id,
    createdAt?.let{stringDateToCalendar(it)},
    description,
    urls?.regular,
    user?.name,
    description
)