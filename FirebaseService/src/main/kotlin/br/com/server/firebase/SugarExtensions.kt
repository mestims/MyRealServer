package br.com.server.firebase

import com.google.firebase.database.GenericTypeIndicator
import com.google.gson.reflect.TypeToken

inline fun <reified T> genericType() = object: GenericTypeIndicator<@JvmSuppressWildcards T>(){}