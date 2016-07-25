package calisthenics.todolist.service.command;

import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.command.UserCommandOutput;
import calisthenics.todolist.service.communication.CommunicationService;

/**
 * Created by jcharlet on 25/07/16.
 */
public abstract class CommandRunner {
    protected final CommunicationService communicationService;

    protected CommandRunner(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    public UserCommandOutput executeCreateCommand(TodoList todoList) {
        return new UserCommandOutput("");
    }
}
