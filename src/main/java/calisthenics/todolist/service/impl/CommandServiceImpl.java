package calisthenics.todolist.service.impl;

import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.command.UserCommand;
import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.IOService;
import calisthenics.todolist.service.CommandService;
import calisthenics.todolist.service.command.CommandStrategy;
import calisthenics.todolist.service.CommunicationService;
import calisthenics.todolist.service.command.impl.*;

/**
 * Created by jcharlet on 18/07/16.
 */
public class CommandServiceImpl implements CommandService {

    private final CommunicationService communicationService;
    private final IOService ioService;

    public CommandServiceImpl(CommunicationService communicationService, IOService ioService) {
        this.communicationService = communicationService;
        this.ioService = ioService;
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
            case importFile:
                commandStrategy = new ImportFileCommandStragegy(communicationService, ioService);
                break;
            case help:
            default:
                commandStrategy = new ShowHelpCommandStrategy(communicationService);
                break;
        }
        commandStrategy.executeCommand(todoList);
    }

}
