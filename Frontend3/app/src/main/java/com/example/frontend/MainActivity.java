package com.example.frontend;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private UrlKezelo urlKezelo;
    private static final int REQUEST_CAMERA_PERMISSION_CODE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    ActivityResultLauncher<Uri> takePictureLauncher;
    Button bejelentkezes_button, regisztracios_button, bejelentkez_button, regisztracio_button, fooldal_felvetel_button, fooldal_kategoria_button, fooldal_statisztika_button, nyugtas_kiadas_button, kep_button;
    LinearLayout nincs_bejelentkezve_layout, bejelentkezes_layout, regisztracio_layout, fooldal_layout, felvetel_valaszto_layout, nyugtas_kiadas_layout;
    EditText regisztracio_felhasznalonev_editText, regisztracio_jelszo_editText, regisztracio_email_editText, bejelentkezes_felhasznalonev_editText, bejelentkezes_jelszo_editText, regisztracio_egyenleg_editText;
    TextView regisztracio_felhasznalonev_text, regisztracio_email_text, regisztracio_jelszo_text, bejelentkezes_felhasznalonev_text, bejelentkezes_jelszo_text, regisztracio_egyenleg_text;

    ImageView kep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bejelentkezes_button = findViewById(R.id.bejelentkezes_button);
        regisztracios_button = findViewById(R.id.regisztracios_button);
        bejelentkez_button = findViewById(R.id.bejelentkez_button);
        regisztracio_button = findViewById(R.id.regisztracio_button);
        fooldal_felvetel_button = findViewById(R.id.fooldal_felvetel_button);
        fooldal_kategoria_button = findViewById(R.id.fooldal_kategoria_button);
        fooldal_statisztika_button = findViewById(R.id.fooldal_statisztika_button);
        nyugtas_kiadas_button = findViewById(R.id.nyugtas_kiadas_button);
        kep_button = findViewById(R.id.kep_button);

        nincs_bejelentkezve_layout = findViewById(R.id.nincs_bejelentkezve_layout);
        bejelentkezes_layout = findViewById(R.id.bejelentkezes_layout);
        regisztracio_layout = findViewById(R.id.regisztracio_layout);
        fooldal_layout = findViewById(R.id.fooldal_layout);
        felvetel_valaszto_layout = findViewById(R.id.felvetel_valaszto_layout);
        nyugtas_kiadas_layout = findViewById(R.id.nyugtas_kiadas_layout);

        regisztracio_felhasznalonev_editText = findViewById(R.id.regisztracio_felhasznalonev_editText);
        regisztracio_jelszo_editText = findViewById(R.id.regisztracio_jelszo_editText);
        regisztracio_email_editText = findViewById(R.id.regisztracio_email_editText);
        regisztracio_egyenleg_editText = findViewById(R.id.regisztracio_egyenleg_editText);
        bejelentkezes_felhasznalonev_editText = findViewById(R.id.bejelentkez_felhasznalonev_editText);
        bejelentkezes_jelszo_editText = findViewById(R.id.bejelentkez_jelszo_editText);

        regisztracio_felhasznalonev_text = findViewById(R.id.regisztracio_felhasznalonev_text);
        regisztracio_email_text = findViewById(R.id.regisztracio_email_text);
        regisztracio_jelszo_text = findViewById(R.id.regisztracio_jelszo_text);
        regisztracio_egyenleg_text = findViewById(R.id.regisztracio_egyenleg_text);
        bejelentkezes_felhasznalonev_text = findViewById(R.id.bejelentkezes_felhasznalonev_text);
        bejelentkezes_jelszo_text = findViewById(R.id.bejelentkezes_jelszo_text);

        kep = findViewById(R.id.kep);

        urlKezelo = new UrlKezelo(this, bejelentkezes_button, regisztracios_button, bejelentkez_button, regisztracio_button, fooldal_felvetel_button, fooldal_kategoria_button, fooldal_statisztika_button, nyugtas_kiadas_button, kep_button,
                nincs_bejelentkezve_layout, bejelentkezes_layout, regisztracio_layout, fooldal_layout, felvetel_valaszto_layout, nyugtas_kiadas_layout,
                regisztracio_felhasznalonev_editText, regisztracio_jelszo_editText, regisztracio_email_editText, bejelentkezes_felhasznalonev_editText, bejelentkezes_jelszo_editText, regisztracio_egyenleg_editText,
                regisztracio_felhasznalonev_text, regisztracio_email_text, regisztracio_jelszo_text, bejelentkezes_felhasznalonev_text, bejelentkezes_jelszo_text, regisztracio_egyenleg_text,
                kep);

    }
        /*Window window = getWindow();

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        frontend = new Frontend(this, width, height);
        setContentView(frontend);*/

        //frontend.start();


    public void foto(View view){
        felvetel_valaszto_layout.setVisibility(View.INVISIBLE);
        nyugtas_kiadas_layout.setVisibility(View.VISIBLE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA_PERMISSION_CODE);
            return;
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            urlKezelo.frontend.kep_bitmap(imageBitmap);


            kep.setImageBitmap(imageBitmap);

        }

    }
}
