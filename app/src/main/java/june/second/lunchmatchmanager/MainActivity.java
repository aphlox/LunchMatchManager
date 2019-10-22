package june.second.lunchmatchmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String here = "MainActivity";
    public static final String JOIN_RESULT = "june.second.lunchmatchmaker.action.ACTION_JOIN_RESULT";

    private Intent serviceIntent;

    static ArrayList<User> userArrayList = new ArrayList<>();

    TextView textUserId;
    TextView textUserName;
    TextView textUserGender;
    TextView textUserBirthday;
    TextView textUserNickName;
    TextView textUserComment;

    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //죽지 않는 서비스
        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(POWER_SERVICE);
        boolean isWhiteListing = false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            isWhiteListing = pm.isIgnoringBatteryOptimizations(getApplicationContext().getPackageName());
        }
        if (!isWhiteListing) {
            Intent intent = new Intent();
            intent.setAction(android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
            startActivity(intent);
        }

        if (RealService.serviceIntent==null) {
            serviceIntent = new Intent(this, RealService.class);
            startService(serviceIntent);
        } else {
            serviceIntent = RealService.serviceIntent;//getInstance().getApplication();
            Toast.makeText(getApplicationContext(), "already", Toast.LENGTH_LONG).show();
        }

        Intent startServiceIntent = new Intent(getApplicationContext(), ManagerService.class);
        startService(startServiceIntent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.w(here, here + "  onResume");

        //유저 데이터를 저장한 쉐어드 불러오기
        SharedPreferences prefUser = getSharedPreferences("prefUser", MODE_PRIVATE);
        SharedPreferences.Editor prefUserEditor = prefUser.edit();


/*        Map<String, ?> allEntries = prefUser.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {

            Log.w("map values", entry.getKey() + ": " + entry.getValue().toString());
        }*/
        try {
            JSONObject userJsonObject = new JSONObject(prefUser.getString("user", ""));
            user = new User(userJsonObject.getBoolean("userApproval"), userJsonObject.getString("userId"), userJsonObject.getString("userPw"), userJsonObject.getString("userName")
                    , userJsonObject.getString("userGender"), userJsonObject.getString("userBirthday"), userJsonObject.getString("userNickName")
                    , userJsonObject.getString("userComment"));
            textUserId = findViewById(R.id.userId);
            textUserId.setText(user.getUserId());
            textUserName = findViewById(R.id.userName);
            textUserName.setText(user.getUserName());
            textUserGender = findViewById(R.id.userGender);
            textUserGender.setText(user.getUserGender());
            textUserBirthday = findViewById(R.id.userBirthday);
            textUserBirthday.setText(user.getUserBirthday());
            textUserNickName = findViewById(R.id.userNickName);
            textUserNickName.setText(user.getUserNickName());
            textUserComment = findViewById(R.id.userComment);
            textUserComment.setText(user.getUserNickComment());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();


//                Toast.makeText(getApplicationContext(), "승인했습니다", Toast.LENGTH_LONG).show();
                sendMyBroadcast(user.getUserId(),"ok");
                Log.w("sendMyBroadcast", user.getUserId() );

            }
        });



        Button btnNo = findViewById(R.id.btnNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();


                Toast.makeText(getApplicationContext(), "거절했습니다", Toast.LENGTH_LONG).show();
                sendMyBroadcast(user.getUserId(),"no");
            }
        });




/*        TextView testText = findViewById(R.id.testText);
        testText.setText( userArrayList.get(0).getUserId());*/

    }


    public void sendMyBroadcast(String userId,String joinResult) {
        Intent intent = new Intent(JOIN_RESULT);
        intent.putExtra("userId", userId);
        intent.putExtra("joinResult",joinResult);
        sendBroadcast(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Main" ,"LoginActivity_onDestroy");
/*        if (serviceIntent!=null) {
            stopService(serviceIntent);
            serviceIntent = null;
        }*/
    }

}

