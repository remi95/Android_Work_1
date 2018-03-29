package com.example.remim.devoir_todo.Core;

public class Todo {

  private String name;
  private Priority priority;
  private boolean isDone;

  public Todo(String name, Priority priority) {
    this.name = name;
    this.priority = priority;
    this.setDone(false);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Priority getPriority() {
    return priority;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }

  public boolean isDone() {
    return isDone;
  }

  public void setDone(boolean done) {
    isDone = done;
  }
}
