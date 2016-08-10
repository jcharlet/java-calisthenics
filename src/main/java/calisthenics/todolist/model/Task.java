package calisthenics.todolist.model;

/**
 * Created by jcharlet on 18/07/16.
 */
public class Task {
    private String name;
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void toggleStatus(){
        this.isDone=(!this.isDone);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Task{");
        sb.append("name='").append(name).append('\'');
        sb.append(", isDone=").append(isDone);
        sb.append('}');
        return sb.toString();
    }
}
