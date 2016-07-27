package calisthenics.todolist.service;

import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.command.UserCommand;

/**
 * Created by jcharlet on 19/07/16.
 */
public interface CommandService {
    UserCommand parseCommmand(String commandString);

    void executeUserCommand(UserCommand command, TodoList todoList);
}
