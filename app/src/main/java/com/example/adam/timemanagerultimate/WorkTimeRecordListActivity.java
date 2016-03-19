package com.example.adam.timemanagerultimate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.example.adam.timemanagerultimate.daoWorkTimeRecord.IWorkTimeRecordRepo;
import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_work_time_record_list)
public class WorkTimeRecordListActivity extends RoboActivity {

    @Inject
    private IWorkTimeRecordRepo workTimeRecordRepo;

    @InjectView(R.id.workTimeRecordview)
    private ListView listViewWorkTimerecord;

    @InjectView(R.id.buttonWorkTimeRecordAdd)
    private Button buttonWorkTimeRecordAdd;

    @InjectView(R.id.goHomeButton)
    private Button goHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_time_record_list);
        try {
            List workTimeRecords = workTimeRecordRepo.getWorkTimeRecords();
            WorkTimeRecordListAdapter workTimeRecordListAdapter = new WorkTimeRecordListAdapter(
                    getApplicationContext(), workTimeRecords);
            listViewWorkTimerecord.setAdapter(workTimeRecordListAdapter);

            buttonWorkTimeRecordAdd.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(WorkTimeRecordListActivity.this,
                            WorkTimeRecordEditActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivityForResult(intent, 0);
                    finish();
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        listViewWorkTimerecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(WorkTimeRecordListActivity.this,
                        WorkTimeRecordEditActivity.class);
                intent.putExtra("WorkTimeRecord", (WorkTimeRecord) parent.getItemAtPosition(position));
                startActivityForResult(intent, 0);
                finish();
            }
        });
    }

    public void goHome(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        Intent intent2 = getIntent();
        String inWork = intent2.getStringExtra("inWork");
        mainIntent.putExtra("inWork", inWork);
        startActivity(mainIntent);
    }
}
