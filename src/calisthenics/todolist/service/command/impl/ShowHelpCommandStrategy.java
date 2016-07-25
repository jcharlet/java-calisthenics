package calisthenics.todolist.service.command.impl;

import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.command.UserCommand;
import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.command.CommandStrategy;
import calisthenics.todolist.service.communication.CommunicationService;

/**
 * Created by jcharlet on 25/07/16.
 */
public class ShowHelpCommandStrategy implements CommandStrategy {

    private final CommunicationService communicationService;

    public ShowHelpCommandStrategy(CommunicationService communicationService) {
        this.communicationService=communicationService;
    }

    @Override
    public void executeCommand(TodoList todoList) {
        this.communicationService.tellUser(new Message("Here are the available commands:"));
        String listOfCommands = "";
        for (UserCommand availableCommand : UserCommand.values()) {
            listOfCommands += availableCommand.name() + " ";
        }
        this.communicationService.tellUser(new Message(listOfCommands));
    }

}