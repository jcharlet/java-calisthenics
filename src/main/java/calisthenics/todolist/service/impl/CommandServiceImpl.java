package calisthenics.todolist.service.impl;

import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.model.command.UserCommand;
import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.CommandService;
import calisthenics.todolist.service.CommunicationService;
import calisthenics.todolist.service.IOService;
import calisthenics.todolist.service.command.CommandStrategy;
import calisthenics.todolist.service.command.impl.*;

/**
 * Created by jcharlet on 18/07/16.
 */
//TODO provide a way to change configuration and store in memory or on disk on demand
public class CommandServiceImpl implements CommandService {

    private final CommunicationService communicationService;
    private final IOService ioService;
    private final TodoListDao todoListDao;

    public CommandServiceImpl(TodoListDao todoListDao, CommunicationService communicationService, IOService ioService) {
        this.todoListDao = todoListDao;
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
    public void executeUserCommand(UserCommand command) {
        CommandStrategy commandStrategy;
        switch (command) {
            case create:
                commandStrategy = new CreateCommandStrategy(todoListDao);
                break;
            case add:
                commandStrategy = new AddTaskCommandStrategy(todoListDao,communicationService);
                break;
            case show:
                commandStrategy = new ShowCommandStragegy(todoListDao,communicationService);
                break;
            case importFile:
                commandStrategy = new ImportFileCommandStragegy(todoListDao,communicationService, ioService);
                break;
            case help:
            default:
                commandStrategy = new ShowHelpCommandStrategy(todoListDao,communicationService);
                break;
        }
        commandStrategy.executeCommand();
    }

}
