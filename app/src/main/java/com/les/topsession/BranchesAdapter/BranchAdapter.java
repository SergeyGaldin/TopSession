package com.les.topsession.BranchesAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.les.topsession.R;

import java.util.List;

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.BranchAdapterVh> {
    List<Branch> branchList;
    LayoutInflater layoutInflater;

    public BranchAdapter(Context context, List<Branch> branchList) {
        this.layoutInflater = layoutInflater.from(context);
        this.branchList = branchList;
    }

    @NonNull
    @Override
    public BranchAdapter.BranchAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BranchAdapterVh(layoutInflater.inflate(R.layout.branch_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BranchAdapter.BranchAdapterVh holder, int position) {
        Branch branch = branchList.get(position);

        holder.address.setText(branch.getAddress());
        holder.place.setText(branch.getPlace());
        holder.active.setText(branch.getActive());
        holder.clock.setText(branch.getClock());

        if (holder.active.getText().toString().equals("Не работает")){
            holder.active.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return branchList.size();
    }

    public class BranchAdapterVh extends RecyclerView.ViewHolder {
        TextView address, place, active, clock;

        public BranchAdapterVh(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.address);
            place = itemView.findViewById(R.id.place);
            active = itemView.findViewById(R.id.active);
            clock = itemView.findViewById(R.id.clock);
        }
    }
}
