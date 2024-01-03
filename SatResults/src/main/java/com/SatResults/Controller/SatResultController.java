package com.SatResults.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SatResults.entity.Student;
import com.SatResults.service.SatResultService;


@RestController
@RequestMapping("/Student/results")
public class SatResultController {
	
	@Autowired
	private SatResultService satResultService;
	
	@PutMapping("/insert")
	public void insertData(@RequestBody Student studentResult) {
		satResultService.insertData(studentResult);
		
	}
	
	@GetMapping("/view-all")
	public List<Student> viewAllData(){
		
		return satResultService.viewAllData();
	}
	
	
	@GetMapping("/get-rank/{name}")
	public int getRank(@PathVariable String name) {
		
		return satResultService.getRank(name);
	}
	
	@PutMapping("/update-score/{name}/{newScore}")
	public void updateScore(@PathVariable String name, @PathVariable int newScore) {
		
		satResultService.updateScore(name, newScore);
	}
	
	@DeleteMapping("/delete/{name}")
	public void deleteRecord(@PathVariable String name) {
		
		satResultService.deleteRecord(name);
	}

	@GetMapping("/results")
    public String showResults(Model model) {
        List<Student> results = satResultService.viewAllData();
        model.addAttribute("results", results);
        return "results";
    }

}
