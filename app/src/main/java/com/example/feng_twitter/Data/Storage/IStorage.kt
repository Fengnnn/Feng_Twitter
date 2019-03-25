package com.example.feng_twitter.Data.Storage

import android.content.Context
import com.example.feng_twitter.Controllers.AuthApi.IUserCredential
import java.io.File

interface ISharedPreferenceStorage {
    fun cleanAll()
    fun save(resourceId: Int, stringToSave: String)
    fun get(resourceId: Int): String
    fun getUserCredential(): IUserCredential
    fun saveUserCredential(userCredential: IUserCredential)
}

interface ITempStorage {
    fun getStringFromTemp(url: String): String
    fun writeStringToTemp(url: String, content: String)
}

interface IFileStorage {
    fun createFile(url: String): File?
    fun getFile(url: String): File
    fun getFileDir(context: Context): File
    fun deleteFile(url: String)
}