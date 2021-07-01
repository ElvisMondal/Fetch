package com.fetch;

import java.util.Comparator;

public class SortByID implements Comparator<DataIntilization> {
    @Override
    public int compare(DataIntilization dataIntilization, DataIntilization t1) {
        if(t1.getListID()>dataIntilization.getListID())
            return -1;
        else if (t1.getListID()<dataIntilization.getListID())
            return 1;
        else
            return 0;
    }
}
