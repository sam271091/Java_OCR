package com.example.java_ocr.demo;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {
    @RequestMapping("/")

    public String Index(){
        return "upload";
    }

 @PostMapping(value = "/upload")
    public @ResponseBody String singleFileUpload(@RequestParam("file") MultipartFile file,
                                                 RedirectAttributes redirectAttributes, Model model) throws IOException, TesseractException {


      byte[] bytes = file.getBytes();
     Path path = Paths.get("C:\\Users\\Samir\\Desktop\\123.jpeg");
     Files.write(path,bytes);
     File convFile = convert(file);

     Tesseract tesseract = new Tesseract();
     tesseract.setDatapath("C:\\Users\\Samir\\Desktop\\123");
     String text = tesseract.doOCR(convFile);
     System.out.println(text);




     return  text;
 }

 @RequestMapping("/result")
    public String result(){
        return "result";
 }

 public static File convert(MultipartFile file) throws IOException{
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
     FileOutputStream fos = new FileOutputStream(convFile);
     fos.write(file.getBytes());
     fos.close();
     return convFile;



 }





}

