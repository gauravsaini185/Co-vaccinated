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

import com.Apex.Institute.Models.Batch;
import com.Apex.Institute.Services.BatchService;
import com.Apex.Institute.Services.TeacherService;

@Controller
public class BatchController {

    @Autowired
    private BatchService batchService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/addBatch")
    public String batchForm(Model model) {
        model.addAttribute("batch", new Batch());
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "admin/batchForm";
    }

    @PostMapping(value = "/addBatch")
    public String addBatch(@ModelAttribute("batch") Batch batch, Model model) {
        batchService.saveBatch(batch);
        model.addAttribute("batches", batchService.getAllBatches());
        model.addAttribute("message", "Batch Saved");
        return "admin/allBatches";
    }

    @GetMapping("/allBatches")
    public String allBatchs(Model model) {
        model.addAttribute("batches", batchService.getAllBatches());
        return "admin/allBatches";
    }
    
	@GetMapping("/updateBatch")
    public String updateBatch(@RequestParam int batch_id, Model model){
        model.addAttribute("batch", batchService.getBatchById(batch_id));
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "admin/updateBatch";
    }

    @RequestMapping(value="/updateBatch", method=RequestMethod.POST)
    public RedirectView updateBatch(@ModelAttribute Batch batch, RedirectAttributes model){
        batchService.updateBatch(batch);
        model.addFlashAttribute("message", "Batch Updated");    	
        return new RedirectView("/allBatches");
    }
    

    @GetMapping("/removeBatch/{batch_id}")
    public RedirectView removeBatch(@PathVariable("batch_id") int batch_id, RedirectAttributes model) {
		try {
			batchService.remove(batch_id);
			model.addFlashAttribute("message", "Batch Removed");
		} catch (Exception e) {
			model.addFlashAttribute("error", "Can't delete this Batch, Students are associated to it.");
		}
		return new RedirectView("/allBatches");
	}
}
