package com.example.remim.devoir_todo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.remim.devoir_todo.Core.Priority;
import com.example.remim.devoir_todo.Core.Todo;

import java.util.ArrayList;

public class AdapterTodo extends ArrayAdapter<Todo> {

  private ArrayList<Todo> todoList;
  private static int resourceItem = R.layout.todo_item;

  public AdapterTodo(@NonNull Context context, @NonNull ArrayList<Todo> todoList) {
    super(context, resourceItem, todoList);
    this.todoList = todoList;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

    final Todo currentTodo = todoList.get(position);

    if (convertView == null) {
      LayoutInflater inflater;
      inflater = LayoutInflater.from(getContext());
      convertView = inflater.inflate(resourceItem, null);


    }

    LinearLayout todoItem = convertView.findViewById(R.id.todo_item);
    TextView todoName = convertView.findViewById(R.id.todo_name);
    CheckBox checkbox = convertView.findViewById(R.id.checkbox);

    checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        currentTodo.setDone(isChecked);
      }
    });

    String currentName = currentTodo.getName();
    Priority currentPriority = currentTodo.getPriority();

    todoName.setText(currentName);
    checkbox.setTag(position);
    if (currentPriority == Priority.High)
      todoItem.setBackgroundColor(Color.RED);
    else
      todoItem.setBackgroundColor(Color.WHITE);

    checkbox.setChecked(currentTodo.isDone());

    return convertView;
  }
}
