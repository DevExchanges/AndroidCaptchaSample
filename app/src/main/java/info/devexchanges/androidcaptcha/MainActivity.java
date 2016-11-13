package info.devexchanges.androidcaptcha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextCaptcha textCaptcha = new TextCaptcha(600, 150, 4, TextCaptcha.TextOptions.LETTERS_ONLY);
        final MathCaptcha mathCaptcha = new MathCaptcha(600, 150, MathCaptcha.MathOptions.PLUS_MINUS);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        ImageView imageView1 = (ImageView) findViewById(R.id.image_2);
        imageView.setImageBitmap(textCaptcha.getImage());
        imageView1.setImageBitmap(mathCaptcha.getImage());

        View btnCheck = findViewById(R.id.btn_check);
        final AppCompatEditText edtTextCaptcha = (AppCompatEditText) findViewById(R.id.edt_text_image);
        final AppCompatEditText edtMathCaptcha = (AppCompatEditText) findViewById(R.id.edt_math_image);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtMathCaptcha.setError(null);
                edtTextCaptcha.setError(null);
                int numberOfCaptchaFalse = 0;

                //checking text captcha
                if (!textCaptcha.checkAnswer(edtTextCaptcha.getText().toString().trim())) {
                    edtTextCaptcha.setError("Captcha is not match");
                    numberOfCaptchaFalse++;
                }

                //checking math captcha
                if (!mathCaptcha.checkAnswer(edtMathCaptcha.getText().toString().trim())) {
                    edtMathCaptcha.setError("Captcha not match");
                    numberOfCaptchaFalse++;
                }

                if (numberOfCaptchaFalse == 0) {
                    Toast.makeText(MainActivity.this, "All captcha texts match!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
