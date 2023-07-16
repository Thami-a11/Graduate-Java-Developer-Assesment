package com.eviro.assessment.grad001.ThamsanqaDyantyi.Controllers;

import com.eviro.assessment.grad001.ThamsanqaDyantyi.InterFaces.FileParser;
import com.eviro.assessment.grad001.ThamsanqaDyantyi.InterFaces.IAccountProfileService;
import com.eviro.assessment.grad001.ThamsanqaDyantyi.Models.AccountProfile;
import com.eviro.assessment.grad001.ThamsanqaDyantyi.Repository.AccountProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {


    @Autowired
    private IAccountProfileService accountProfileService;

    @GetMapping("/GetAll")
    public ResponseEntity<List<AccountProfile>> GetAll(){
        try {
            List<AccountProfile> lst = new ArrayList<>();
            accountProfileService.GetAll().forEach(lst::add);

            if (lst.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(lst, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> UploadFile(@RequestParam("file") MultipartFile multipartFile){

        if(multipartFile.isEmpty()) {
            return ResponseEntity.badRequest().body("No uploaded file!");
        }
        try{
            File file = convertMultipartFileToFile(multipartFile);
            accountProfileService.UploadFile(file);
            file.delete();

            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Convert the multifile from imput to file
    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = File.createTempFile("temp", null);
        multipartFile.transferTo(file);
        return file;
    }


    @GetMapping(value = "/{name}/{surname}/{\\w\\.\\w}")
    public FileSystemResource gethttpImageLink(@PathVariable String name, @PathVariable String surname)
    {
        var link = accountProfileService.getHttpLink(name,surname);
        FileSystemResource f;

        if (link != null) {
             f = new FileSystemResource(link.getPath());
            return f;
        }
        else{
            return null;
        }
    }
}
