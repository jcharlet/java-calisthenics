package calisthenics.todolist.service.command.impl;

import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.CommunicationService;
import calisthenics.todolist.service.command.CommandStrategy;

/**
 * Created by jcharlet on 27/07/16.
 */
public class ShowCommandStragegy extends CommandStrategy{
    private final CommunicationService communicationService;

    public ShowCommandStragegy(TodoListDao todoListDao, CommunicationService communicationService) {
        super(todoListDao);
        this.communicationService = communicationService;
    }

    @Override
    public void executeCommand() {
        communicationService.tellUser(new Message(todoListDao.get().toString()));
    }
}
