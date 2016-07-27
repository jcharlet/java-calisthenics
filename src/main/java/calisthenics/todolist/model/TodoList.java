package calisthenics.todolist.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jcharlet on 18/07/16.
 */
public class TodoList {
    List<Task> tasks;

    public TodoList() {
        this.tasks=new ArrayList<>();
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TodoList{");
        sb.append("tasks=").append(tasks);
        sb.append('}');
        return sb.toString();
    }

    public void emptyTasks() {
        this.tasks.clear();
    }
}
