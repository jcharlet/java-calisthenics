package calisthenics.todolist.service.command;

import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.command.UserCommandOutput;
import calisthenics.todolist.service.communication.CommunicationService;

/**
 * Created by jcharlet on 25/07/16.
 */
public class CreateCommandRunner extends CommandRunner {

    public CreateCommandRunner(CommunicationService communicationService) {
        super(communicationService);
    }

    @Override
    public UserCommandOutput executeCreateCommand(TodoList todoList) {
        if (todoList == null) {
            todoList = new TodoList();
        }
        todoList.emptyTasks();
        return new UserCommandOutput(todoList.toString());
    }
}
