package com.enovira.base_component.extension

import com.enovira.base_component.utils.FileUtils
import java.io.File
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * @date 2025/1/7 16:41
 * @author xiaozy
 * @description 文件对象扩展类,基于FileUtils
 */
fun File.readByString(charset: Charset = StandardCharsets.UTF_8): String? {
    return FileUtils.readFileByString(this, charset)
}

fun File.readByLimitString(limit: Long, charset: Charset = StandardCharsets.UTF_8): String? {
    return FileUtils.readFileByLimitString(this, limit, charset)
}