package com.example.serializationdemo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Data(
    val id: Long,
    @SerialName("user_name")
    val name: String,
    val hasLastName: Boolean,
    val type: Type,
    val list: List<Details>,
    //Optional field with default value
    val map: Map<Int, Boolean> = emptyMap(),
    //Ignore serialization with default value
    @Transient
    val set: Set<Int> = emptySet()
)

@Serializable
data class Details(
    val id: Long,
    val name: String,
    val list: List<Int>
)

@Serializable // Need because @SerialName used
enum class Type {
    @SerialName("online")
    ONLINE,

    @SerialName("offline")
    OFFLINE
}