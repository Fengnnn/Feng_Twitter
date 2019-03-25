package com.example.feng_twitter.Data.Storage

import android.content.Context
import android.net.Uri
import java.io.File

class TempStorage(private val mContext: Context) : ITempStorage, IFileStorage {
    private val mFileDir = this.getFileDir(this.mContext)
    override fun getStringFromTemp(url: String): String {
        try {
            val file = this.getFile(url)
            return file.readText(Charsets.UTF_8)
        } catch (e: Exception) {
            return "";
        }
    }

    override fun writeStringToTemp(url: String, content: String) {
        val file = this.getFile(url)
        file.writeText(content)
    }

    override fun createFile(url: String): File? {
        return Uri.parse(url)?.lastPathSegment?.let { filename ->
            File.createTempFile(filename, null, this.mFileDir)
        }
    }

    override fun getFile(url: String): File {
        return File(this.mFileDir, url)
    }

    override fun getFileDir(context: Context): File {
        return context.cacheDir
    }

    override fun deleteFile(url: String) {
        val file = this.getFile(url)
        file.delete()
    }
}