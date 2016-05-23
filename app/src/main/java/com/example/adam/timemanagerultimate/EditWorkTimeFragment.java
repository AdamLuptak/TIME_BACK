package com.example.adam.timemanagerultimate;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.adam.timemanagerultimate.daoWorkTimeRecord.IWorkTimeRecordRepo;
import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import roboguice.fragment.RoboFragment;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

public class EditWorkTimeFragment extends RoboFragment {

    Context thiscontext;

    @Inject
    private IWorkTimeRecordRepo workTimeRecordRepo;

    @InjectView(R.id.workTimeRecordview)
    private ListView listViewWorkTimerecord;

    @InjectView(R.id.buttonWorkTimeRecordAdd)
    private Button buttonWorkTimeRecordAdd;

    @InjectView(R.id.goHomeButton)
    private Button goHomeButton;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        thiscontext = container.getContext();

        View rootView = inflater.inflate(R.layout.fragment_work_time_records, container, false);

//        try {
//            List workTimeRecords = workTimeRecordRepo.getWorkTimeRecords();
//            WorkTimeRecordListAdapter workTimeRecordListAdapter = new WorkTimeRecordListAdapter(
//                    thiscontext.getApplicationContext(), workTimeRecords);
//            listViewWorkTimerecord.setAdapter(workTimeRecordListAdapter);
//
//            buttonWorkTimeRecordAdd.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
////                    Intent intent = new Intent(WorkTimeRecordListActivity.this,
////                            WorkTimeRecordEditActivity.class);
////                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                    startActivityForResult(intent, 0);
////                    finish();
//                }
//            });
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        listViewWorkTimerecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                Intent intent = new Intent(WorkTimeRecordListActivity.this,
////                        WorkTimeRecordEditActivity.class);
////                intent.putExtra("WorkTimeRecord", (WorkTimeRecord) parent.getItemAtPosition(position));
////                startActivityForResult(intent, 0);
////                finish();
//            }
//        });


        return rootView;


    }


}
