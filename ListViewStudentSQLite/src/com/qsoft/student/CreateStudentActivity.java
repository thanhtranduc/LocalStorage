package com.qsoft.student;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * class nhập thông tin tác giả
 * Mọi thay đổi đều gửi thông tin về MainActivity để xử lý
 * @author thanh
 *
 */
public class CreateStudentActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
        final Button btnInsert =(Button) findViewById(R.id.buttonInsert);
        final EditText txtName=(EditText) findViewById(R.id.editTextName);
		final EditText txtAge=(EditText) findViewById(R.id.editTextAge);
		 final  Intent intent= getIntent();
        btnInsert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				Bundle bundle=new Bundle();
				bundle.putString("studentName", txtName.getText().toString());
				bundle.putString("studentAge", txtAge.getText().toString());
				intent.putExtra("DATA_AUTHOR", bundle);
				setResult(MainActivity.SEND_DATA_FROM_AUTHOR_ACTIVITY, intent);
				CreateStudentActivity.this.finish();
			}
		});
        final Button btnClear=(Button) findViewById(R.id.buttonClear);
        btnClear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txtName.setText("");
				txtAge.setText("");
				txtName.requestFocus();
			}
		});
       
        Bundle bundle= intent.getBundleExtra("DATA");
        if(bundle!=null && bundle.getInt("KEY")==1)
        {
        	String f2=bundle.getString("getField2");
        	String f3=bundle.getString("getField3");
			txtName.setText(f2);
			txtAge.setText(f3);
			btnInsert.setText("Update");
			this.setTitle("View Detail");
			/*TableRow row=(TableRow) findViewById(R.id.tableRow3);
			row.removeViewAt(0);
			row.setGravity(Gravity.RIGHT);*/
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_student, menu);
        return true;
    }
}
