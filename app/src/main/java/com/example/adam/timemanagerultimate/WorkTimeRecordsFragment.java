package com.example.adam.timemanagerultimate;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.adam.timemanagerultimate.daoWorkTimeRecord.IWorkTimeRecordRepo;
import com.melnykov.fab.FloatingActionButton;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import roboguice.fragment.RoboFragment;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
public class WorkTimeRecordsFragment extends RoboFragment {

    Context thiscontext;


    @Inject
    private IWorkTimeRecordRepo workTimeRecordRepo;

    @InjectView(R.id.workTimeRecordview)
    private ListView listViewWorkTimerecord;

    @InjectView(R.id.fab)
    FloatingActionButton fabButton;

//    @InjectView(R.id.buttonWorkTimeRecordAdd)
//    private Button buttonWorkTimeRecordAdd;
//
//    @InjectView(R.id.goHomeButton)
//    private Button goHomeButton;

    public WorkTimeRecordsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Log.e("sdf","sdfffffffffffffff");
         thiscontext = container.getContext();

        View rootView = inflater.inflate(R.layout.fragment_work_time_records, container, false);




        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            List workTimeRecords = workTimeRecordRepo.getWorkTimeRecords();
            WorkTimeRecordListAdapter workTimeRecordListAdapter = new WorkTimeRecordListAdapter(
                    thiscontext.getApplicationContext(), workTimeRecords);
            listViewWorkTimerecord.setAdapter(workTimeRecordListAdapter);



            fabButton.attachToListView(listViewWorkTimerecord);


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

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        listViewWorkTimerecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(WorkTimeRecordListActivity.this,
//                        WorkTimeRecordEditActivity.class);
//                intent.putExtra("WorkTimeRecord", (WorkTimeRecord) parent.getItemAtPosition(position));
//                startActivityForResult(intent, 0);
//                finish();
            }
        });


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
