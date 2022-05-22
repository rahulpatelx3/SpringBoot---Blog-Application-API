package com.blog.api.service.Impl;

import com.blog.api.service.FileService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
    	
    	//getting file name from uploaded file
        String name=file.getOriginalFilename();
        
        //creating random id
        String randomId= UUID.randomUUID().toString();
        
        //concatination of file name And random id
        String fileName=randomId.concat(name.substring(name.lastIndexOf(".")));

        // File.separator == "\" for windows
        String filePath=path+File.separator+fileName;

        File f=new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath=path+File.separator+fileName;
        InputStream is=new FileInputStream(fullPath);
        return is;
    }
}