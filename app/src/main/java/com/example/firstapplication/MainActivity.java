package com.example.firstapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstapplication.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {
    private boolean isNameEmpty = true;
    private boolean isSurnameEmpty = true;
    private boolean isMarksEmpty = true;

    private float avr = 0;

    private ActivityResultLauncher<Intent> mActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

        EditText editTextName = findViewById(R.id.name);
        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 MainActivity.this.isNameEmpty = charSequence.toString().isEmpty();
                 changeVisibility();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    editTextName.setError(getString(R.string.nameError));
                }
            }
        });

        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    if(editTextName.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this, getString(R.string.nameError),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        EditText editTextSurname = findViewById(R.id.surname);
        editTextSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.this.isSurnameEmpty = charSequence.toString().isEmpty();
                changeVisibility();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    editTextSurname.setError(getString(R.string.surnameError));
                }
            }
        });

        editTextSurname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    if(editTextSurname.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this, getString(R.string.surnameError),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        EditText editTextMarks = findViewById(R.id.marks);
        editTextMarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    if(Integer.parseInt(charSequence.toString()) >= 5 && Integer.parseInt(charSequence.toString()) <= 15){
                        MainActivity.this.isMarksEmpty = false;
                    } else{
                        MainActivity.this.isMarksEmpty = true;
                    }
                } else{
                    MainActivity.this.isMarksEmpty = true;
                }
                changeVisibility();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().isEmpty()){
                    editTextMarks.setError(getString(R.string.emptyMarksError));
                } else if (Integer.parseInt(editable.toString()) < 5 || Integer.parseInt(editable.toString()) > 15){
                    editTextMarks.setError(getString(R.string.rangeMarksError));
                }
            }
        });

        editTextMarks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    if(editTextMarks.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this, getString(R.string.emptyMarksError),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSecondActivity();
            }
        });

        mActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(
                            ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent resultIntent = result.getData();
                            TextView resutlTextView =
                                    findViewById(R.id.avrtext);
                            resutlTextView.setVisibility(View.VISIBLE);
                            resutlTextView.setText("Twoja średnia to "+resultIntent.getStringExtra("avr"));
                            avr=Float.valueOf(resultIntent.getStringExtra("avr"));
                            if(avr >= 3 && avr != 0){
                                button.setText("Super!");
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(MainActivity.this, "Gratuluję :)",Toast.LENGTH_SHORT).show();
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                finishAffinity();
                                            }
                                        }, 3000);
                                    }
                                });
                            } else if(avr <= 3 && avr != 0){
                                button.setText("Tym razem nie poszło:(");
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(MainActivity.this, "Wysyłam podanie o zaliczenie warunkowe",Toast.LENGTH_SHORT).show();
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                finishAffinity();
                                            }
                                        }, 3000);
                                    }
                                });
                            }
                        }}});
    }


    private void startSecondActivity() {
        Intent intent = new Intent(MainActivity.this,MarksListActivity.class);
        EditText editTextMarks = findViewById(R.id.marks);
        intent.putExtra("marks",Integer.parseInt(editTextMarks.getText().toString()));
        mActivityResultLauncher.launch(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle packet = data.getExtras();
        TextView text = findViewById(R.id.avrtext);
        String s = String.valueOf(packet.getFloat("avr"));
        text.setText(s);
        text.setVisibility(View.VISIBLE);
    }


    private void changeVisibility(){
        Button button = findViewById(R.id.button);
        if(!isNameEmpty && !isMarksEmpty && !isSurnameEmpty) {
            button.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText name = (EditText) findViewById(R.id.name);
        EditText surname = (EditText) findViewById(R.id.surname);
        EditText marks = (EditText) findViewById(R.id.marks);

        outState.putString("name", name.getText().toString());
        outState.putString("surname", surname.getText().toString());
        outState.putString("marks", marks.getText().toString());

    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        EditText name = (EditText) findViewById(R.id.name);
        EditText surname = (EditText) findViewById(R.id.surname);
        EditText marks = (EditText) findViewById(R.id.marks);

        name.setText(savedInstanceState.getString("name"));
        surname.setText(savedInstanceState.getString("surname"));
        marks.setText(savedInstanceState.getString("marks"));
    }

}