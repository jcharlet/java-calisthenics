package calisthenics.todolist;

import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.dao.impl.FileTodoListDaoImpl;
import calisthenics.todolist.service.CommandService;
import calisthenics.todolist.service.CommunicationService;
import calisthenics.todolist.service.IOService;
import calisthenics.todolist.service.impl.CommandServiceImpl;
import calisthenics.todolist.service.impl.CommunicationServiceImpl;
import calisthenics.todolist.service.impl.IOServiceImpl;

/**
 * Created by jcharlet on 01/08/16.
 */
class ApplicationContext {
    final CommunicationService communicationService;
    final CommandService commandService;
    final IOService ioService;
    public final TodoListDao todoListDao;

    ApplicationContext() {
        this.ioService = new IOServiceImpl();
        this.communicationService = new CommunicationServiceImpl();
//        this.todoListDao = new MemoryTodoListDaoImpl();
        this.todoListDao = new FileTodoListDaoImpl();

        this.commandService = new CommandServiceImpl(todoListDao, communicationService, this.ioService);
    }

    public ApplicationContext(CommunicationService communicationService, CommandService commandService, IOService ioService, TodoListDao todoListDao) {
        this.communicationService = communicationService;
        this.commandService = commandService;
        this.ioService = ioService;
        this.todoListDao = todoListDao;
    }
}
