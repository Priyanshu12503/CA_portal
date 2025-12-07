package com.example.Drive_BE.controller;

import com.example.Drive_BE.entity.FileEntity;
import com.example.Drive_BE.services.FileServieStorage;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:3000")
public class FileController {

    private final FileServieStorage fileServieStorage;

    public FileController(FileServieStorage fileServieStorage) {
        this.fileServieStorage = fileServieStorage;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam(value = "parentFolderId", required = false) Long parentFolderId) {
        try {
            String response = fileServieStorage.saveFile(file, parentFolderId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id){

        try{

            //from my sql
            FileEntity fileEntity = fileServieStorage.getFileById(id);
            Path path = Paths.get(fileEntity.getPath());

            Resource resource = new UrlResource(path.toUri());

            return ResponseEntity.ok().header("content-Disposition", "attachment; filename=\""+fileEntity.getName()+"\"").body(resource);
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/list")
    public ResponseEntity<List<FileEntity>> listFiles(
            @RequestParam(value = "parentFolderId", required = false)  Long parentFolderId
    ){
        List<FileEntity> files;
        if(parentFolderId == null){
            files=fileServieStorage.getFilelsInFolder(null);
        }
        else{
            files=fileServieStorage.getFilelsInFolder(parentFolderId);
        }

        return ResponseEntity.ok(files);


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        try {
            FileEntity fileEntity = fileServieStorage.getFileById(id);
            Path path = Paths.get(fileEntity.getPath());
            Files.deleteIfExists(path);

            // Also remove metadata from DB
            fileServieStorage.deleteById(id);

            return ResponseEntity.ok("File deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Delete failed: " + e.getMessage());
        }
    }



}



