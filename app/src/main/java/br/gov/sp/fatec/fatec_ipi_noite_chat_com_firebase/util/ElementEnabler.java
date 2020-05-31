package br.gov.sp.fatec.fatec_ipi_noite_chat_com_firebase.util;

import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Ativa e desativa um elemento a partir do estado de outros
 */
public class ElementEnabler {
    /**
     * Ativa um elemento se todos textos estiverem preenchidos e o desativa se um deles estiver vazio.
     * Inicialmente o elemento é desativado e será atualizado após a edição de um dos textos.
     *
     * @param element   elemento a ser ativado e desativado
     * @param textViews textos a serem observados
     */
    public static void textViews(View element, TextView... textViews) {

        element.setEnabled(false);

        HashMap<TextView, String> map = new HashMap<>(textViews.length);

        for (TextView text : textViews) {
            map.put(text, text.getText().toString());
            text.addTextChangedListener((FunctionalTextWatcher) editable -> {
                map.put(text, text.getText().toString());
                element.setEnabled(map.values().parallelStream().noneMatch(s -> s.trim().isEmpty()));
            });
        }
    }
}
