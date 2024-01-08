package com.example.frontend;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;

public class Frontend1 extends Thread {

    Context context;

    String currentPhotoPath;

    UrlKezelo urlKezelo;


    Button bejelentkezes_button, regisztracios_button, bejelentkez_button, regisztracio_button, fooldal_felvetel_button, fooldal_kategoria_button, fooldal_statisztika_button, nyugtas_kiadas_button;
    LinearLayout nincs_bejelentkezve_layout, bejelentkezes_layout, regisztracio_layout, fooldal_layout, nyugtas_kiadas_layout;
    EditText regisztracio_felhasznalonev_editText, regisztracio_jelszo_editText, regisztracio_email_editText, bejelentkezes_felhasznalonev_editText, bejelentkezes_jelszo_editText, regisztracio_egyenleg_editText;
    TextView regisztracio_felhasznalonev_text, regisztracio_email_text, regisztracio_jelszo_text, bejelentkezes_felhasznalonev_text, bejelentkezes_jelszo_text, regisztracio_egyenleg_text;

    FrameLayout nyugtas_kiadas_frame;

    Uri imageUri;

    Camera camera;

    public boolean futas = false;
    private CameraPreview cameraPreview;

    public Frontend1(UrlKezelo urlKezelo, Context context, Button bejelentkezes_button, Button regisztracios_button, Button bejelentkez_button, Button regisztracio_button, Button fooldal_felvetel_button, Button fooldal_kategoria_button, Button fooldal_statisztika_button, Button nyugtas_kiadas_button,
                     LinearLayout nincs_bejelentkezve_layout, LinearLayout bejelentkezes_layout, LinearLayout regisztracio_layout, LinearLayout fooldal_layout, LinearLayout nyugtas_kiadas_layout,
                     EditText regisztracio_felhasznalonev_editText, EditText regisztracio_jelszo_editText, EditText regisztracio_email_editText, EditText bejelentkezes_felhasznalonev_editText, EditText bejelentkezes_jelszo_editText, EditText regisztracio_egyenleg_editText,
                     TextView regisztracio_felhasznalonev_text, TextView regisztracio_email_text, TextView regisztracio_jelszo_text, TextView bejelentkezes_felhasznalonev_text, TextView bejelentkezes_jelszo_text, TextView regisztracio_egyenleg_text,
                     FrameLayout nyugtas_kiadas_frame){
        this.context = context;
        this.urlKezelo = urlKezelo;
        this.bejelentkezes_button = bejelentkezes_button;
        this.regisztracios_button = regisztracios_button;
        this.bejelentkez_button = bejelentkez_button;
        this.regisztracio_button = regisztracio_button;
        this.fooldal_felvetel_button = fooldal_felvetel_button;
        this.fooldal_kategoria_button = fooldal_kategoria_button;
        this.fooldal_statisztika_button = fooldal_statisztika_button;
        this.nyugtas_kiadas_button = nyugtas_kiadas_button;

        this.nincs_bejelentkezve_layout = nincs_bejelentkezve_layout;
        this.bejelentkezes_layout = bejelentkezes_layout;
        this.regisztracio_layout = regisztracio_layout;
        this.fooldal_layout = fooldal_layout;
        this.nyugtas_kiadas_layout = nyugtas_kiadas_layout;

        this.regisztracio_felhasznalonev_editText = regisztracio_felhasznalonev_editText;
        this.regisztracio_jelszo_editText = regisztracio_jelszo_editText;
        this.regisztracio_email_editText = regisztracio_email_editText;
        this.regisztracio_egyenleg_editText = regisztracio_egyenleg_editText;
        this.bejelentkezes_felhasznalonev_editText = bejelentkezes_felhasznalonev_editText;
        this.bejelentkezes_jelszo_editText = bejelentkezes_jelszo_editText;

        this.regisztracio_felhasznalonev_text = regisztracio_felhasznalonev_text;
        this.regisztracio_email_text = regisztracio_email_text;
        this.regisztracio_jelszo_text = regisztracio_jelszo_text;
        this.regisztracio_egyenleg_text = regisztracio_egyenleg_text;
        this.bejelentkezes_felhasznalonev_text = bejelentkezes_felhasznalonev_text;
        this.bejelentkezes_jelszo_text = bejelentkezes_jelszo_text;

        this.nyugtas_kiadas_frame = nyugtas_kiadas_frame;

        bejelentkezes_layout.setVisibility(View.INVISIBLE);
        regisztracio_layout.setVisibility(View.INVISIBLE);
        fooldal_layout.setVisibility(View.INVISIBLE);
        nyugtas_kiadas_layout.setVisibility(View.INVISIBLE);



    }

    private Uri createUri(){
        File imageFile = new File(context.getApplicationContext().getFilesDir(), "camera_photo.jpg");
        return FileProvider.getUriForFile(
                context.getApplicationContext(),
                "com.example.frontend.fileProvider",
                imageFile
        );


    }
    public void loop(){

        if (nincs_bejelentkezve_layout.getVisibility() == View.VISIBLE)
            nincs_bejelentkezve();

        if (bejelentkezes_layout.getVisibility() == View.VISIBLE)
            bejelentkezes();

        if (regisztracio_layout.getVisibility() == View.VISIBLE)
            regisztracio();

        if (fooldal_layout.getVisibility() == View.VISIBLE)
            fooldal();

        if (nyugtas_kiadas_layout.getVisibility() == View.VISIBLE)
            nyugtas_kiadas();


    }

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            Toast.makeText(context, "Kamera üzemkész", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            // no camera on this device
            Toast.makeText(context, "Kamera nem található", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void nyugtas_kiadas() {
        nyugtas_kiadas_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkCameraHardware(context);
                camera = getCameraInstance();
                cameraPreview = new CameraPreview(context, camera);
                nyugtas_kiadas_frame.addView(cameraPreview);
            }
        });
    }

    public void regisztracio_hibas_adatok(String hiba){
        int nulla = 0;
        int egy = 0;

        Log.d("Vissza", hiba);

        for (int i=0; i<hiba.length();i++){
            if (hiba.charAt(i) == '0') {nulla++;
                if (nulla + egy == 1)
                    regisztracio_felhasznalonev_text.setTextColor(Color.BLACK);
                if (nulla + egy == 2)
                    regisztracio_email_text.setTextColor(Color.BLACK);
                if (nulla + egy == 3)
                    regisztracio_jelszo_text.setTextColor(Color.BLACK);}
            if (hiba.charAt(i) == '1'){

                egy++;
                if (nulla + egy == 1)
                {regisztracio_felhasznalonev_text.setTextColor(Color.RED);}
                if (nulla + egy == 2){
                    regisztracio_email_text.setTextColor(Color.RED);}
                if (nulla + egy == 3){
                    regisztracio_jelszo_text.setTextColor(Color.RED);}

                }

            }

        Toast.makeText(context, "Sikertelen regisztráció", Toast.LENGTH_SHORT).show();
        }

    public void sikeres_regisztracio() {
        nincs_bejelentkezve_layout.setVisibility(View.VISIBLE);
        regisztracio_layout.setVisibility(View.INVISIBLE);
        Toast.makeText(context, "Sikeres regisztráció", Toast.LENGTH_SHORT).show();
    }

    private void regisztracio() {

        regisztracio_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String adat = urlKezelo.reg(regisztracio_felhasznalonev_editText.getText().toString(),
                        regisztracio_email_editText.getText().toString(),
                        regisztracio_jelszo_editText.getText().toString(),
                        regisztracio_egyenleg_editText.getText().toString());
                Log.d("ADAT", adat);
                if (adat.contains("1"))
                    regisztracio_hibas_adatok(adat);
                else
                    sikeres_regisztracio();


            }
        });

    }

    public void sikeres_bejelentkezes(){
        fooldal_layout.setVisibility(View.VISIBLE);
        bejelentkezes_layout.setVisibility(View.INVISIBLE);


        /*try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("felhasznalo.txt"));
            writer.write(bejelentkezes_felhasznalonev_editText.getText().toString());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Log.d("bejelentkez", "siker");

        Toast.makeText(context, "Sikeres bejelentkezés", Toast.LENGTH_SHORT).show();
    }

    public void bejelentkezes_hibas_adatok(){
        bejelentkezes_felhasznalonev_text.setTextColor(Color.RED);
        bejelentkezes_jelszo_text.setTextColor(Color.RED);

        Log.d("Bejelentkezés", "HIBA");

        Toast.makeText(context, "Sikertelen bejelentkezés", Toast.LENGTH_SHORT).show();
    }

    private void bejelentkezes() {
        bejelentkez_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String adat = urlKezelo.be(bejelentkezes_felhasznalonev_editText.getText().toString(),
                                        bejelentkezes_jelszo_editText.getText().toString());
                if (adat.contains("1"))
                    bejelentkezes_hibas_adatok();
                else
                    sikeres_bejelentkezes();
            }
        });

    }

    private void fooldal(){
        fooldal_felvetel_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nyugtas_kiadas_layout.setVisibility(View.VISIBLE);
                fooldal_layout.setVisibility(View.INVISIBLE);

            }
        });

        fooldal_kategoria_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        fooldal_statisztika_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }

    public void nincs_bejelentkezve(){

        bejelentkezes_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nincs_bejelentkezve_layout.setVisibility(View.INVISIBLE);
                bejelentkezes_layout.setVisibility(View.VISIBLE);

            }
        });

        regisztracios_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nincs_bejelentkezve_layout.setVisibility(View.INVISIBLE);
                regisztracio_layout.setVisibility(View.VISIBLE);
        }
        });

    }


    @Override
    public void run() {
        super.run();
        while (futas) {
            loop();

        }
    }




}

