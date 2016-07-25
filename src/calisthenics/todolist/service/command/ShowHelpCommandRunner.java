package calisthenics.todolist.service.command;

import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.command.UserCommand;
import calisthenics.todolist.model.command.UserCommandOutput;
import calisthenics.todolist.service.communication.CommunicationService;

/**
 * Created by jcharlet on 25/07/16.
 */
public class ShowHelpCommandRunner extends CommandRunner {

    public ShowHelpCommandRunner(CommunicationService communicationService) {
        super(communicationService);
    }

    @Override
    public UserCommandOutput executeCreateCommand(TodoList todoList) {
        this.communicationService.tellUser(new Message("Here are the available commands:"));
        String listOfCommands = "";
        for (UserCommand availableCommand : UserCommand.values()) {
            listOfCommands += availableCommand.name() + " ";
        }
        return new UserCommandOutput(listOfCommands);
    }

}
