package br.gov.sp.fatec.fatec_ipi_noite_chat_com_firebase.util;

import android.text.TextWatcher;

/**
 * Implementa TextWatcher como uma interface funcional
 */
public interface FunctionalTextWatcher extends TextWatcher {

    @Override
    default void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    default void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}
