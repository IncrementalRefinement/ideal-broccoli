package com.example.idealbroccoli.service.impl;

import com.example.idealbroccoli.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    @Value(value = "${spring.savefile.path}")
    String filePathString;

    @Override
    public String saveFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path rootFilePath = Path.of(filePathString);
        try {
            Files.createDirectories(rootFilePath);
        } catch (FileAlreadyExistsException ignored){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Path targetLocation = rootFilePath.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return targetLocation.toString();
    }
}
