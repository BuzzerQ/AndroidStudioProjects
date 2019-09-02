package com.example.learnandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText etNama, etAlamat;
    Spinner spProdi;
    CheckBox cbValid;
    RadioGroup rgLulusan;
    Button btSimpan, btnNotifikasi, btnToast, btnHapus, btnExit, btnSnackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //definisi dan sambungkan objek
        etNama = (EditText) findViewById(R.id.et_nama);
        etAlamat = (EditText) findViewById(R.id.et_alamat);
        spProdi = (Spinner) findViewById(R.id.sp_prodi);
        rgLulusan = (RadioGroup) findViewById(R.id.rg_lulusan);
        cbValid = (CheckBox) findViewById(R.id.cb_valid);
        btSimpan = (Button) findViewById(R.id.bt_simpan);
        btnHapus = (Button) findViewById(R.id.bt_hapus);
        btnNotifikasi = (Button) findViewById(R.id.bt_notifikasi);
        btnToast = (Button) findViewById(R.id.bt_toast);
        btnExit = (Button) findViewById(R.id.bt_exit);
        btnSnackBar = (Button) findViewById(R.id.bt_snack_bar);

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setTitle("Hapus Data Ini")
                        .setMessage("Menghapus " + etNama.getText().toString() + "???")
                        .setCancelable(true)
                        .setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(
                                                getApplicationContext(),
                                                "OK Ditekan",
                                                Toast.LENGTH_SHORT
                                        ).show();
                                    }
                                }
                        )
                        .setNegativeButton(
                                "Batal",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(
                                                getApplicationContext(),
                                                "Di batalkan",
                                                Toast.LENGTH_SHORT
                                        ).show();
                                    }
                                }
                        )

                        .show();
            }
        });

        //event onclik pada button toast
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(
                        getApplicationContext(),
                        "Hei " + etNama.getText().toString(),
                        Toast.LENGTH_SHORT)
                        .show();

                int selectedid = rgLulusan.getCheckedRadioButtonId();
                final RadioButton rbLulus = (RadioButton) findViewById(selectedid);

                Log.d("test",
                        etNama.getText().toString() + " "
                        + etAlamat.getText().toString() + " "
                        +spProdi.getSelectedItem().toString() + " "
                        +rbLulus.getText().toString());
            }
        });

        btnNotifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //notifikasi
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getApplicationContext(), "notify_001");
                Intent ii = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, ii, 0);

                NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                bigText.setBigContentTitle("Punya lu");
                bigText.setSummaryText("Cek Notifikasi Lu Cuk");

                mBuilder.setContentIntent(pendingIntent);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
                mBuilder.setContentTitle("Punya lu");
                mBuilder.setContentText("Cek Notifikasi Lu Cuk");
                mBuilder.setPriority(Notification.PRIORITY_MAX);
                mBuilder.setStyle(bigText);
                mBuilder.setDefaults(Notification.DEFAULT_SOUND); //suara
                mBuilder.setVibrate(new long[] {1000, 1000, 1000, 1000, 1000, 1000}); //getar

                NotificationManager mNotificationManager =
                        (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("notify_001",
                            "Channel human readable title",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    mNotificationManager.createNotificationChannel(channel);
                }

                mNotificationManager.notify(0, mBuilder.build());
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSnackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = findViewById(R.id.main_layout);
                String pesan = "Snackbar activated";
                int durasi = Snackbar.LENGTH_SHORT;
                Snackbar.make(v,pesan,durasi).show();
            }
        });

    }

    //memanggil menu(memanggil menu)
    //alt + ins -> override menu -> onCreateOptionsMenu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    //alt+ins -> override menu -> onOptionsItemSelected

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
