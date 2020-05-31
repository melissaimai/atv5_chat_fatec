package br.gov.sp.fatec.fatec_ipi_noite_chat_com_firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {
    private List<Mensagem> mensagens;
    private Context context;

    ChatAdapter(List<Mensagem> mensagens, Context context) {
        this.mensagens = mensagens;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Mensagem m = mensagens.get(position);
        holder.mensagemTextView.setText(m.getTexto());
        holder.dataNomeTextView.setText(context.getString(R.string.mensagem, SimpleDateFormat.getTimeInstance().format(m.getDate()), m.getUsuario()));
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View raiz = inflater.inflate(R.layout.list_item, parent, false);
        return new ChatViewHolder(raiz);
    }

    @Override
    public int getItemCount() {
        return this.mensagens.size();
    }
}