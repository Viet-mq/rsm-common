package com.edso.resume.lib.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    public static String saveFile(String serverPath, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        File file1 = new File(serverPath + fileName);
        int i = 0;
        String[] arr = fileName.split("\\.");
        while (file1.exists()) {
            i++;
            file1 = new File(serverPath + arr[0] + " (" + i + ")." + arr[1]);
        }
        FileOutputStream fos = new FileOutputStream(file1);
        fos.write(file.getBytes());
        fos.close();
        return file1.getName();
    }

    public static File saveFile2(String serverPath, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        File file1 = new File(serverPath + fileName);
        int i = 0;
        String[] arr = fileName.split("\\.");
        while (file1.exists()) {
            i++;
            file1 = new File(serverPath + arr[0] + " (" + i + ")." + arr[1]);
        }
        FileOutputStream fos = new FileOutputStream(file1);
        fos.write(file.getBytes());
        fos.close();
        return file1;
    }

}
