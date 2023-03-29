package com.example.firstapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArrayAdapter extends
        RecyclerView.Adapter<ArrayAdapter.MarksViewHolder>{

    private List<MarksModel> marksList;
    private LayoutInflater mPompka;

    public void updateMark(int position, int mark) {
        marksList.get(position).setMark(mark);
        notifyItemChanged(position);
    }

    public class MarksViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener{
        RadioGroup mRadioGroup;
        TextView mtext;
        public MarksViewHolder(@NonNull View itemView) {
            super(itemView);
            mRadioGroup = itemView.findViewById(R.id.group);
            mRadioGroup.check(R.id.two);
            mtext = itemView.findViewById(R.id.row);
        }

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            MarksModel model = (MarksModel) radioGroup.getTag();
            if (radioGroup.getCheckedRadioButtonId() == R.id.two) {
                model.setMark(2);
            }
            if (radioGroup.getCheckedRadioButtonId() == R.id.three) {
                model.setMark(3);
            }
            if (radioGroup.getCheckedRadioButtonId() == R.id.four) {
                model.setMark(4);
            }
            if (radioGroup.getCheckedRadioButtonId() == R.id.five) {
                model.setMark(5);
            }

        }
    }


    public ArrayAdapter(Activity context, List<MarksModel> list){
        mPompka = context.getLayoutInflater();
        this.marksList=list;
    }
    @NonNull
    @Override
    public MarksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = mPompka.inflate(R.layout.row, null);
        return new MarksViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MarksViewHolder holder, int position) {
        holder.mRadioGroup.setOnCheckedChangeListener(holder);
        holder.mRadioGroup.setTag(marksList.get(position));
        holder.mtext.setText(marksList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return marksList.size();
    }
}

