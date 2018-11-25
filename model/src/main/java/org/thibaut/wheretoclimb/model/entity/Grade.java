package org.thibaut.wheretoclimb.model.entity;

import java.util.Arrays;
import java.util.List;

public abstract class Grade {
	private final static List<String> grades = Arrays.asList(
			"3A" , "3A+" , "3B" , "3B+" , "3C" , "3C+" ,
			"4A" , "4A+" , "4B" , "4B+" , "4C" , "4C+" ,
			"5A" , "5A+" , "5B" , "5B+" , "5C" , "5C+" ,
			"6A" , "6A+" , "6B" , "6B+" , "6C" , "6C+" ,
			"7A" , "7A+" , "7B" , "7B+" , "7C" , "7C+" ,
			"8A" , "8A+" , "8B" , "8B+" , "8C" , "8C+" ,
			"9A" , "9A+" , "9B" , "9B+" , "9C" , "9C+" );

	public static List< String > getGrades( ) {
		return grades;
	}
}
