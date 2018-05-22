package id.haerul.flagquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private RadioButton jawaban1,jawaban2,jawaban3, jawaban61, jawaban62, jawaban63, jawaban64, jawaban9;
    private EditText jawaban4s, jawaban7;
    private CheckBox jawaban51, jawaban52, jawaban53, jawaban54, jawaban101, jawaban102;
    private RadioGroup radioGroup6;
    private Button bTambah, bKurang;
    private Spinner jawaban81, jawaban82, jawaban83;
    private ScrollView scrollView;

    Double nilai;
    int jwb7 = 0;
    int jBenar = 0;

    SessionManager sessionManager;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences.Editor editor = getSharedPreferences("RESUME", MODE_PRIVATE).edit();
        editor.putString("back", "0");
        editor.apply();

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        name = user.get(SessionManager.KEY_NAME);

        jawaban1 = (RadioButton)findViewById(R.id.j1);
        jawaban2 = (RadioButton)findViewById(R.id.j2);
        jawaban3 = (RadioButton)findViewById(R.id.j3);
        jawaban4s = (EditText)findViewById(R.id.j4);
        jawaban51 = (CheckBox)findViewById(R.id.j51);
        jawaban52 = (CheckBox)findViewById(R.id.j52);
        jawaban53 = (CheckBox)findViewById(R.id.j53);
        jawaban54 = (CheckBox)findViewById(R.id.j54);
        jawaban61 = (RadioButton)findViewById(R.id.j61);
        jawaban62 = (RadioButton)findViewById(R.id.j62);
        jawaban63 = (RadioButton)findViewById(R.id.j63);
        jawaban64 = (RadioButton)findViewById(R.id.j64);
        radioGroup6 = (RadioGroup)findViewById(R.id.rg6);
        bTambah = (Button)findViewById(R.id.bTambah);
        bKurang = (Button)findViewById(R.id.bKurang);
        jawaban7 = (EditText)findViewById(R.id.j7);
        jawaban81 = (Spinner)findViewById(R.id.j81);
        jawaban82 = (Spinner)findViewById(R.id.j82);
        jawaban83 = (Spinner)findViewById(R.id.j83);
        jawaban9 = (RadioButton)findViewById(R.id.j9);
        jawaban101 = (CheckBox) findViewById(R.id.j101);
        jawaban102 = (CheckBox) findViewById(R.id.j102);

        scrollView = (ScrollView)findViewById(R.id.scroll);


        findViewById(R.id.bSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekJawaban1sd4();
            }
        });

        bTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jwb7 = Integer.parseInt(jawaban7.getText().toString());
                jwb7++;
                jawaban7.setText(String.valueOf(jwb7));
            }
        });

        bKurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jwb7 = Integer.parseInt(jawaban7.getText().toString());
                jwb7--;
                if (jwb7 < 1){
                    jawaban7.setText("1");
                    Toast.makeText(MainActivity.this, "Jumlah minimum yaitu 1 !", Toast.LENGTH_SHORT).show();
                }else {
                    jawaban7.setText(String.valueOf(jwb7));
                }
            }
        });

        findViewById(R.id.bUlang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out_from_view);
                finish();
            }
        });

    }

    private void cekJawaban1sd4(){

        nilai = 0.0;

        if (jawaban1.isChecked()){
            nilai++;
        }
        if (jawaban2.isChecked()){
            nilai++;
        }
        if (jawaban3.isChecked()){
            nilai++;
        }
        String jawaban4 = jawaban4s.getText().toString();
        if (jawaban4.equals("France") || jawaban4.equals("france") || jawaban4.equals("prancis") || jawaban4.equals("Prancis")){
            nilai++;
        }

        if (jawaban51.isChecked()){
            nilai = nilai + 0.25;
        }

        if (jawaban52.isChecked()){
            nilai = nilai + 0.25;
        }

        if (jawaban53.isChecked()){
            nilai = nilai + 0.25;
        }

        if (jawaban54.isChecked()){
            nilai = nilai + 0.25;
        }

        if (jawaban61.isChecked()){
            nilai = nilai + 0.50;
        }

        if (jawaban62.isChecked()){
            nilai = nilai + 0.50;
        }

        if (jawaban63.isChecked()){
            nilai = nilai + 0.50;
        }

        if (jawaban64.isChecked()){
            nilai++;
        }

        if (jawaban7.getText().toString().equals("6")){
            nilai++;
        }

        if (jawaban81.getSelectedItem().equals("Hitam") && jawaban82.getSelectedItem().equals("Merah") && jawaban83.getSelectedItem().equals("Orange")){
            nilai++;
        }

        if (jawaban9.isChecked()){
            nilai++;
        }

        if (jawaban101.isChecked()){
            nilai = nilai + 0.50;
        }

        if (jawaban102.isChecked()){
            nilai = nilai + 0.50;
        }

        String sNilai = String.valueOf(nilai);

        Intent i = new Intent(MainActivity.this, ScoreActivity.class);
        i.putExtra("nilai", sNilai);
        startActivity(i);
        overridePendingTransition(R.anim.zoom_in_from_view, R.anim.zoom_out_from_view);

    }

    protected void onResume() {

        HashMap<String, String> user = sessionManager.getUserDetails();
        name = user.get(SessionManager.KEY_NAME);

        super.onResume();
        SharedPreferences spref = getSharedPreferences("RESUME", MODE_PRIVATE);
        String back = spref.getString("back", null);

        if (back.equals("1")) {
            Toast.makeText(MainActivity.this, "Selamat mengerjakan ulang "+name, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out_from_view);
            finish();
        } else if (back.equals("2")) {
            jawaban1.setBackgroundColor(Color.YELLOW);
            jawaban2.setBackgroundColor(Color.YELLOW);
            jawaban3.setBackgroundColor(Color.YELLOW);
            jawaban4s.setBackgroundColor(Color.YELLOW);
            jawaban4s.setText("France/Prancis");
            jawaban51.setBackgroundColor(Color.YELLOW);
            jawaban52.setBackgroundColor(Color.YELLOW);
            jawaban53.setBackgroundColor(Color.YELLOW);
            jawaban54.setBackgroundColor(Color.YELLOW);
            jawaban64.setBackgroundColor(Color.YELLOW);
            jawaban7.setBackgroundColor(Color.YELLOW);
            jawaban7.setText("6");
            jawaban81.setBackgroundColor(Color.YELLOW);
            jawaban81.setSelection(3);
            jawaban82.setBackgroundColor(Color.YELLOW);
            jawaban82.setSelection(1);
            jawaban83.setBackgroundColor(Color.YELLOW);
            jawaban83.setSelection(4);
            jawaban9.setBackgroundColor(Color.YELLOW);
            jawaban101.setBackgroundColor(Color.YELLOW);
            jawaban102.setBackgroundColor(Color.YELLOW);
            findViewById(R.id.bSubmit).setVisibility(View.GONE);
            findViewById(R.id.bUlang).setVisibility(View.VISIBLE);

            scrollView.fullScroll(View.FOCUS_UP);
            Toast.makeText(MainActivity.this, "Review jawaban anda "+name, Toast.LENGTH_SHORT).show();
        } else if (back.equals("3")) {
            Toast.makeText(MainActivity.this, name +  " anda memulai ulang quiz!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out_from_view);
            finish();
        } else if (back.equals("4")) {
            startActivity(new Intent(MainActivity.this, InputNameActivity.class));
            overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out_from_view);
            finish();
        } else {
            Toast.makeText(MainActivity.this, "Selamat mengerjakan "+name, Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = getSharedPreferences("RESUME", MODE_PRIVATE).edit();
            editor.putString("back", "0");
            editor.apply();
        }
    }

    @Override
    public void onBackPressed() {

        tanyaKeluar();

    }

    public void tanyaKeluar(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Konfirmasi");
        alertDialog.setMessage("Apakah anda yakin akan keluar? ");

        alertDialog.setNegativeButton((CharSequence) " Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        alertDialog.setPositiveButton((CharSequence) "Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.profil) {

            startActivity(new Intent(MainActivity.this, ProfilActivity.class));
            overridePendingTransition(R.anim.zoom_in_from_view, R.anim.zoom_out_from_view);
        }
        if (id == R.id.bantuan) {

            final View views = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_dialog_bantuan, null);
            final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
            final ImageButton vclose = (ImageButton) views.findViewById(R.id.close);
            dialog.setView(views);
            dialog.show();

            vclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

        }
        if (id == R.id.tentang) {

            final View views = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_dialog_tentang, null);
            final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
            final ImageButton vclose = (ImageButton) views.findViewById(R.id.close);
            dialog.setView(views);
            dialog.show();

            final RelativeLayout email = (RelativeLayout)views.findViewById(R.id.email);
            final RelativeLayout phone = (RelativeLayout)views.findViewById(R.id.phone);
            final RelativeLayout sms = (RelativeLayout)views.findViewById(R.id.sms);
            final RelativeLayout wa = (RelativeLayout)views.findViewById(R.id.wa);

            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("mailto:haerul.muttaqin@gmail.com"));
                    startActivity(intent);
                }
            });

            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("tel:+6282111237599"));
                    startActivity(intent);

                }
            });

            sms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("smsto:+62895615773513"));
                    startActivity(intent);

                }
            });

            wa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("smsto:+6282111237599"));
                    intent.setPackage("com.whatsapp");
                    startActivity(intent);

                }
            });

            vclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }
}
