package com.atharva.project.filters;

import com.atharva.project.MovieDatabase;

public class YearAfterFilter implements Filter {
	private int myYear;
	
	public YearAfterFilter(int year) {
		myYear = year;
	}
	
	@Override
	public boolean satisfies(String id) { //
		return MovieDatabase.getYear(id) >= myYear; // returns true if movies Year greater than given year
	}

}
