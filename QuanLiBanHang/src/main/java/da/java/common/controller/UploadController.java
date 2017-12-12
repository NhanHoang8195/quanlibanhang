package da.java.common.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import da.java.common.entities.Category;

@Controller
public class UploadController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "c://temp//";
    
    @Autowired
    ServletContext context;
    
    private String getFileExtension(String name) {
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
    
    @PostMapping("/upload/") // //new annotation since 4.3
    public ResponseEntity<?> singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        try {
        	File UPLOAD_DIR = new File(UPLOADED_FOLDER);
        	 if (!UPLOAD_DIR.exists()) {
        		 UPLOAD_DIR.mkdirs();
             }
        	  byte[] bytes = file.getBytes();
        	  
            // Get the file and save it somewhere
        	String name = java.util.UUID.randomUUID().toString();
        	String extension = getFileExtension(file.getOriginalFilename());
            Path path = Paths.get(UPLOADED_FOLDER + name + "." + extension);
            
            Files.write(path, bytes);
            
            Category cate = new Category();
            cate.setName("/imageUploaded/" + name + "/" + extension);
            return new ResponseEntity<>(cate, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
		return null;
    }
    @RequestMapping(value = "/imageUploaded/{imageName}/{extension}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imageName") String imageName,
    		@PathVariable(value = "extension") String extension) throws IOException {
        File serverFile = new File(UPLOADED_FOLDER + imageName + "." + extension);

        return Files.readAllBytes(serverFile.toPath());
    }
}
