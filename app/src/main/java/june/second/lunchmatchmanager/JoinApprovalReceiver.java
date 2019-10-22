package june.second.lunchmatchmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class JoinApprovalReceiver extends BroadcastReceiver {
    public static final String JOIN_APPROVAL = "june.second.lunchmatchmaker.action.ACTION_JOIN_APPROVAL";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (JOIN_APPROVAL.equals(intent.getAction())) {


            //회원 가입시에 뜰 방송
            //노티?? 보내기?
            String userData = intent.getStringExtra("userData");
            Toast.makeText(context, "매니저 - 회원 가입 되었습니다." +userData, Toast.LENGTH_SHORT).show();


            //유저 데이터를 저장한 쉐어드 불러오기
            SharedPreferences prefUser = context.getSharedPreferences("prefUser", MODE_PRIVATE);
            SharedPreferences.Editor prefUserEditor = prefUser.edit();
            prefUserEditor.putString("user",userData); // 키값으로 매치 객체 스트링으로 저장

            prefUserEditor.commit();

            try {
                JSONObject userJsonObject = new JSONObject(userData);
                User user = new User(userJsonObject.getBoolean("userApproval"), userJsonObject.getString("userID"), userJsonObject.getString("userPw"), userJsonObject.getString("userName")
                        , userJsonObject.getString("userGender"), userJsonObject.getString("userBirthday"), userJsonObject.getString("userNickName")
                        , userJsonObject.getString("userNickComment"));

                prefUserEditor.putString(user.getUserId(), userJsonObject.toString()); // 키값으로 매치 객체 스트링으로 저장
//                Toast.makeText(context, userJsonObject.toString(), Toast.LENGTH_SHORT).show();

                prefUserEditor.commit();


            } catch (JSONException e) {
                e.printStackTrace();
//                Toast.makeText(context, "catch", Toast.LENGTH_SHORT).show();


            }
//            Toast.makeText(context, "check", Toast.LENGTH_SHORT).show();

            //이후의 브로드캐스트의 전파를 막기
//            abortBroadcast();
        }
    }
}
