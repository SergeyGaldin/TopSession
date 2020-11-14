package com.les.topsession;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.les.topsession.SharedPreferences.TokenSave;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView data_text, text_eur, text_usd;
    Document doc;
    Runnable runnable;
    String dolText = "";
    String eurText = "";
    EditText login;
    EditText password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        checkSes();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String tvData = dateFormat.format(new Date());
        data_text.setText(tvData);
    }

    public void onClickBranch(View view) {
        startActivity(new Intent(MainActivity.this, BranchesActivity.class));
    }

    public void onClickCourse(View view) {
        startActivity(new Intent(MainActivity.this, CourseActivity.class));
    }

    public void init() {
        data_text = findViewById(R.id.data_text);
        text_eur = findViewById(R.id.parsEUR);
        text_usd = findViewById(R.id.parsUSD);
        mAuth = FirebaseAuth.getInstance();
        runnable = new Runnable() {
            @Override
            public void run() {
                courseParsing();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void courseParsing() {
        try {
            doc = Jsoup.connect("http://www.cbr.ru/scripts/XML_daily.asp?").get();
            Elements teg = doc.getElementsByTag("Value");
            Element myTegDol = teg.get(10);
            Element myTegEuro = teg.get(11);
            dolText = myTegDol.text().substring(0, 5);
            eurText = myTegEuro.text().substring(0, 5);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    text_usd.setText(dolText);
                    text_eur.setText(eurText);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickHome(View v) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.forma_authorization, (GridLayout) findViewById(R.id.gridAl));
        alertDialog.setView(view).setCancelable(false);
        AlertDialog dialog = alertDialog.create();

        login = view.findViewById(R.id.login);
        password = view.findViewById(R.id.password);

        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });

        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void SignIn() {
        if (!TextUtils.isEmpty(login.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())) {
            mAuth.signInWithEmailAndPassword(login.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Вошёл", Toast.LENGTH_SHORT).show();
                                TokenSave.saveSharedSetting(MainActivity.this, "token", "true");
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Аккаунт не найден", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(MainActivity.this, "Проверте ввод логина и пароля", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkSes() {
        Boolean check = Boolean.valueOf(TokenSave.readSharedSetting(MainActivity.this, "token", "true"));
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        intent.putExtra("tokenSave", check);
        if (check) {
            startActivity(intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}