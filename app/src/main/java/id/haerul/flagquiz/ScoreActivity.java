package id.haerul.flagquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;

public class ScoreActivity extends AppCompatActivity {

    private TextView mScore, mUlasan, mNilai, mTop;
    private RatingBar mRat;
    String sSkor, top,last,name;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        SharedPreferences.Editor editor = getSharedPreferences("RESUME", MODE_PRIVATE).edit();
        editor.putString("back", "0");
        editor.apply();

        mScore = (TextView) findViewById(R.id.mScore);
        mUlasan = (TextView) findViewById(R.id.txtUlasans);
        mNilai = (TextView) findViewById(R.id.txtNilai);
        mRat = (RatingBar)findViewById(R.id.rat);
        mTop = (TextView)findViewById(R.id.txtTop);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        sSkor = bundle.getString("nilai");

        mScore.setText(String.valueOf(sSkor));

        setRating();
        hitungNilai();

        findViewById(R.id.review).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("RESUME", MODE_PRIVATE).edit();
                editor.putString("back", "2");
                editor.apply();
                finish();
            }
        });

        findViewById(R.id.ulang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("RESUME", MODE_PRIVATE).edit();
                editor.putString("back", "3");
                editor.apply();
                finish();
            }
        });

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        top = user.get(SessionManager.KEY_TOP);
        last = user.get(SessionManager.KEY_LAST);
        name = user.get(SessionManager.KEY_NAME);

        setSkor();
    }

    private void setRating(){
        Double intScore = Double.valueOf(sSkor);
        if (intScore < 5.0){
            mUlasan.setText("LUMAYAN!");
            mRat.setRating((float)1.5);
        } else if (intScore > 5.0 && intScore < 6.5){
            mUlasan.setText("LUMAYAN!");
            mRat.setRating((float)2.5);
        }else if (intScore > 6.5 && intScore < 8.0){
            mUlasan.setText("BAGUS!");
            mRat.setRating((float)4.0);
        }else if (intScore > 8.0 && intScore < 10.0){
            mUlasan.setText("MEMUASKAN!");
            mRat.setRating((float)4.5);
        }

        if (intScore == 0.0){
            mUlasan.setText("UPSS!");
            mRat.setRating((float)0.0);
        }

        if (intScore == 10.0){
            mUlasan.setText("MEMUASKAN!");
            mRat.setRating((float)5.0);
        }
    }

    private void hitungNilai(){
        Double Score = Double.valueOf(sSkor);
        Double Scores = Score / 10;

        Double Nilai = Scores * 100;

        mNilai.append(String.valueOf(Nilai));
    }

    private void setSkor(){

        sessionManager.createLoginSession(name,String.valueOf(top),sSkor);

        Float tops = Float.valueOf(top);
        Float lasts = Float.valueOf(sSkor);
        if (lasts > tops){
            mTop.append(sSkor);
            sessionManager.createLoginSession(name,sSkor,sSkor);
        }else {
            mTop.append(top);
            sessionManager.createLoginSession(name,top,sSkor);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences.Editor editor = getSharedPreferences("RESUME", MODE_PRIVATE).edit();
        editor.putString("back", "1");
        editor.apply();
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out_from_view);
    }
}
