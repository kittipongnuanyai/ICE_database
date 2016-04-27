package ice_pbru.kittipongnuanyai.ice_database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, surnameEditText, userEditText, passwordEditText, emailEditText;
    private String nameString, surnameString ,userString, passwordString, emailString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //BindWidget
        bindWidget();



    }//Main method

    public void clickSignUp (View view) {
        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();

        if (checkSpace()) {
            //Have space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,"มีช่องว่าง","กรุณาตรวจสอบข้อบมูลและกรอกให้ครบถ้วน");

        } else {
            //No space

        }



    }//clickSignUp

    private boolean checkSpace() {
        return nameString.equals("")||
                surnameString.equals("")||
                userString.equals("")||
                passwordString.equals("")||
                emailEditText.equals("");
    }


    private void bindWidget() {
        nameEditText = (EditText) findViewById(R.id.editText);
        surnameEditText = (EditText) findViewById(R.id.editText2);
        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        emailEditText = (EditText) findViewById(R.id.editText5);



    }//bind widget



}//Main Class
