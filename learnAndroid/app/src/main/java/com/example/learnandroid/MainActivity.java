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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText etNama, etAlamat;
    Spinner spProdi;
    CheckBox cbValid;
    RadioGroup rgLulusan;
    RadioButton rbLulus;
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

        final RadioButton rbSMA = (RadioButton) findViewById(R.id.rb_SMA);
        rbSMA.setChecked(true);


        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama, alamat, prodi, lulusan;
                boolean valid;

                nama = etNama.getText().toString();
                alamat = etAlamat.getText().toString();
                prodi = spProdi.getSelectedItem().toString();

                int selectedid = rgLulusan.getCheckedRadioButtonId();
                final RadioButton rbLulus = (RadioButton) findViewById(selectedid);

                lulusan = rbLulus.getText().toString();

                valid = cbValid.isChecked();

                if(!valid){
                    Toast.makeText(getApplicationContext(), "Lengkapi Formulir Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(nama.length() == 0 || alamat.length() == 0){
                    Toast.makeText(getApplicationContext(), "Lengkapi terlebih dahulu", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent i = new Intent(MainActivity.this, detailActivity.class);
                i.putExtra("i_nama", nama);
                i.putExtra("i_alamat", alamat);
                i.putExtra("i_prodi", prodi);
                i.putExtra("i_lulusan", lulusan);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(i);
            }
        });

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
                                                "Data Berhasil Dihapus",
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
                                                "Dibatalkan",
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
                        "Hei " + etNama.getText().toString()+ " dari " + etAlamat.getText().toString(),
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
                mBuilder.setContentTitle("learnAndroid");
                mBuilder.setContentText("Munculkan notifikasi!");
                mBuilder.setPriority(Notification.PRIORITY_MAX);
                mBuilder.setStyle(bigText);
                mBuilder.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE); //suara
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
        //memanggil layout menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.mn_cari);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    //alt+ins -> override menu -> onOptionsItemSelected

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_notifikasi:
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
                return true;
            case R.id.mn_snack_bar:
                View v = findViewById(R.id.main_layout);
                String pesan = "Snackbar activated";
                int durasi = Snackbar.LENGTH_SHORT;
                Snackbar.make(v,pesan,durasi).show();
                return true;
            case R.id.mn_toast:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
