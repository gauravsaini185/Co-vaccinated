package com.Apex.Institute.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.Apex.Institute.Models.Student;
import com.Apex.Institute.Services.BatchService;
import com.Apex.Institute.Services.StudentService;


@Controller
public class StudentController {

    @Autowired
   private BatchService batchService;

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private UserController userController;

    @GetMapping("/addStudent")
    public String studentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("allBatches", batchService.getAllBatches());
        return "admin/studentForm";
    }

    @PostMapping("/addStudent")
    public String saveStudent(@ModelAttribute Student student, Model model){
    	student.setFile_path("profileImages/default.png");
        studentService.save(student);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("message", "Student saved");
        return "admin/allStudents";
    } 

    @GetMapping("/allStudents")
        public String allStudents(Model model){
            model.addAttribute("students", studentService.getAllStudents());
            return "admin/allStudents";
        }

    @GetMapping("/removeStudent/{student_id}")
    public RedirectView removeStudent(@PathVariable("student_id") int student_id, RedirectAttributes model) {
        studentService.remove(student_id);
        model.addFlashAttribute("message", "Student Removed");
        return new RedirectView("/allStudents");
    }

    @GetMapping("/updateStudent")
    public String updateStudent(@RequestParam int student_id, Model model){
        model.addAttribute("student",studentService.getStudentById(student_id));
        model.addAttribute("batches", batchService.getAllBatches());
        return "admin/updateStudent";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute Student student,@RequestParam("file") MultipartFile file, Model model) throws IOException{
    	student.setFile_path(setProfileImage(file, student.getUser_id()));
        studentService.updateStudent(student);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("message", "Student Updated");
        return "admin/allStudents";
    }
    
    @GetMapping("/viewStudentProfile")
    public String studentProfile(Model model) {
    	Student student = (Student) userController.getCurrentUser();
    	model.addAttribute("user", student);
    	return "student/studentProfile";
    }
    
    @PostMapping("/updateStudentProfile")
    public RedirectView updateStudentProfile(@ModelAttribute Student user,@RequestParam("name") String name,
    		@RequestParam("lastName") String lname,@RequestParam("file") MultipartFile file, Model model, RedirectAttributes rattr) throws IOException{
    	if(file.getSize() != 0) {
    	user.setFile_path(setProfileImage(file, user.getUser_id()));
    	}
    	else {
    		user.setFile_path(studentService.getStudentById(user.getUser_id()).getFile_path());
    	}
    	user.setName(name+" "+lname);
    	user.setBatches(studentService.getStudentById(user.getUser_id()).getBatches());
        studentService.updateStudent(user);
        rattr.addFlashAttribute("message", "Profile Updated");
        return new RedirectView("/student");
    }
    
    public String setProfileImage(MultipartFile file, int userid) throws IOException {
    	  String path = "C:/Users/Anand/Documents/intellij projects/Apex-Institute/src/main/resources/static/profileImages/";
    	  String extension = StringUtils.cleanPath(file.getOriginalFilename());
    	  String fileName = Integer.toString(userid)+extension.substring(extension.lastIndexOf("."));
    	   Path uploadPath = Paths.get(path);
           
           if (!Files.exists(uploadPath)) {
               Files.createDirectories(uploadPath);
           }
            
           try (InputStream inputStream = file.getInputStream()) {
               Path filePath = uploadPath.resolve(fileName);
               Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
           } catch (IOException ioe) {        
               throw new IOException("Could not save image file: " + fileName, ioe);
           } 
    	  return "profileImages/"+fileName;
    }

}

