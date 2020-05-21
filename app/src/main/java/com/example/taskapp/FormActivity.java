package com.example.taskapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.taskapp.models.Task;

import static com.daimajia.androidanimations.library.Techniques.Shake;

public class FormActivity extends AppCompatActivity {
    private EditText editTitle;
    private EditText editDesc;
    private Task task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        if (getSupportActionBar() !=null){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Новая задача");
    }
        editTitle = findViewById(R.id.edit_title);
        editDesc = findViewById(R.id.edit_desc);

        task = (Task) getIntent().getSerializableExtra("task");
        if (task != null){
            editTitle.setText(task.getTitle());
            editDesc.setText(task.getDesk());
        }


    }

   @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void onClick(View view) {
        String title = editTitle.getText().toString().trim();
        String desc = editDesc.getText().toString().trim();
        if (title.isEmpty()){
            editTitle.setError("Введите задачу");
            YoYo.with(Shake)
                    .duration(400)
                    .playOn(editTitle);
            return;
        }
        if (desc.isEmpty()){
            editDesc.setError("Введите описание");
            YoYo.with(Shake)
                    .duration(400)
                    .playOn(editDesc);
            return;
        }

        if (task != null){
            task.setTitle(title);
            task.setDesk(desc);
            App.getInstance().getDatabase().taskDao().update(task);
        }else {
         task = new Task();
            task.setTitle(title);
            task.setDesk(desc);
        App.getInstance().getDatabase().taskDao().insert(task);
//        запись базаданных
        finish();

    }
}

}
