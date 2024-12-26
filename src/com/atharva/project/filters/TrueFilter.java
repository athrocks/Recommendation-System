package com.atharva.project.filters;

public class TrueFilter implements Filter {
	@Override
	public boolean satisfies(String id) { //returns true if movieID exist
		return true;
	}

}
