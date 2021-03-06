package calisthenics.todolist.service.impl;

import calisthenics.todolist.dao.TodoListImportInterface;
import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.model.command.UserCommand;
import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.CommandService;
import calisthenics.todolist.service.CommunicationService;
import calisthenics.todolist.service.command.CommandStrategy;
import calisthenics.todolist.service.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jcharlet on 18/07/16.
 */
//TODO provide a way to change configuration and store in memory or on disk on demand
public class CommandServiceImpl implements CommandService {

    private final CommunicationService communicationService;

    private final Map<UserCommand,CommandStrategy> mapOfCommandStrategies;

    public CommandServiceImpl(TodoListDao todoListDao, CommunicationService communicationService, TodoListImportInterface todoListImportInterface) {
        this.communicationService = communicationService;

        mapOfCommandStrategies = new HashMap<>();
        mapOfCommandStrategies.put(UserCommand.create, new CreateCommandStrategy(todoListDao, communicationService));
        mapOfCommandStrategies.put(UserCommand.add, new AddTaskCommandStrategy(todoListDao,communicationService));
        mapOfCommandStrategies.put(UserCommand.show, new ShowCommandStragegy(todoListDao,communicationService));
        mapOfCommandStrategies.put(UserCommand.importFile, new ImportFileCommandStragegy(todoListDao, communicationService, todoListImportInterface));
        mapOfCommandStrategies.put(UserCommand.help, new ShowHelpCommandStrategy(todoListDao,communicationService));
        mapOfCommandStrategies.put(UserCommand.toggleStatus, new ToggleStatusCommandStrategy(todoListDao,communicationService));
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
        CommandStrategy commandStrategy = mapOfCommandStrategies.get(command);
        commandStrategy.executeCommand();
    }

}
