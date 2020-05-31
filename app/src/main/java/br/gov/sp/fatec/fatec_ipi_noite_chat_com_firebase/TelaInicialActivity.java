package br.gov.sp.fatec.fatec_ipi_noite_chat_com_firebase;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TelaInicialActivity extends AppCompatActivity {

    private static final short SPLASH_SCREEN = 3000;

    private ImageView image;
    private TextView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tela__inicial);

        //Animations
        Animation topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        Animation bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.chat_imageView);
        logo = findViewById(R.id.chat_textView);
        TextView slogan = findViewById(R.id.fatec_textView);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(TelaInicialActivity.this, MainActivity.class);

            final Pair<View, String> logo_image = new Pair<>(image, "logo_image");
            final Pair<View, String> logo_text = new Pair<>(logo, "logo_text");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(TelaInicialActivity.this, logo_image, logo_text);
            startActivity(intent, options.toBundle());
        }, SPLASH_SCREEN);
    }
}
