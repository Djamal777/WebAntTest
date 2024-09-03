package com.example.webanttes.data.remote.response

data class ResponsePhoto(
    val blurHash: String?,
    val color: String?,
    val createdAt: String?,
    val currentUserCollections: List<CurrentUserCollection>,
    val description: String?,
    val downloads: Int?,
    val exif: Exif?,
    val height: Int?,
    val id: String?,
    val likedByUser: Boolean?,
    val likes: Int?,
    val links: Links?,
    val location: Location?,
    val publicDomain: Boolean?,
    val tags: List<Tag>?,
    val updatedAt: String?,
    val urls: Urls?,
    val user: User?,
    val width: Int?
) {
    data class CurrentUserCollection(
        val coverPhoto: Any?,
        val id: Int,
        val lastCollectedAt: String,
        val publishedAt: String,
        val title: String,
        val updatedAt: String,
        val user: Any?
    )

    data class Exif(
        val aperture: String,
        val exposureTime: String,
        val focalLength: String,
        val iso: Int,
        val make: String,
        val model: String,
        val name: String
    )

    data class Links(
        val download: String,
        val downloadLocation: String,
        val html: String,
        val self: String
    )

    data class Location(
        val city: String,
        val country: String,
        val position: Position
    ) {
        data class Position(
            val latitude: Double,
            val longitude: Double
        )
    }

    data class Tag(
        val title: String
    )

    data class Urls(
        val full: String,
        val raw: String,
        val regular: String,
        val small: String,
        val thumb: String
    )

    data class User(
        val bio: String,
        val id: String,
        val links: Links,
        val location: String,
        val name: String,
        val portfolioUrl: String,
        val totalCollections: Int,
        val totalLikes: Int,
        val totalPhotos: Int,
        val updatedAt: String,
        val username: String
    ) {
        data class Links(
            val html: String,
            val likes: String,
            val photos: String,
            val portfolio: String,
            val self: String
        )
    }
}