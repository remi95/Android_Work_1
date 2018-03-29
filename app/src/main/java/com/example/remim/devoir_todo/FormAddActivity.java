package com.example.remim.devoir_todo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.remim.devoir_todo.Core.Priority;
import com.example.remim.devoir_todo.Core.Todo;

import java.util.ArrayList;

public class FormAddActivity extends AppCompatActivity {

  private EditText etTodoName;
  private Spinner spinner;
  private Button btnSave, btnBack;

  public void initComponents() {
    etTodoName = findViewById(R.id.et_todo_name);
    spinner = findViewById(R.id.spinner_priority);
    btnSave = findViewById(R.id.btn_save);
    btnBack = findViewById(R.id.btn_back);

    spinner.setAdapter(new ArrayAdapter<Priority>(this, android.R.layout.simple_spinner_dropdown_item,Priority.values()));

    btnSave.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        addTodo();
      }
    });
    btnBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        goToMainView(null);
      }
    });
  }

  public void addTodo() {
    String todoName = etTodoName.getText().toString();
    String priority = spinner.getSelectedItem().toString();

    if (todoName.equals("")) {
      etTodoName.setError("Give a name for your Todo");
      return;
    }

    Bundle extras = new Bundle();
    extras.putString("todoName", todoName);
    extras.putString("priority", priority);

    goToMainView(extras);
  }

  public void goToMainView(Bundle extras) {
    Intent intent = new Intent(FormAddActivity.this, MainActivity.class);
    if (extras != null)
      intent.putExtras(extras);
    setResult(Activity.RESULT_OK, intent);
    finish();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_form_add);

    initComponents();
  }
}
