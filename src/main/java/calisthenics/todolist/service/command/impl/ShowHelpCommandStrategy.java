package calisthenics.todolist.service.command.impl;

import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.model.command.UserCommand;
import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.CommunicationService;
import calisthenics.todolist.service.command.CommandStrategy;

/**
 * Created by jcharlet on 25/07/16.
 */
public class ShowHelpCommandStrategy extends CommandStrategy {

    private final CommunicationService communicationService;

    public ShowHelpCommandStrategy(TodoListDao todoListDao, CommunicationService communicationService) {
        super(todoListDao);
        this.communicationService=communicationService;
    }

    @Override
    public void executeCommand() {
        this.communicationService.tellUser(new Message("Here are the available commands:"));
        String listOfCommands = "";
        for (UserCommand availableCommand : UserCommand.values()) {
            listOfCommands += availableCommand.name() + " ";
        }
        this.communicationService.tellUser(new Message(listOfCommands));
    }

}
