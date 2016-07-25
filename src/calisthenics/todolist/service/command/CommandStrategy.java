package calisthenics.todolist.service.command;

import calisthenics.todolist.model.TodoList;

/**
 * Created by jcharlet on 25/07/16.
 */
public interface CommandStrategy {
    void executeCommand(TodoList todoList);
}
