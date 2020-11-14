package com.les.topsession;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.les.topsession.CourseAdapter.Course;
import com.les.topsession.CourseAdapter.CourseAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class CourseActivity extends AppCompatActivity {
    TextView data_text;
    RecyclerView recyclerView;
    Document document;
    CourseAdapter courseAdapter;
    ArrayList<Course> arrayList = new ArrayList<>();
    double numBuy = 0.0;
    double numSell = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        init();
    }

    public void init() {
        data_text = findViewById(R.id.data_text);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String date = dateFormat.format(new Date());
        data_text.setText(date);

        recyclerView = findViewById(R.id.courseRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getCourse();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        courseAdapter = new CourseAdapter(this, arrayList);
        recyclerView.setAdapter(courseAdapter);
    }

    public void getCourse() {
        try {
            document = Jsoup.connect("https://cbr.ru/currency_base/daily/").get();
            Elements Teg = document.getElementsByTag("tbody");
            Element tr = Teg.get(0);

            for (int i = 1; i < tr.childrenSize(); i++) {
                Course course = new Course();
                Random random = new Random();

                course.setName(tr.children().get(i).child(1).text());
                course.setLastName(tr.children().get(i).child(3).text());

                String textBuy = course.setTextBuy(tr.children().get(i).child(4).text().replace(",", ".").substring(0, 5));
                numBuy = Double.parseDouble(textBuy) + (Double.parseDouble(textBuy) * 0.5);
                if (numBuy > 50) {
                    course.setArUp(R.drawable.arrow_up);
                } else {
                    course.setArUp(R.drawable.arrow_back);
                }
                course.setTextBuy(String.valueOf(numBuy).substring(0, 5));

                String textSell = course.setTextSell(tr.children().get(i).child(4).text().replace(",", ".").substring(0, 5));
                numSell = Double.parseDouble(textSell) + (Double.parseDouble(textSell) * 0.5) - random.nextInt(50) + 1;
                if (numSell <= 50.0) {
                    course.setArBack(R.drawable.arrow_back);
                } else {
                    course.setArBack(R.drawable.arrow_up);
                }
                course.setTextSell(String.valueOf(numSell).substring(0, 4));

                arrayList.add(course);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    courseAdapter.notifyDataSetChanged();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}