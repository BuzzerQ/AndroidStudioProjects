package com.example.learnandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class detailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final TextView tvNama = (TextView) findViewById(R.id.tv_nama);
        final TextView tvAlamat = (TextView) findViewById(R.id.tv_alamat);
        final TextView tvProdi = (TextView) findViewById(R.id.tv_prodi);
        final TextView tvLulusan = (TextView) findViewById(R.id.tv_lulusan);

        Intent i = getIntent();
        tvNama.setText(((Intent) i).getStringExtra("i_nama"));
        tvAlamat.setText(((Intent) i).getStringExtra("i_alamat"));
        tvProdi.setText(((Intent) i).getStringExtra("i_prodi"));
        tvLulusan.setText(((Intent) i).getStringExtra("i_lulusan"));


        getSupportActionBar().setTitle("Informasi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
