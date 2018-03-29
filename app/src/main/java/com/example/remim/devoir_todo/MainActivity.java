package com.example.remim.devoir_todo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.remim.devoir_todo.Core.Priority;
import com.example.remim.devoir_todo.Core.Todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

  private ListView list;
  private ArrayList<Todo> todoList;
  private AdapterTodo adapter;
  private Button btnAdd;

  public void initComponents() {
    list = findViewById(R.id.todo_list);
    btnAdd = findViewById(R.id.btn_add);

    todoList = new ArrayList<Todo>();
    todoList.add(new Todo("Faire le ménage", Priority.Normal));
    todoList.add(new Todo("Acheter à manger", Priority.High));
    todoList.add(new Todo("Appeler mamie", Priority.High));

    adapter = new AdapterTodo(this, todoList);

    sortTodolistByPriority(todoList);
    list.setAdapter(adapter);

    btnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        goToFormView();
      }
    });
  }

  public void sortTodolistByPriority(ArrayList<Todo> todoList) {
    Collections.sort(todoList, new Comparator<Todo>() {
      @Override
      public int compare(Todo t1, Todo t2) {
        if (t1.getPriority() == t2.getPriority())
          return t1.getName().compareTo(t2.getName());
        else
          return t1.getPriority().compareTo(t2.getPriority());
      }
    });
  }

  public void goToFormView() {
    Intent intent = new Intent(MainActivity.this, FormAddActivity.class);
    startActivityForResult(intent, 1);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 1) {
      if(resultCode == Activity.RESULT_OK) {

        Bundle extras = data.getExtras();
        if (extras != null){
          String todoName = extras.getString("todoName");
          String priority = extras.getString("priority");

          TodoPriorityHelper helper = TodoPriorityHelper.getInstance();
          Priority todoPriority = helper.getTodoPriority(priority);

          todoList.add(new Todo(todoName, todoPriority));
          sortTodolistByPriority(todoList);
          list.setAdapter(adapter);
        }
      }
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initComponents();
  }
}
