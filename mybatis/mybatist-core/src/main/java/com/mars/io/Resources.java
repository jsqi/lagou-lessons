package com.mars.io;

import java.io.InputStream;

public class Resources {

    /**
     * 根配置文件的路径 将配置文件加载到内存中
     * @param path
     * @return
     */
    public static InputStream getResourceAsInputStream(String path){
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}
