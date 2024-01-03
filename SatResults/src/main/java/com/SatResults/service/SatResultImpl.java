package com.SatResults.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.SatResults.entity.Student;

@Service
public class SatResultImpl implements SatResultService{

	private final Map<String, Student> dataStore = new ConcurrentHashMap<>();
	
	@Override
	public void insertData(Student studentResult) {
		// TODO Auto-generated method stub
		
		studentResult.setPassed(studentResult.getScore() > 30);
		dataStore.put(studentResult.getName(), studentResult);
		
	}

	@Override
	public List<Student> viewAllData() {
		// TODO Auto-generated method stub
		
		return dataStore.values().stream().collect(Collectors.toList());

	}

	@Override
	public int getRank(String name) {
		// TODO Auto-generated method stub
		 List<Student> sortedResults = dataStore.values().stream()
	                .sorted((r1, r2) -> Integer.compare(r2.getScore(), r1.getScore()))
	                .collect(Collectors.toList());

	        for (int i = 0; i < sortedResults.size(); i++) {
	            if (sortedResults.get(i).getName().equals(name)) {
	                return i + 1;
	            }
	        }
	        return -1;
	}

	@Override
	public void updateScore(String name, int newScore) {
		// TODO Auto-generated method stub
		Student satResult = dataStore.get(name);
        if (satResult != null) {
            satResult.setScore(newScore);
            satResult.setPassed(newScore > 30);
        }
		
	}

	@Override
	public void deleteRecord(String name) {
		// TODO Auto-generated method stub
		dataStore.remove(name);
	}
	
	

}
