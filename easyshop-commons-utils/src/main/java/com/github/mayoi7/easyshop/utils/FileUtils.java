package com.github.mayoi7.easyshop.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.UUID;

/**
 * 文件工具类
 * @author LiuHaonan
 * @date 17:11 2020/5/21
 * @email acerola.orion@foxmail.com
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtils {

    public static final int TRY_TIMES = 1;

    public static String getRandomName(String filename) {
        String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
        String randomName = UUID.randomUUID().toString().replace("-", "");
        return randomName + "." + suffixName;
    }

    /**
     * 生成文件对象
     * @param path 文件保存目录地址，以'/'结尾
     * @param fileName 文件名
     * @param tryTimes 重试次数
     * @return 返回文件路径（并不进行保存）
     */
    public static File saveFile(String path, String fileName, int tryTimes) {
        File directory = new File(path);

        if (!directory.exists()) {
            int tries = tryTimes;
            while(tries > 0 && !directory.mkdirs()) {
                tries--;
            }
        }
        return new File(path, fileName);
    }

    /**
     * 生成文件对象
     * @param path 文件保存目录地址，以'/'结尾
     * @param fileName 文件名
     * @return 返回文件路径（并不进行保存）
     */
    public static File saveFile(String path, String fileName) {
        return saveFile(path, fileName, TRY_TIMES);
    }

    /**
     * 从指定路径加载文件
     * @param path 文件全路径
     * @return 返回文件的字节数组
     */
    public static byte[] loadFile(String path) {
        try(FileInputStream is = new FileInputStream(new File(path))) {
            int i = is.available();
            byte[] data = new byte[i];
            if (is.read(data) == -1) {
                return null;
            }
        } catch (IOException e) {
            log.error("[FILE] load file failed <path={}>", path);
            return null;
        }
        return null;
    }
}
