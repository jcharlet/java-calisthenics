package calisthenics.todolist;

import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.dao.impl.TodoListDaoImpl;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.command.UserCommand;
import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.CommandService;
import calisthenics.todolist.service.CommunicationService;
import calisthenics.todolist.service.IOService;
import calisthenics.todolist.service.impl.CommandServiceImpl;
import calisthenics.todolist.service.impl.CommunicationServiceImpl;
import calisthenics.todolist.service.impl.IOServiceImpl;

/**
 * write a CLI which enables you to deal with a todo list
 * <h2>rules</h2>
 * <ol>
 * <li>Only One Level Of Indentation Per Method</li>
 * <li>
 * Don’t Use The ELSE Keyword</li>
 * <li>
 * Wrap All Primitives And Strings</li>
 * <li>
 * First Class Collections</li>
 * <li>
 * One Dot Per Line</li>
 * <li>
 * Don’t Abbreviate</li>
 * <li>
 * Keep All Entities Small</li>
 * <li>
 * No Classes With More Than Two Instance Variables</li>
 * <li>
 * No Getters/Setters/Properties</li>
 * </ol>
 * <h2>US</h2>
 * <h3>Core User Stories</h3>
 * As a user I can create a todo list<br/>
 * As a user I can add tasks to the todo list<br/>
 * As a user I can see all the tasks in a list<br/>
 * As a user I can manipulate the todo list through a menu<br/>
 * <h3>Secondary User Stories</h3>
 * As a user I can open a list from a text file<br/>
 * As a user I can save a list to a text file<br/>
 * As a user I can delete a task<br/>
 * As a user I can update a task<br/>
 * <h3>Reach User Stories</h3>
 * As a user I can set a task status<br/>
 * As a user I can toggle a task status as complete or incomplete
 */
public class Main {

    private final CommunicationService communicationService;
    private final CommandService commandService;
    private final IOService ioService;
    private final TodoListDao todoListDao;

    public Main() {
        this.ioService = new IOServiceImpl();
        this.communicationService = new CommunicationServiceImpl();
        this.todoListDao = new TodoListDaoImpl();

        this.commandService = new CommandServiceImpl(todoListDao, communicationService, this.ioService);
    }

    public Main(CommunicationService communicationService, CommandService commandService, IOService ioService, TodoListDao todoListDao) {
        this.communicationService = communicationService;
        this.commandService = commandService;
        this.ioService = ioService;
        this.todoListDao = todoListDao;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.todoListDao.save(new TodoList());
        while (true) {
            main.runTodoListProgram();
        }
    }

    private void runTodoListProgram() {
        communicationService.tellUser(new Message("state your command"));

        final Message userMessage = communicationService.getUserInput();

        UserCommand command = commandService.parseCommmand(userMessage.text);

        if (command != null){
            commandService.executeUserCommand(command);
        }
    }

}
