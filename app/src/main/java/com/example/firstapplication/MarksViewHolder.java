package com.example.firstapplication;

//import android.view.View;
//import android.widget.RadioGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class MarksViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener{
//    RadioGroup mRadioGroup;
//    public MarksViewHolder(@NonNull View itemView) {
//        super(itemView);
//        RadioGroup mRadioGroup = itemView.findViewById(R.id.group);
//        mRadioGroup.check(R.id.two);
//    }
//
//    @Override
//    public void onCheckedChanged(RadioGroup radioGroup, int i) {
//        MarksModel model = (MarksModel) radioGroup.getTag();
//        if (radioGroup.getCheckedRadioButtonId() == R.id.two) {
//            model.setMark(2);
//        }
//        if (radioGroup.getCheckedRadioButtonId() == R.id.three) {
//            model.setMark(3);
//        }
//        if (radioGroup.getCheckedRadioButtonId() == R.id.four) {
//            model.setMark(4);
//        }
//        if (radioGroup.getCheckedRadioButtonId() == R.id.five) {
//            model.setMark(5);
//        }
//
//    }
//}
