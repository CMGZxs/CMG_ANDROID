package ui.activity;

import static common.utils.AppInit.appId;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.pasc.lib.videolib.R;

import common.utils.PersonInfoManager;
import common.utils.SPUtils;
import common.utils.ToastUtils;
import event.SingleLiveEvent;

public class LoginActivity extends AppCompatActivity {
    private TextView login;
    private EditText userIdEtv;
    private EditText tel;
    private EditText adress;
    private EditText nickNameEtv;
    private TextView changeUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userIdEtv = findViewById(R.id.userId);
        tel = findViewById(R.id.tel);
        adress = findViewById(R.id.headProfile);
        nickNameEtv = findViewById(R.id.nickName);

        //登录
        findViewById(R.id.loginA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonInfoManager.getInstance().setRequestUserId("123879419");
                PersonInfoManager.getInstance().setRequestUserHead("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202003%2F22%2F20200322205340_ioizc.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1680855933&t=c2a4708710cd93db7e75697a68dedf03");
                PersonInfoManager.getInstance().setRequestUserNickName("loginA");
                PersonInfoManager.getInstance().setRequestUserPhone("123131231");
                ToastUtils.showShort("登录成功");
                finish();
            }
        });

        findViewById(R.id.loginB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonInfoManager.getInstance().setRequestUserId("1238341479419");
                PersonInfoManager.getInstance().setRequestUserHead("测试");
                PersonInfoManager.getInstance().setRequestUserNickName("loginB");
                PersonInfoManager.getInstance().setRequestUserPhone("12313123131");
                ToastUtils.showShort("登录成功");
                finish();
            }
        });

    }
}