package id.haerul.flagquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class FlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        SharedPreferences.Editor editor = getSharedPreferences("RESUME", MODE_PRIVATE).edit();
        editor.putString("back", "0");
        editor.apply();

        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom2);
        findViewById(R.id.attrBanner).startAnimation(animation1);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom);
        findViewById(R.id.textBanner).startAnimation(animation2);
        Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_top);
        findViewById(R.id.iconFlag).startAnimation(animation3);
        Animation animation4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_top);
        findViewById(R.id.progressBar).startAnimation(animation4);
        Animation animation5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        findViewById(R.id.bgFlash).startAnimation(animation5);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(FlashActivity.this, InputNameActivity.class));
                overridePendingTransition(R.anim.zoom_in_from_view, R.anim.zoom_out_from_view);
                finish();
            }
        }, 3000);
    }
}
