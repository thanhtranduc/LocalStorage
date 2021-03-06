package com.qsoft.student;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


import java.util.Locale;

/**
 * hàm hình chính cho phép chọn các thao tác
 *
 * @author drthanh
 */
public class MainActivity extends Activity {

    Button btnCreateDatabase = null;
    Button btnInsertStudent = null;
    Button btnShowStudentList = null;
    Button btnShowAuthorList2 = null;
    Button btnTransaction = null;
    Button btnShowDetail = null;
    public static final int OPEN_STUDENT_DIALOG = 1;
    public static final int SEND_DATA_FROM_STUDENT_ACTIVITY = 2;
    SQLiteDatabase database = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsertStudent = (Button) findViewById(R.id.btnInsertStudent);
        btnInsertStudent.setOnClickListener(new MyEvent());
        btnShowStudentList = (Button) findViewById(R.id.buttonShowStudentList);
        btnShowStudentList.setOnClickListener(new MyEvent());
        getDatabase();
    }

    /**
     * hàm kiểm tra xem bảng có tồn tại trong CSDL hay chưa
     *
     * @param database  - cơ sở dữ liệu
     * @param tableName - tên bảng cần kiểm tra
     * @return trả về true nếu tồn tại
     */
    public boolean isTableExists(SQLiteDatabase database, String tableName) {
        Cursor cursor = database.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    /**
     * hàm tạo CSDL và các bảng liên quan
     *
     * @return
     */
    public SQLiteDatabase getDatabase() {
        try {
            database = openOrCreateDatabase("mydata.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            if (database != null) {
                if (isTableExists(database, "tblStudents"))
                    return database;
                database.setLocale(Locale.getDefault());
                database.setVersion(1);
                String sqlStudent = "create table tblStudents ("
                        + "id integer primary key autoincrement,"
                        + "studentName text, "
                        + "studentAge integer)";
                database.execSQL(sqlStudent);
//				String sqlBook="create table tblBooks ("
//						+"id integer primary key autoincrement,"
//						+"title text, "
//						+"dateadded date,"
//						+"authorid integer not null constraint authorid references tblAuthors(id) on delete cascade)";
//				database.execSQL(sqlBook);
                //Cách tạo trigger khi nhập dữ liệu sai ràng buộc quan hệ
//				String sqlTrigger="create trigger fk_insert_book before insert on tblBooks "
//						+" for each row "
//						+" begin "
//						+" 	select raise(rollback,'them du lieu tren bang tblBooks bi sai') "
//						+" 	where (select id from tblAuthors where id=new.authorid) is null ;"
//						+" end;";
//				database.execSQL(sqlTrigger);
                Toast.makeText(MainActivity.this, "OK OK", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        return database;
    }

    public void createDatabaseAndTrigger() {
        if (database == null) {
            getDatabase();
            Toast.makeText(MainActivity.this, "OK OK", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * hàm mở màn hình nhập sinh viên
     */
    public void showInsertStudentDialog() {
        Intent intent = new Intent(MainActivity.this, CreateStudentActivity.class);
        startActivityForResult(intent, OPEN_STUDENT_DIALOG);
    }

    /**
     * hàm xem danh sách sinh viên dùng Activity
     * Tôi làm 2 cách để các bạn ôn tập lại ListView
     * bạn gọi hàm nào thì gọi 1 thôi showStudentList1 hoặc showStudentList2
     */
    public void showStudentList1() {
        Intent intent = new Intent(MainActivity.this, ShowListStudentActivity.class);
        startActivity(intent);
    }

    /**
     * hàm xem danh sách student dùng ListActivity
     * Tôi làm 2 cách để các bạn ôn tập lại ListView
     * bạn gọi hàm nào thì gọi 1 thôi showStudentList1 hoặc showStudentList2
     */
    public void showStudentList2() {
        Intent intent = new Intent(MainActivity.this, ShowListStudentActivity2.class);
        startActivity(intent);
    }

    /**
     * Tôi cung cấp thêm hàm này để các bạn nghiên cứu thêm về transaction
     */
    public void interactDBWithTransaction() {
        if (database != null) {
            database.beginTransaction();
            try {
                //làm cái gì đó tùm lum ở đây,
                //chỉ cần có lỗi sảy ra thì sẽ kết thúc transaction
                ContentValues values = new ContentValues();
                values.put("studentName", "xx");
                values.put("studentAge", "yyy");
                database.insert("tblStudents", null, values);
                database.delete("tblStudents", "ma=?", new String[]{"x"});
                //Khi nào hàm này được gọi thì các thao tác bên trên mới thực hiện được
                //Nếu nó không được gọi thì mọi thao tác bên trên đều bị hủy
                database.setTransactionSuccessful();
            } catch (Exception ex) {
                Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
            } finally {
                database.endTransaction();
            }
        }
    }

    /**
     * hàm xử lý kết quả trả về
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SEND_DATA_FROM_STUDENT_ACTIVITY) {
            Bundle bundle = data.getBundleExtra("DATA_STUDENT");
            String name = bundle.getString("studentName");
            String age = bundle.getString("studentAge");
            ContentValues content = new ContentValues();
            content.put("studentName", name);
            content.put("studentAge", age);
            if (database != null) {
                long studentId = database.insert("tblStudents", null, content);
                if (studentId == -1) {
                    Toast.makeText(MainActivity.this, studentId + " - " + name + " - " + age + " ==> insert error!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, studentId + " - " + name + " - " + age + " ==>insert OK!", Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    /**
     * class xử lý sự kiện
     *
     * @author thanh
     */
    private class MyEvent implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (v.getId() == R.id.btnInsertStudent) {
                showInsertStudentDialog();
            } else if (v.getId() == R.id.buttonShowStudentList) {
                showStudentList1();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_simple_database_main, menu);
        return true;
    }
}
