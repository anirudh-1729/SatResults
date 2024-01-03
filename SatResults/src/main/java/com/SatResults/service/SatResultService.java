package com.SatResults.service;

import java.util.List;

import com.SatResults.entity.Student;

public interface SatResultService {
	
	/*Insert data*/
	void insertData(Student studentResult);
	
	/*View all data*/
	List<Student> viewAllData();
	
	/*Get Rank*/
	int getRank(String name);
	
	/*update the score*/
	void updateScore(String name, int newScore);
	
	/*Delete the record*/
	void deleteRecord(String name);

}
