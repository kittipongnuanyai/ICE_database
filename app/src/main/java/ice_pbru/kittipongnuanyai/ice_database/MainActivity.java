package ice_pbru.kittipongnuanyai.ice_database;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;

    private MySQLite mySQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //bind widget
        bindwidget();

        mySQLite = new MySQLite(this);

        //Synchronize mySQL to SQlite
        synAndDelete();





    } //Main Method

    private void synAndDelete() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name, MODE_PRIVATE, null);
        sqLiteDatabase.delete(MySQLite.user_table, null, null);

        MySynJSON mySynJSON = new MySynJSON();
        mySynJSON.execute();

    }


    public class MySynJSON extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            try {
                String strURL = "http://ice.pbru.ac.th/ice56/kittipong/php_get_user.php";
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("Kittipong -->", "Do in Back" + e.toString());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("Kittipong -->", "strJSON" + s.toString());
            try {

                JSONArray jsonArray = new JSONArray(s);


                for (int i = 0 ; i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strName = jsonObject.getString(MySQLite.colum_Name);
                    String strSurname = jsonObject.getString(MySQLite.colum_Surname);
                    String strUser = jsonObject.getString(MySQLite.colum_User);
                    String strPassword = jsonObject.getString(MySQLite.colum_Password);
                    String strEmail = jsonObject.getString(MySQLite.colum_Email);

                    mySQLite.addNewUser(strName, strSurname, strUser, strPassword, strEmail);



                    Log.d("Kittipong -->", "name = " + strName.toString());


                }

                Toast.makeText(MainActivity.this, "Sync MySQL to SQLite finish", Toast.LENGTH_LONG).show();


            } catch (Exception e) {

                Log.d("Kittipong -->", "onpost " + e.toString());

            }



            Log.d("Kittipong --> ", "Finish");
        }
    }











    public void clickRegis(View view) {
        startActivity(new Intent(MainActivity.this, SignUp.class));

    }

    public void clickSignIn(View view) {
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        if (checkSpace()) {
            //have space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,"ข้อผิดพลาด", "กรุณาตรวจสอบ username หรือ pass ให้ครบ");
        } else {

        }

    }

    private boolean checkSpace() {
        return userString.equals("")||passwordString.equals("");
    }

    public void testMyAlert(View view) {
        MyAlert myAlert = new MyAlert();
        myAlert.myDialog(this,"Alert","ทดสอบ");

    }

    private void bindwidget() {
        userEditText = (EditText) findViewById(R.id.editText6);
        passwordEditText = (EditText) findViewById(R.id.editText7);


    }//bind widget









}//Main Class
