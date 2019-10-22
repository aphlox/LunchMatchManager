package june.second.lunchmatchmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static java.sql.DriverManager.println;

public class AdminLoginActivity extends AppCompatActivity {

    public static final String JOIN_APPROVAL = "june.second.lunchmatchmaker.action.ACTION_JOIN_APPROVAL";

    private EditText inputPW;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        inputPW = findViewById(R.id.inputPw);

        Intent startServiceIntent = new Intent(getApplicationContext(), ManagerService.class);
        startService(startServiceIntent);

        //로그캣 확인용
        Log.i("Main", "LoginActivity_onCreate");
        //Toast.makeText(this, "LoginActivity_onCreate", Toast.LENGTH_SHORT).show();
        println("onCreate 호출됨");


        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if("a8696!".equals(inputPW.getText().toString())){
                    Intent intent = new Intent(AdminLoginActivity.this , MainActivity.class);

                    startActivity(intent);
                }
                else{

                    Toast.makeText(getApplicationContext(), "비밀번호가 틀립니다." , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminLoginActivity.this , MainActivity.class);

                    startActivity(intent);
                }


            }
        });






    }




    @Override
    protected void onStart() {
        super.onStart();

        Log.i("Main" ,"LoginActivity_onStart");
        //Toast.makeText(this, "LoginActivity_onStart", Toast.LENGTH_SHORT).show();
        println("onStart 호출됨");

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i("Main" ,"LoginActivity_onRestart");
        //Toast.makeText(this, "LoginActivity_onRestart", Toast.LENGTH_SHORT).show();
        println("onRestart 호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("Main" ,"LoginActivity_onStop");
        //Toast.makeText(this, "LoginActivity_onStop", Toast.LENGTH_SHORT).show();
        println("onStop 호출됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("Main" ,"LoginActivity_onDestroy");
        //Toast.makeText(this, "LoginActivity_onDestroy", Toast.LENGTH_SHORT).show();
        println("onDestroy 호출됨");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("Main", "LoginActivity_onPause");
        //Toast.makeText(this, "LoginActivity_onPause", Toast.LENGTH_SHORT).show();
        println("onPause 호출됨");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("Main" ,"LoginActivity_onResume");
        //Toast.makeText(this, "LoginActivity_onResume", Toast.LENGTH_SHORT).show();
        println("onResume 호출됨");
    }



}
