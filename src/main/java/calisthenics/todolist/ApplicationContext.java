package calisthenics.todolist;

import calisthenics.todolist.dao.TodoListImportInterface;
import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.dao.impl.FileTodoListDaoImpl;
import calisthenics.todolist.dao.impl.MemoryTodoListDaoImpl;
import calisthenics.todolist.service.CommandService;
import calisthenics.todolist.service.CommunicationService;
import calisthenics.todolist.service.impl.CommandServiceImpl;
import calisthenics.todolist.service.impl.CommunicationServiceImpl;

/**
 * Created by jcharlet on 01/08/16.
 */
class ApplicationContext {
    private static final StorageType STORAGE_TYPE = StorageType.FILE;
    public final TodoListDao todoListDao;
    final CommunicationService communicationService;
    final CommandService commandService;
    private final TodoListImportInterface todoListImportInterface;
    ApplicationContext() {
        this.communicationService = new CommunicationServiceImpl();
        switch (STORAGE_TYPE) {
            case FILE:
                this.todoListDao = new FileTodoListDaoImpl();
                this.todoListImportInterface = (TodoListImportInterface) todoListDao;
                break;
            case MEMORY:
            default:
                this.todoListDao = new MemoryTodoListDaoImpl();
                this.todoListImportInterface = new FileTodoListDaoImpl();
                break;
        }

        this.commandService = new CommandServiceImpl(todoListDao, communicationService, todoListImportInterface);
    }

    public ApplicationContext(CommunicationService communicationService, CommandService commandService, TodoListDao todoListDao, TodoListImportInterface todoListImportInterface) {
        this.communicationService = communicationService;
        this.commandService = commandService;
        this.todoListDao = todoListDao;
        this.todoListImportInterface = todoListImportInterface;
    }

    private enum StorageType {
        MEMORY, FILE
    }
}
