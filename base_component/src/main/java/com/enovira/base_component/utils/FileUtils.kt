package com.enovira.base_component.utils

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.io.InputStreamReader
import java.io.RandomAccessFile
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * @date 2025/1/7 16:12
 * @author xiaozy
 * @permission Manifest.permission.WRITE_EXTERNAL_STORAGE //Android10-
 * @permission Manifest.permission.READ_EXTERNAL_STORAGE //Android10-
 * @permission Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION //文件管理权限 Android11+
 * @description 文件操作工具类,需注意文件访问权限
 */
object FileUtils {

    @JvmStatic
    fun readFileByLimitString(file: File, max: Long, charset: Charset): String? {
        try {
            if (file.exists()) {
                val raf = RandomAccessFile(file, "r")
                val pos = if (max > raf.length()) 0 else raf.length() - max
                raf.seek(pos)
                val byteArray = ByteArray((file.length() - pos).toInt())
                raf.readFully(byteArray)
                return String(byteArray, charset)
            } else {
                throw FileNotFoundException("target file is not exist, confirm the file path is correct: ${file.path}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    @JvmStatic
    fun readFileByString(file: File, charset: Charset = StandardCharsets.UTF_8): String? {
        try {
            if (file.exists()) {
                val bufferedReader = BufferedReader(InputStreamReader(file.inputStream(), charset))
                return bufferedReader.readText()
            } else {
                throw FileNotFoundException("target file is not exist, confirm the file path is correct: ${file.path}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    @JvmStatic
    fun readFileByByteArray(file: File): ByteArray? {
        try {
            if (file.exists()) {
                return file.readBytes()
            } else {
                throw FileNotFoundException("target file is not exist, confirm the file path is correct: ${file.path}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * @param append whether append content after existed content
     */
    @JvmStatic
    fun writeFileByString(file: File, content: String, append: Boolean = false): Boolean {
        try {
            file.parentFile?.takeIf { !it.exists() }?.mkdirs()
            file.takeIf { !it.exists() }?.createNewFile()
            val bufferedWriter =  BufferedWriter(FileWriter(file, append))
            bufferedWriter.write(content)
            bufferedWriter.flush()
            bufferedWriter.close()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}