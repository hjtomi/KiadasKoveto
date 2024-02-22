package com.example.frontend;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private UrlKezelo urlKezelo;
    private static final int REQUEST_CAMERA_PERMISSION_CODE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private File photoFile;
    Button bejelentkezes_button, regisztracios_button, bejelentkez_button, regisztracio_button, fooldal_felvetel_button, fooldal_kategoria_button, fooldal_statisztika_button, nyugtas_kiadas_button, kep_button, kategoria_kuldes_button, home;
    Button kategoria_1, kategoria_2, kategoria_3, kategoria_4, kategoria_5, kategoria_6, kategoria_7, kategoria_8;
    Button manualis_kiadas_button, manualis_tovabb, manualis_mentes, manualis_kuldes, kategoria_hozzaadasa_button;
    LinearLayout nincs_bejelentkezve_layout, bejelentkezes_layout, regisztracio_layout, fooldal_layout, felvetel_valaszto_layout, nyugtas_kiadas_layout, kategoria_layout, manualis_bolt_layout, manualis_layout, var_layout, kategoria_hozzaadasa_layout;
    EditText regisztracio_felhasznalonev_editText, regisztracio_jelszo_editText, regisztracio_email_editText, bejelentkezes_felhasznalonev_editText, bejelentkezes_jelszo_editText, regisztracio_egyenleg_editText, nyugtas_bolt_editText, kategoria_egyeb_editText, manualis_bolt_editText;
    EditText manualis_nev_editText, manualis_ar_editText, manualis_kategoria_editText, manualis_fiok_editText, nyugtas_fiok_editText, kategoria_hozzaadasa_editText, ny_termekar_editText, ny_termeknev_editText;
    TextView regisztracio_felhasznalonev_text, regisztracio_email_text, regisztracio_jelszo_text, bejelentkezes_felhasznalonev_text, bejelentkezes_jelszo_text, regisztracio_egyenleg_text, nyugtas_bolt_text, kategoria_text, manualis_bolt_text;
    TextView manualis_nev_text, manualis_ar_text, manualis_datum_text, manualis_kategoria_text, manualis_fiok_text, var_text, nyugtas_fiok_text, kategoria_hozzaadasa_text;
    DatePicker manualis_datum_pick;
    ImageView kep;

    Intent i;
    Uri mUri;
    String filename;
    File folder = null;
    final static int cameraData = 0;
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
        kategoria_kuldes_button = findViewById(R.id.kategora_kuldes_button);
        home = findViewById(R.id.home);
        kategoria_hozzaadasa_button = findViewById(R.id.kategoria_hozzaadasa_button);

        kategoria_1 = findViewById(R.id.kategora_1_button);
        kategoria_2 = findViewById(R.id.kategora_2_button);
        kategoria_3 = findViewById(R.id.kategora_3_button);
        kategoria_4 = findViewById(R.id.kategora_4_button);
        kategoria_5 = findViewById(R.id.kategora_5_button);
        kategoria_6 = findViewById(R.id.kategora_6_button);
        kategoria_7 = findViewById(R.id.kategora_7_button);
        kategoria_8 = findViewById(R.id.kategora_8_button);

        manualis_kiadas_button = findViewById(R.id.manualis_kiadas_button);
        manualis_kuldes = findViewById(R.id.manualis_kuldes);
        manualis_mentes = findViewById(R.id.manualis_mentes);
        manualis_tovabb = findViewById(R.id.manualis_tovabb);

        nincs_bejelentkezve_layout = findViewById(R.id.nincs_bejelentkezve_layout);
        bejelentkezes_layout = findViewById(R.id.bejelentkezes_layout);
        regisztracio_layout = findViewById(R.id.regisztracio_layout);
        fooldal_layout = findViewById(R.id.fooldal_layout);
        felvetel_valaszto_layout = findViewById(R.id.felvetel_valaszto_layout);
        nyugtas_kiadas_layout = findViewById(R.id.nyugtas_kiadas_layout);
        kategoria_layout = findViewById(R.id.kategoria_layout);
        manualis_layout = findViewById(R.id.manualis_layout);
        manualis_bolt_layout = findViewById(R.id.manualis_bolt_layout);
        var_layout = findViewById(R.id.var_layout);
        kategoria_hozzaadasa_layout = findViewById(R.id.kategoria_hozzaadasa_layout);

        regisztracio_felhasznalonev_editText = findViewById(R.id.regisztracio_felhasznalonev_editText);
        regisztracio_jelszo_editText = findViewById(R.id.regisztracio_jelszo_editText);
        regisztracio_email_editText = findViewById(R.id.regisztracio_email_editText);
        regisztracio_egyenleg_editText = findViewById(R.id.regisztracio_egyenleg_editText);
        bejelentkezes_felhasznalonev_editText = findViewById(R.id.bejelentkez_felhasznalonev_editText);
        bejelentkezes_jelszo_editText = findViewById(R.id.bejelentkez_jelszo_editText);
        nyugtas_bolt_editText = findViewById(R.id.nyugtas_bolt_editText);
        kategoria_egyeb_editText = findViewById(R.id.kategoria_egyeb_editText);
        manualis_nev_editText = findViewById(R.id.manualis_nev_editText);
        manualis_ar_editText = findViewById(R.id.manualis_ar_editText);
        manualis_bolt_editText = findViewById(R.id.manualis_bolt_editText);
        manualis_kategoria_editText = findViewById(R.id.manualis_kategoria_editText);
        manualis_fiok_editText = findViewById(R.id.manualis_fiok_editText);
        nyugtas_fiok_editText = findViewById(R.id.nyugtas_fiok_editText);
        kategoria_hozzaadasa_editText = findViewById(R.id.kategoria_hozzaadasa_editText);
        ny_termekar_editText = findViewById(R.id.ny_termekar_editText);
        ny_termeknev_editText = findViewById(R.id.ny_termeknev_editText);

        regisztracio_felhasznalonev_text = findViewById(R.id.regisztracio_felhasznalonev_text);
        regisztracio_email_text = findViewById(R.id.regisztracio_email_text);
        regisztracio_jelszo_text = findViewById(R.id.regisztracio_jelszo_text);
        regisztracio_egyenleg_text = findViewById(R.id.regisztracio_egyenleg_text);
        bejelentkezes_felhasznalonev_text = findViewById(R.id.bejelentkezes_felhasznalonev_text);
        bejelentkezes_jelszo_text = findViewById(R.id.bejelentkezes_jelszo_text);
        nyugtas_bolt_text = findViewById(R.id.nyugtas_bolt_text);
        kategoria_text = findViewById(R.id.kategoria_text);
        manualis_ar_text = findViewById(R.id.manualis_ar_text);
        manualis_bolt_text = findViewById(R.id.manualis_bolt_text);
        manualis_datum_text = findViewById(R.id.manualis_bolt_text); //a manualis datum text ki lett véve, így nincs HIBA
        manualis_nev_text = findViewById(R.id.manualis_nev_text);
        manualis_kategoria_text = findViewById(R.id.manualis_kategoria_text);
        manualis_fiok_text = findViewById(R.id.manualis_fiok_text);
        var_text = findViewById(R.id.var_text);
        nyugtas_fiok_text = findViewById(R.id.nyugtas_fiok_text);
        kategoria_hozzaadasa_text = findViewById(R.id.kategoria_hozzaadasa_text);

        kep = findViewById(R.id.kep);
        manualis_datum_pick = findViewById(R.id.manualis_datum_pick);

        // Register the onClick listener with the implementation above

        urlKezelo = new UrlKezelo(this, bejelentkezes_button, regisztracios_button, bejelentkez_button, regisztracio_button, fooldal_felvetel_button, fooldal_kategoria_button, fooldal_statisztika_button, nyugtas_kiadas_button, kep_button, kategoria_kuldes_button, home,
                kategoria_1, kategoria_2, kategoria_3, kategoria_4, kategoria_5, kategoria_6, kategoria_7, kategoria_8,
                manualis_kiadas_button, manualis_kuldes, manualis_mentes, manualis_tovabb, kategoria_hozzaadasa_button,
                nincs_bejelentkezve_layout, bejelentkezes_layout, regisztracio_layout, fooldal_layout, felvetel_valaszto_layout, nyugtas_kiadas_layout, kategoria_layout, manualis_layout, manualis_bolt_layout, var_layout, kategoria_hozzaadasa_layout,
                regisztracio_felhasznalonev_editText, regisztracio_jelszo_editText, regisztracio_email_editText, bejelentkezes_felhasznalonev_editText, bejelentkezes_jelszo_editText, regisztracio_egyenleg_editText, nyugtas_bolt_editText, kategoria_egyeb_editText,
                manualis_bolt_editText, manualis_ar_editText, manualis_nev_editText, manualis_kategoria_editText, manualis_fiok_editText, nyugtas_fiok_editText, kategoria_hozzaadasa_editText, ny_termekar_editText, ny_termeknev_editText,
                regisztracio_felhasznalonev_text, regisztracio_email_text, regisztracio_jelszo_text, bejelentkezes_felhasznalonev_text, bejelentkezes_jelszo_text, regisztracio_egyenleg_text, nyugtas_bolt_text, kategoria_text,
                manualis_ar_text, manualis_bolt_text, manualis_datum_text, manualis_nev_text, manualis_kategoria_text, manualis_fiok_text, var_text, nyugtas_fiok_text, kategoria_hozzaadasa_text,
                kep, manualis_datum_pick);

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

    /*
    public void foto(View view){
        felvetel_valaszto_layout.setVisibility(View.INVISIBLE);
        nyugtas_kiadas_layout.setVisibility(View.VISIBLE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA_PERMISSION_CODE);
            return;
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        String filename = Environment.getExternalStorageDirectory().getPath() + "/test/testfile.jpg";
        Uri imageUri = Uri.fromFile(new File(filename));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
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

    */

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){

            Log.d("ok", "ok");
            Bundle extras = data.getExtras();

            Bitmap bmp = (Bitmap) extras.get("data");
        }
    }*/


    //https://developer.android.com/media/camera/camerax/preview
    //https://github.com/codepath/android_guides/wiki/Accessing-the-Camera-and-Stored-Media

    public void foto(View view){
        nyugtas_kiadas_layout.setVisibility(View.VISIBLE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA_PERMISSION_CODE);
            return;
        }

        photoFile = getPhotoFileUri("photo.jpg");
        Uri imageUri = FileProvider.getUriForFile(this, "com.example.frontend.fileprovider", photoFile);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

    }

    private File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photo");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d("photo", "failed to create directory");
        }

        // Return the file target for the photo based on filename
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);

        return file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //Bundle extras = data.getExtras();

            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            Bitmap imageBitmap = BitmapFactory.decodeFile(photoFile.getPath());
            urlKezelo.frontend.kep_bitmap(imageBitmap);

            kep.setImageBitmap(imageBitmap);

        }

    }

}
