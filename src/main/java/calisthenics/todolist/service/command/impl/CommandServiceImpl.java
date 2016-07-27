package calisthenics.todolist.service.command.impl;

import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.command.UserCommand;
import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.command.CommandService;
import calisthenics.todolist.service.command.CommandStrategy;
import calisthenics.todolist.service.communication.CommunicationService;

/**
 * Created by jcharlet on 18/07/16.
 */
public class CommandServiceImpl implements CommandService {

    private final CommunicationService communicationService;

    public CommandServiceImpl(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }


    @Override
    public UserCommand parseCommmand(String commandString) {
        UserCommand command;
        try{
            command = UserCommand.valueOf(commandString);
        }catch (IllegalArgumentException e){
            communicationService.tellUser(new Message("unknown command"));
            return null;
        }
        return command;
    }


    @Override
    public void executeUserCommand(UserCommand command, TodoList todoList) {
        CommandStrategy commandStrategy;
        switch (command) {
            case create:
                commandStrategy = new CreateCommandStrategy();
                break;
            case add:
                commandStrategy = new AddTaskCommandStrategy(communicationService);
                break;
            case show:
                commandStrategy = new ShowCommandStragegy(communicationService);
                break;
            case help:
            default:
                commandStrategy = new ShowHelpCommandStrategy(communicationService);
                break;
        }
        commandStrategy.executeCommand(todoList);
    }

}
