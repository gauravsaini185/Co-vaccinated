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
import com.Apex.Institute.Models.Shipment;
import com.Apex.Institute.Models.Student;
import com.Apex.Institute.Services.AccommodationService;
import com.Apex.Institute.Services.ShipmentService;

@Controller
public class ShipmentController {
	
	@Autowired
	private ShipmentService shipmentService;
		
		@Autowired
		private UserController userController;
		
		// Student
		
		@GetMapping("/shipmentFor")
		public String getShipForm(Model model) {   // new object pass
			Student user = (Student) userController.getCurrentUser();
			model.addAttribute("user", user);
			model.addAttribute("shipment", new Shipment());
			return "student/shipmentForm";
		}
		
		@PostMapping(value = "/shipmentFor")
	    public String addShipment(@ModelAttribute("shipment") Shipment shipment, Model model) {
		
			shipmentService.saveShipment(shipment);
		 Student user = (Student) userController.getCurrentUser();
			model.addAttribute("user", user);
//	        model.addAttribute("accommodations", accommodationService.getAllAccomodation());
	        model.addAttribute("message", "Shipment Saved");
	        return "student/payment";
	    }
		

		// admin and teacher
		
		@GetMapping("/allShipment")
	    public String allShipment(Model model) {
			model.addAttribute("shipments", shipmentService.getAllShipment());
	        return "teacher/allShipment";
	    }
	 
	 @GetMapping("/addShipment")
		public String shipmentAdminForm(Model model) {
			model.addAttribute("shipment", new Shipment());
			return "teacher/shipmentForm";
		}
	 
	 @PostMapping(value = "/addShipment")
	    public String postShipmentform(@ModelAttribute("shipment") Shipment shipment, Model model) {
		 shipmentService.saveShipment(shipment);
	        model.addAttribute("shipments", shipmentService.getAllShipment());
	        model.addAttribute("message", "Shipment Saved");
	        return "teacher/allShipment";
	    }
	 
	 @GetMapping("/updateShipment")
	    public String updateShipment(@RequestParam int shipment_id, Model model){
	        model.addAttribute("shipment", shipmentService.getShipmentById(shipment_id));
	        return "teacher/updateShipment";
	    }

	    @RequestMapping(value="/updateShipment", method=RequestMethod.POST)
	    public RedirectView updateShipment(@ModelAttribute Shipment shipment, RedirectAttributes model){
	    	shipmentService.updateShipment(shipment);
	        model.addFlashAttribute("message", "Shipment Updated");    	
	        return new RedirectView("/allShipment");
	    }
	    

	    @GetMapping("/removeShipment/{shipment_id}")
	    public RedirectView removeShipment(@PathVariable("shipment_id") int shipment_id, RedirectAttributes model) {
			try {
				shipmentService.remove(shipment_id);
				model.addFlashAttribute("message", "Shipment Removed");
			} catch (Exception e) {
				model.addFlashAttribute("error", "Can't delete this Shipment, Users are associated to it.");
			}
			return new RedirectView("/allShipment");
		}

}
