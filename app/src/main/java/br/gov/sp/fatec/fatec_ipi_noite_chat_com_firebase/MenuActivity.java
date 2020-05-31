package br.gov.sp.fatec.fatec_ipi_noite_chat_com_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void novidades(View view) {
        chat();
    }

    public void cinema(View view) {
        chat();
    }

    public void tecnologia(View view) {
        chat();
    }

    public void economia(View view) {
        chat();
    }

    private void chat() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }
}
