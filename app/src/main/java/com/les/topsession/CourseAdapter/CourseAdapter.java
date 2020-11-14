package com.les.topsession.CourseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.les.topsession.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseAdapterVH> {
    LayoutInflater layoutInflater;
    List<Course> courseList;

    public CourseAdapter(Context context, List<Course> courseList) {
        this.layoutInflater = layoutInflater.from(context);
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseAdapterVH(layoutInflater.inflate(R.layout.course_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapterVH holder, int position) {
        Course course = courseList.get(position);

        holder.name.setText(course.getName());
        holder.lastName.setText(course.getLastName());
        holder.textBuy.setText(course.getTextBuy());
        holder.textSell.setText(course.getTextSell());
        holder.arUp.setImageResource(course.getArUp());
        holder.arBack.setImageResource(course.getArBack());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class CourseAdapterVH extends RecyclerView.ViewHolder {
        TextView name, lastName, textBuy, textSell;
        ImageView arUp, arBack;

        public CourseAdapterVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_course);
            lastName = itemView.findViewById(R.id.nameLast_course);
            textBuy = itemView.findViewById(R.id.tvBuy);
            textSell = itemView.findViewById(R.id.tvSell);
            arUp = itemView.findViewById(R.id.arrowBuy);
            arBack = itemView.findViewById(R.id.arrowSell);
        }
    }
}
