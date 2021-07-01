package com.fetch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private final List<DataIntilization> dList = new ArrayList<>();
    private final List<DataIntilization> dtList = new ArrayList<>();


    private RecyclerView recyclerView;
    dAdapter nAdap;

    Map<Integer, List<DataIntilization>> gID = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycle);
        nAdap = new dAdapter(dList, this);
        recyclerView.setAdapter(nAdap);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DataClass dataRunnable = new DataClass(this);
        new Thread(dataRunnable).start();


    }

    public void addInfo(List<DataIntilization> oList) {

        dtList.addAll(oList);

        group();

    }

    public void groupedInfo(List<DataIntilization> olList){

        dList.addAll(olList);
        nAdap.notifyDataSetChanged();
    }



    public void group(){

        List<DataIntilization> list=new ArrayList<>();

      for(DataIntilization l:dtList) {
          try {
              int p = l.getListID();
              if (gID.containsKey(p)) {
                  list = gID.get(p);
                  list.add(l);

              } else {
                  list = new ArrayList<DataIntilization>();
                  list.add(l);
                  gID.put(p, list);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
      }



        int[] a ={4,3,2,1};
        int i=0;
        for(int x=0;x<4;x++) {
            try {
                if(i<=4) {
                    groupedInfo(gID.get(a[i]));
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sort(){

        dList.sort(new SortByID());
        nAdap.notifyDataSetChanged();
    }

    public void sortname(){

        String p,num="";
        int n,t;
        for (DataIntilization l : dtList) {
            try {
                p = l.getName();
                if ((p.length() > 0)&&(!p.equals("null"))) {
                    num = p.substring(5);
                    n = Integer.parseInt(num);
                    t = n;

                    dList.sort(new DataIntilization(n, t, p));
                    nAdap.notifyDataSetChanged();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }



    }


    public void emptyname() {

        List<DataIntilization> lists = new ArrayList<>();
        String p = "";
        for (DataIntilization l : dtList) {
            try {
                p = l.getName();
                if ((p.length() > 0)&&(!p.equals("null"))) {
                     lists.add(l);
                }
            }

            catch (Exception e) {
                e.printStackTrace();
            }


        }

        groupedInfo(lists);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.identities:

                sort();

                return true;

            case R.id.name:

                sortname();

                return true;

            case R.id.eName:
                dList.clear();
                emptyname();

                return true;

            default:

                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }
}