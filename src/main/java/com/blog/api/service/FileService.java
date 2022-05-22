package com.blog.api.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.*;

public interface FileService {
    public String uploadImage(String path, MultipartFile file) throws IOException;
    public InputStream getResource(String path, String fileName) throws FileNotFoundException;
}