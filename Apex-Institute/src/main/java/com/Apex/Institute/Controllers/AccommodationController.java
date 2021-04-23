package com.Apex.Institute.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.Apex.Institute.Models.Accommodation;
import com.Apex.Institute.Models.Assignment;
import com.Apex.Institute.Models.Batch;
import com.Apex.Institute.Models.Student;
import com.Apex.Institute.Services.AccommodationService;

@Controller
public class AccommodationController {

@Autowired
private AccommodationService accommodationService;
	
	@Autowired
	private UserController userController;
	
	// student
	
	@GetMapping("/accommodationFor")
	public String getForm(Model model) {   // new object pass
		Student user = (Student) userController.getCurrentUser();
		model.addAttribute("user", user);
		model.addAttribute("accommodation", new Accommodation());
		return "student/accommodationForm";
	}
	

	 @PostMapping(value = "/accommodationFor")
	    public String addBatch(@ModelAttribute("accommodation") Accommodation accommodation, Model model) {
		
		 accommodationService.saveAccommodatin(accommodation);
		 Student user = (Student) userController.getCurrentUser();
			model.addAttribute("user", user);
//	        model.addAttribute("accommodations", accommodationService.getAllAccomodation());
	        model.addAttribute("message", "Accomodation Saved");
	        return "student/payment";
	    }

	
		/*
		 * @GetMapping("/accommodationForm") public String getFormT(Model model) { //
		 * new object pass model.addAttribute("accommodation", new Accommodation());
		 * return "teacher/accommodationForm"; }
		 */
	
	 
	 // admin  and teacher
	 
	 @GetMapping("/allAccommodation")
	    public String allAccommodation(Model model) {
	        model.addAttribute("accommodations", accommodationService.getAllAccomodation());
	        return "teacher/allAccommodationAdmin";
	    }
	 
	 @GetMapping("/addAccommodation")
		public String assignmentForm(Model model) {
			model.addAttribute("accommodation", new Accommodation());
			return "teacher/accommodationForm";
		}
	 
	 @PostMapping(value = "/addAccommodation")
	    public String addAccommodation(@ModelAttribute("accommodation") Accommodation accommodation, Model model) {
		 accommodationService.saveAccommodatin(accommodation);
	        model.addAttribute("accommodations", accommodationService.getAllAccomodation());
	        model.addAttribute("message", "Accomodation Saved");
	        return "teacher/allAccommodationAdmin";
	 	}
	 
	 @GetMapping("/updateAccommodation")
	    public String updateAccommodation(@RequestParam int accommodation_id, Model model){
	        model.addAttribute("accommodation", accommodationService.getAccommodationById(accommodation_id));
	        return "teacher/updateAccommodation";
	    }

	    @RequestMapping(value="/updateAccommodation", method=RequestMethod.POST)
	    public RedirectView updateAccommodation(@ModelAttribute Accommodation accommodation, RedirectAttributes model){
	    	accommodationService.updateAccomodation(accommodation);
	        model.addFlashAttribute("message", "Accommodation Updated");    	
	        return new RedirectView("/allAccommodation");
	    }
	    

	    @GetMapping("/removeAccommodation/{accommodation_id}")
	    public RedirectView removeAccommodation(@PathVariable("accommodation_id") int accommodation_id, RedirectAttributes model) {
			try {
				accommodationService.remove(accommodation_id);
				model.addFlashAttribute("message", "Accommodation Removed");
			} catch (Exception e) {
				model.addFlashAttribute("error", "Can't delete this Accommodation, Users are associated to it.");
			}
			return new RedirectView("/allAccommodation");
		}
}

