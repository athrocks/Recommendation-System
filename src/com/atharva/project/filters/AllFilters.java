package com.atharva.project.filters;

import java.util.ArrayList;

public class AllFilters implements Filter {
    ArrayList<Filter> filters;
    
    public AllFilters() {
        filters = new ArrayList<>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    @Override
    public boolean satisfies(String id) {
        for(Filter f : filters) {
            if (!f.satisfies(id)) { // if (! f.satisfies(id)) ==> f.satisfies(id) == false
                return false;
            }
        }
        
        return true;
    }

}
