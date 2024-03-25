package ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.pasc.lib.videolib.R;
import com.just.agentweb.AgentWebView;

import common.callback.VideoInteractiveParam;
import widget.BaseWebView;
import widget.SzrmWebView;

public class TgtCodeActivity extends AppCompatActivity {
    private TextView tgt;
    private TextView to_video;
    private SzrmWebView szrmWeb;
    private RelativeLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tgt_code);
        tgt = findViewById(R.id.tgt);
        to_video = findViewById(R.id.to_video);
        to_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TgtCodeActivity.this, VideoDetailActivity.class);
                intent.putExtra("contentId", "737797");
                startActivity(intent);
            }
        });
        try {
            tgt.setText("tgt码:" + VideoInteractiveParam.getInstance().getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        szrmWeb = new SzrmWebView(this, container, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
    }
}