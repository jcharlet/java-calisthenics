package calisthenics.todolist.service.command;

import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.command.UserCommand;
import calisthenics.todolist.model.command.UserCommandOutput;

/**
 * Created by jcharlet on 19/07/16.
 */
public interface CommandService {
    UserCommand parseCommmand(String commandString);

    UserCommandOutput executeUserCommand(UserCommand command, TodoList todoList);
}
