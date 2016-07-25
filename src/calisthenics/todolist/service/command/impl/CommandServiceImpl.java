package calisthenics.todolist.service.command.impl;

import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.command.UserCommand;
import calisthenics.todolist.model.command.UserCommandOutput;
import calisthenics.todolist.service.command.CommandService;
import calisthenics.todolist.service.communication.CommunicationService;
import calisthenics.todolist.service.command.AddTaskCommandRunner;
import calisthenics.todolist.service.command.CommandRunner;
import calisthenics.todolist.service.command.CreateCommandRunner;
import calisthenics.todolist.service.command.ShowHelpCommandRunner;

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
    public UserCommandOutput executeUserCommand(UserCommand command, TodoList todoList) {
        CommandRunner commandRunner = null;
        switch (command) {
            case create:
                commandRunner = new CreateCommandRunner(communicationService);
                break;
            case add:
                commandRunner = new AddTaskCommandRunner(communicationService);
                break;
            case help:
            default:
                commandRunner = new ShowHelpCommandRunner(communicationService);
                break;
        }
        return commandRunner.executeCreateCommand(todoList);
    }

}
