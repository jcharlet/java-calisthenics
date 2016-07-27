package calisthenics.todolist.service.command.impl;

import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.command.CommandStrategy;
import calisthenics.todolist.service.CommunicationService;

/**
 * Created by jcharlet on 27/07/16.
 */
public class ShowCommandStragegy implements CommandStrategy{
    private final CommunicationService communicationService;

    public ShowCommandStragegy(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public void executeCommand(TodoList todoList) {
        communicationService.tellUser(new Message(todoList.toString()));
    }
}
