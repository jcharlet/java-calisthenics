package calisthenics.todolist.model;

import java.util.Arrays;

/**
 * Created by jcharlet on 18/07/16.
 */
public class TodoList {
    Task[] tasks;

    public TodoList() {
    }

    public TodoList(Task[] tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TodoList{");
        sb.append("tasks=").append(tasks == null ? "null" : Arrays.asList(tasks).toString());
        sb.append('}');
        return sb.toString();
    }
}
