package calisthenics.todolist.model;

/**
 * Created by jcharlet on 19/07/16.
 */
public enum UserCommand {
    CREATE_TODO_LIST("create");

    public String value;

    UserCommand(String value) {
        this.value = value;
    }
}
