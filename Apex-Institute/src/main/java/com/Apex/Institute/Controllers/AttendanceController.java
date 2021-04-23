package com.Apex.Institute.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.Apex.Institute.Models.Attendance;
import com.Apex.Institute.Models.Batch;
import com.Apex.Institute.Models.Student;
import com.Apex.Institute.Services.AttendanceService;
import com.Apex.Institute.Services.BatchService;
import com.Apex.Institute.Services.StudentService;

@Controller
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@Autowired
	private BatchService batchService;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UserController userController;

	@GetMapping("/batchesForAttendance")
	public String getBatches(Model model) {
		model.addAttribute("batches", batchService.getAllBatches());
		return "teacher/batchAttendance";
	}

	@RequestMapping("/takeAttendance")
	public String attendanceForm(Model model, @RequestParam("batch_id") int batch_id) {
		Batch batch = batchService.getBatchById(batch_id);
		List<Student> students = studentService.getStudentByBatch(batch);
		model.addAttribute("students", students);
		model.addAttribute("batch", batch);
		return "teacher/attendance";
	}
	
	@GetMapping("/batchForAttendance")
	public String selectBatch(Model model) {
		Student user= studentService.getStudentById(userController.getCurrentUser().getUser_id());
		model.addAttribute("user",user);
		return "student/batchForAttendance";
	}

	@RequestMapping(value = "/saveAttendance", method = RequestMethod.POST)
	public RedirectView saveAttendance(@RequestParam String data,@RequestParam String batch_id, @RequestParam String date, RedirectAttributes fattr) {
		List<Attendance> attendanceList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
		JSONArray array = new JSONArray(data);
		for (int i = 0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);
			Attendance attendance = new Attendance();
			attendance.setBatch(batchService.getBatchById(Integer.parseInt(batch_id)));
			attendance.setDate(LocalDate.parse(date, formatter));
			attendance.setStudent(studentService.getStudentById(object.getInt("id")));
			attendance.setStatus(object.getString("status"));
			attendanceList.add(attendance);			
		}
		attendanceService.saveAll(attendanceList);
		fattr.addFlashAttribute("message", "Attendance Saved");
		return new RedirectView("batchesForAttendance");
	}
	
	@GetMapping("/studentAttendance")
	public String studentAttendance(Model model, @RequestParam int batch_id){
		Student user =  (Student) userController.getCurrentUser();
		List<Attendance> attendance = attendanceService.getAttendanceByStudent(user);
		Iterator<Attendance> itr = attendance.iterator();
		while(itr.hasNext()) {
			if(itr.next().getBatch().getBatch_id() != batch_id) {
				itr.remove();
			}
		}
		Map<LocalDate, String> result = attendance.stream().collect(Collectors.toMap(Attendance::getDate, Attendance::getStatus));
		JSONObject json = new JSONObject(result);
		model.addAttribute("attendance", json);
		model.addAttribute("user",user);
		return "student/studentAttendance";
	}
	
	@GetMapping("/viewAttendance")
	public String viewAttendance(Model model, @RequestParam int batch_id) {
		Batch batch = batchService.getBatchById(batch_id);
		model.addAttribute("students",studentService.getStudentByBatch(batch));
		model.addAttribute("batch",batch);
		return "teacher/batchForAttendance";
	}
	
	@GetMapping("/showAttendance")
	public String adminAttendance(@RequestParam int batch_id, @RequestParam int student_id, Model model) {
		Student student = studentService.getStudentById(student_id);
		List<Attendance> attendance = attendanceService.getAttendanceByStudent(student);
		Iterator<Attendance> itr = attendance.iterator();
		while(itr.hasNext()) {
			if(itr.next().getBatch().getBatch_id() != batch_id) {
				itr.remove();
			}
		}
		Map<LocalDate, String> result = attendance.stream().collect(Collectors.toMap(Attendance::getDate, Attendance::getStatus));
		JSONObject json = new JSONObject(result);
		model.addAttribute("attendance", json);
		model.addAttribute("user",student);
		return "teacher/studentAttendance";
	}

}
