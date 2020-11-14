package com.les.topsession;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.les.topsession.BranchesAdapter.Branch;
import com.les.topsession.BranchesAdapter.BranchAdapter;

import java.util.ArrayList;

public class BranchesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    BranchAdapter branchAdapter;
    ArrayList<Branch> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches);

        recyclerView = findViewById(R.id.branchRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        branchesList();

        branchAdapter = new BranchAdapter(this, arrayList);
        recyclerView.setAdapter(branchAdapter);
    }

    public void branchesList() {
        String address = "Москва, ул.Вавилова, д.7";
        String bank = "Банкомат";
        String active = "Работает";
        String clock1 = "Часы работы 00:00-00:00";

        String branch = "Отделение";
        String noActive = "Не работает";
        String clock2 = "Часы работы 09:00-20:00";

    arrayList.add(new Branch(address, bank, active,clock1));
    arrayList.add(new Branch(address, branch, noActive,clock2));
    arrayList.add(new Branch(address, bank, active,clock1));
    arrayList.add(new Branch(address, bank, active,clock1));
    arrayList.add(new Branch(address, branch, noActive,clock2));
    arrayList.add(new Branch(address, bank, active,clock1));
    arrayList.add(new Branch(address, bank, active,clock1));
    arrayList.add(new Branch(address, branch, noActive,clock2));
    arrayList.add(new Branch(address, bank, active,clock1));
    }
}