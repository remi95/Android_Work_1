package com.example.remim.devoir_todo;

import com.example.remim.devoir_todo.Core.Priority;
import java.util.HashMap;

public class TodoPriorityHelper {

  private static TodoPriorityHelper instance;
  public HashMap<String, Priority> priorityReference;

  public TodoPriorityHelper() {
    priorityReference = new HashMap<>();
    priorityReference.put("Normal", Priority.Normal);
    priorityReference.put("High", Priority.High);
  }

  public static TodoPriorityHelper getInstance() {
    if (instance == null) {
      instance = new TodoPriorityHelper();
    }
    return instance;
  }

  public Priority getTodoPriority(String priority) {
    return priorityReference.get(priority);
  }
}
