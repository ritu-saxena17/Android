package com.example.l3_cd;

import android.os.Bundle;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnInsert, btnUpdate, btnSelect, btnDelete;
    private DbHelper dbHelper;
    private Cursor cursor;
    private EditText etname,etsalary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        Initializing Database
        dbHelper = new DbHelper(getApplicationContext());

//        Getting ids for all buttons
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        etname=(EditText)findViewById(R.id.etname);
        etsalary=(EditText)findViewById(R.id.etsalary);

//        Attaching listeners for all buttons
        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        etname.setOnClickListener(this);
        etsalary.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnInsert:
                String getname=etname.getText().toString();
                String getid=etsalary.getText().toString();
                dbHelper.inserRecord(getname,getid);
                Toast.makeText(MainActivity.this,"Inserted  Successfully!\n"+getname+"\n"+getid,Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this,getid,Toast.LENGTH_LONG).show();
                break;

            case R.id.btnUpdate:
                getname = etname.getText().toString();
                getid = etsalary.getText().toString();
                boolean isUpdate = dbHelper.updateRecord(getname,getid);
                if (isUpdate == true)
                    Toast.makeText(MainActivity.this,"Record Updated Successfully..!  \n" , Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Record Not Updated Successfully..!  \n" , Toast.LENGTH_LONG).show();
                break;

            case R.id.btnSelect:

                cursor = dbHelper.selectRecords();

                if (cursor.moveToFirst()) {

                    do {

                        String strName = cursor.getString(cursor.getColumnIndex(DbHelper.NAME));
                        String strSalary = cursor.getString(cursor.getColumnIndex(DbHelper.SALARY));

                        Toast.makeText(MainActivity.this, "Values are \n Name :" + strName + "\n Salary :" + strSalary, Toast.LENGTH_LONG).show();

                    } while (cursor.moveToNext());

                }
                cursor.close();

                break;

            case R.id.btnDelete:

                dbHelper.deleteRecord();
                Toast.makeText(MainActivity.this, "Record Deleted Successfully..!", Toast.LENGTH_LONG).show();

                break;

        }
    }
}