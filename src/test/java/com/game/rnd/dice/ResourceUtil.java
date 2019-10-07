package com.game.rnd.dice;

import org.springframework.util.FileCopyUtils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public final class ResourceUtil {

    public static String getContentAsString(String path) {
        URL resource = ResourceUtil.class.getClassLoader().getResource(path);
        if (resource == null) {
            throw new RuntimeException("Resource is not found by path " + path);
        }
        try {
            return FileCopyUtils.copyToString(new FileReader(resource.getFile()));
        } catch (IOException e) {
            throw new RuntimeException("Resource loading failure", e);
        }
    }
}
