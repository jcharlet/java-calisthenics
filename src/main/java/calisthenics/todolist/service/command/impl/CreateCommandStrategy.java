package calisthenics.todolist.service.command.impl;

import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.service.command.CommandStrategy;

/**
 * Created by jcharlet on 25/07/16.
 */
public class CreateCommandStrategy implements CommandStrategy {

    public CreateCommandStrategy() {
    }

    @Override
    public void executeCommand(TodoList todoList) {
        if (todoList == null) {
            todoList = new TodoList();
        }
        todoList.emptyTasks();
    }
}
