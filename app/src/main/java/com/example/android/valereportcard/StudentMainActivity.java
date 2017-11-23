package com.example.android.valereportcard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class StudentMainActivity extends AppCompatActivity{



    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private TextView mDetailTextView;


    TextView count;

    EditText ename, eroll_no, emarks;
    Button add,view,viewall,show1,delete,modify;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);





        ename = (EditText) findViewById(R.id.name);
        eroll_no = (EditText) findViewById(R.id.rool_no);
        emarks = (EditText) findViewById(R.id.marks);
        add = (Button) findViewById(R.id.addbtn);
        view = (Button) findViewById(R.id.viewbtn);
        viewall = (Button) findViewById(R.id.viewallbtn);
        delete = (Button) findViewById(R.id.deletebtn);
        show1 = (Button) findViewById(R.id.showbtn);
        modify = (Button) findViewById(R.id.modifybtn);

        db = openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno INTEGER, name VARCHAR,marks INTEGER)");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eroll_no.getText().toString().trim().length() == 0 ||
                        ename.getText().toString().trim().length() == 0 ||
                        emarks.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter all values");
                    return;




                }

                db.execSQL("INSERT INTO student VALUES('"+eroll_no.getText()+"','"+ename.getText()+
                        "','"+emarks.getText()+"');");
                showMessage("Success", "Record added successfully");
                clearText();

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (eroll_no.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + eroll_no.getText() + "'", null);
                if (c.moveToFirst()) {
                    db.execSQL("DELETE FROM student WHERE rollno='" + eroll_no.getText() + "'");
                    showMessage("Success", "Record Deleted");
                } else {
                    showMessage("Error", "Invalid Rollno");
                }
                clearText();
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (eroll_no.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + eroll_no.getText() + "'", null);
                if (c.moveToFirst()) {
                    db.execSQL("UPDATE student SET name='" + ename.getText() + "',marks='" + emarks.getText() +
                            "' WHERE rollno='" + eroll_no.getText() + "'");
                    showMessage("Success", "Record Modified");
                } else {
                    showMessage("Error", "Invalid Rollno");
                }
                clearText();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (eroll_no.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + eroll_no.getText() + "'", null);
                if (c.moveToFirst()) {
                    ename.setText(c.getString(1));
                    emarks.setText(c.getString(2));
                } else {
                    showMessage("Error", "Invalid Rollno");
                    clearText();
                }
            }
        });

        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT * FROM student", null);
                if (c.getCount() == 0) {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    buffer.append("Rollno: " + c.getString(0) + "\n");
                    buffer.append("Name: " + c.getString(1) + "\n");
                    buffer.append("Marks: " + c.getString(2) + "\n\n");
                }
                showMessage("Student Details", buffer.toString());
            }
        });

        show1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showMessage("Student Management Application", "Valenciah Masete");
            }
        });
    }








    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        eroll_no.setText("");
        ename.setText("");
        emarks.setText("");
        eroll_no.requestFocus();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                //add the function to perform here
                return (true);
            case R.id.reset:
                //add the function to perform here
                return (true);
            case R.id.about:
                //add the function to perform here
                return (true);
            case R.id.exit:
                //add the function to perform here
                return (true);
        }
        return (super.onOptionsItemSelected(item));


    }

    }
