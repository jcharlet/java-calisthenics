package calisthenics.todolist.model;

/**
 * Created by jcharlet on 18/07/16.
 */
public class Task {
    String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Task{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
