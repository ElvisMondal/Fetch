package com.fetch;

import java.io.Serializable;
import java.util.Comparator;

public class DataIntilization implements Serializable, Comparator<DataIntilization> {

    int id,listID;
    String name;

    public DataIntilization(int id, int listID, String name) {
        this.id = id;
        this.listID = listID;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public int getListID() {
        return listID;
    }

    public String getName() {
        return name;
    }



    @Override
    public String toString() {
        return id+" "+listID+" "+name;
    }

//For Sorting the Name
    @Override
    public int compare(DataIntilization dataIntilization, DataIntilization t1) {
        return  Integer.compare(t1.id, dataIntilization.id);
    }
}
