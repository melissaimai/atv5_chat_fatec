package br.gov.sp.fatec.fatec_ipi_noite_chat_com_firebase;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.gov.sp.fatec.fatec_ipi_noite_chat_com_firebase.util.ElementEnabler;

abstract class ChatActivity extends AppCompatActivity {

    private RecyclerView mensagensRecyclerView;
    private ChatAdapter adapter;
    private List<Mensagem> mensagens;
    private EditText mensagemEditText;
    private FirebaseUser firebaseUser;
    private CollectionReference mensagensReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mensagensRecyclerView = findViewById(R.id.mensagensRecyclerView);
        mensagens = new ArrayList<>();
        adapter = new ChatAdapter(mensagens, this);
        mensagensRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mensagensRecyclerView.setLayoutManager(linearLayoutManager);
        mensagemEditText = findViewById(R.id.mensagemEditText);
        Button sendButton = findViewById(R.id.sendButton);
        ElementEnabler.textViews(sendButton, mensagemEditText);
    }

    private void getRemoteMsgs() {
        mensagensReference.addSnapshotListener((snapshots, firebaseException) -> {
            mensagens.clear();
            for (DocumentSnapshot doc : snapshots.getDocuments()) {
                Mensagem msg = doc.toObject(Mensagem.class);
                mensagens.add(msg);
            }
            Collections.sort(mensagens);
            adapter.notifyDataSetChanged();
        });
    }

    private void setupFirebase() {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mensagensReference = FirebaseFirestore.getInstance().collection(getSala());
        getRemoteMsgs();
    }

    private void scrollToBottom() {
        mensagensRecyclerView.smoothScrollToPosition(adapter.getItemCount());
    }

    private void scrollToBottomDelayed() {
        new Handler().postDelayed(this::scrollToBottom, 600);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupFirebase();
        scrollToBottomDelayed();
    }

    public void enviarMensagem(View v) {
        String texto = mensagemEditText.getText().toString();
        Mensagem mensagem = new Mensagem(firebaseUser.getEmail(), new Date(), texto);
        mensagensReference.add(mensagem);
        mensagemEditText.getText().clear();
        esconderTeclado(v);
        scrollToBottom();
    }

    private void esconderTeclado(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    abstract String getSala();
}