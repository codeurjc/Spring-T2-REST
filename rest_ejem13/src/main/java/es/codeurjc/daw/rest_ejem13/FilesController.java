package es.codeurjc.daw.rest_ejem13;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FilesController {

    @Value("${spring.resources.static-locations}")
    private String STATIC_FOLDER;

    private String staticFolder(){
        return System.getProperty("user.dir") + "/" + STATIC_FOLDER.split(":")[1];
    }


    @GetMapping("/")
    public List<String> getFiles() throws IOException {
        return Files.list(new File(staticFolder()).toPath()).map(f -> f.getFileName().toString()).collect(Collectors.toList());
    }

    @PostMapping("/")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multiPartFile)
            throws IllegalStateException, IOException {

        String fileName = multiPartFile.getOriginalFilename();
        System.out.println(staticFolder() + fileName);
        File file = new File(staticFolder() + fileName);
        multiPartFile.transferTo(file);
        
        return new ResponseEntity<>("File '"+fileName+"' saved!", HttpStatus.CREATED);
    }
    
}
