import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.dao.impl.TodoListDaoImpl;
import calisthenics.todolist.model.Task;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.command.UserCommand;
import calisthenics.todolist.service.IOService;
import calisthenics.todolist.service.impl.CommandServiceImpl;
import calisthenics.todolist.service.impl.IOServiceImpl;
import stubs.CommunicationServiceStub;
import stubs.IOServiceStub;

/**
 * Created by jcharlet on 18/07/16.
 */
public class CommandServiceTest {

    public static void main(String[] args) {
        CommandServiceTest main = new CommandServiceTest();
        main.testCreateNewList();
        main.testAddTaskToList();
        main.testGetHelp();
        main.testShowTodoList();
        main.testImportTodoListFromFile();
    }

    private void testCreateNewList() {
        //given the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        IOService ioService = new IOServiceStub();
        TodoListDao todoListDao = new TodoListDaoImpl();
        final CommandServiceImpl commandService = new CommandServiceImpl(todoListDao, communicationServiceStub, ioService);

        //with a not empty todo list
        TodoList initTodoList = new TodoList();
        initTodoList.addTask(new Task("test"));
        todoListDao.save(initTodoList);

        //and the expected result
        TodoList expectedTodoList = new TodoList();
        final String expectedOutput = expectedTodoList.toString();

        // when I ask to create a todo list
        commandService.executeUserCommand(UserCommand.create);
        final TodoList todoList = todoListDao.get();

        // todo list is returned with a test task

        if (expectedOutput.equals(todoList.toString())) {
            System.out.println("testCreateNewList OK");
        } else {
            throw new IllegalStateException("createNewList: not the expected output: " + todoList.toString() + " instead of: " + expectedOutput);
        }
    }

    private void testAddTaskToList() {
        //GIVEN the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        IOService ioService = new IOServiceStub();
        TodoListDao todoListDao = new TodoListDaoImpl();
        final CommandServiceImpl commandService = new CommandServiceImpl(todoListDao, communicationServiceStub, ioService);

        //with our stub prepared
        communicationServiceStub.stubInputMessage = "test";

        // and one empty todo list was created
        todoListDao.save(new TodoList());

        //and the expected result
        Task testTask = new Task("test");
        TodoList expectedTodoList = new TodoList();
        expectedTodoList.addTask(testTask);
        final String expectedOutput = expectedTodoList.toString();

        // WHEN I ask to add a task
        commandService.executeUserCommand(UserCommand.add);
        final TodoList todoList = todoListDao.get();

        // THEN todo list is returned with a test task

        if (expectedOutput.equals(todoList.toString())) {
            System.out.println("testAddTaskToList OK");
        } else {
            throw new IllegalStateException("add task to list: not the expected output: " + todoList.toString() + " instead of: " + expectedOutput);
        }
    }

    private void testShowTodoList() {
        //given the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        IOService ioService = new IOServiceStub();
        TodoListDao todoListDao = new TodoListDaoImpl();
        final CommandServiceImpl commandService = new CommandServiceImpl(todoListDao, communicationServiceStub, ioService);

        // with a todo list initialized
        TodoList initTodoList = new TodoList();
        initTodoList.addTask(new Task("test"));
        todoListDao.save(initTodoList);

        //and the expected result
        TodoList expectedTodoList = new TodoList();
        expectedTodoList.addTask(new Task("test"));
        String expectedOutput = expectedTodoList.toString();

        // when I ask to get help
        commandService.executeUserCommand(UserCommand.show);
        final TodoList todoList = todoListDao.get();

        // todo list is returned with a test task

        if (expectedOutput.equals(communicationServiceStub.stubOutputMessage) && todoList.toString().equals(expectedTodoList.toString())) {
            System.out.println("testShowTodoList OK");
        } else {
            throw new IllegalStateException("testShowTodoList: not the expected output: " + communicationServiceStub.stubOutputMessage + " instead of: " + expectedOutput);
        }
    }

    private void testGetHelp() {
        //given the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        IOService ioService = new IOServiceStub();
        TodoListDao todoListDao = new TodoListDaoImpl();
        final CommandServiceImpl commandService = new CommandServiceImpl(todoListDao, communicationServiceStub, ioService);

        // with no todo list initialized
        todoListDao.save(null);

        //and the expected result
        String expectedOutput = "";
        for (UserCommand availableCommand : UserCommand.values()) {
            expectedOutput += availableCommand.name() + " ";
        }

        // when I ask to get help
        commandService.executeUserCommand(UserCommand.help);
        final TodoList todoList = todoListDao.get();

        // todo list is returned with a test task

        if (expectedOutput.equals(communicationServiceStub.stubOutputMessage) && todoList == null) {
            System.out.println("testGetHelp OK");
        } else {
            throw new IllegalStateException("get help: not the expected output: " + communicationServiceStub.stubOutputMessage + " instead of: " + expectedOutput);
        }
    }

    private void testImportTodoListFromFile() {
        //GIVEN the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        //FIXME ioservice should be tested somewhere else, we should only use stubs here
        IOService ioService = new IOServiceImpl();
        TodoListDao todoListDao = new TodoListDaoImpl();
        final CommandServiceImpl commandService = new CommandServiceImpl(todoListDao, communicationServiceStub, ioService);

        //with our stub prepared
        communicationServiceStub.stubInputMessage = "src/test/resources/todolist.txt";

        // and one empty todo list was created
        // with a todo list initialized
        todoListDao.save(new TodoList());

        //and the expected result
        TodoList expectedTodoList = new TodoList();
        expectedTodoList.addTask(new Task("task1"));
        expectedTodoList.addTask(new Task("task2"));
        final String expectedOutput = expectedTodoList.toString();

        // WHEN I ask to add a task
        commandService.executeUserCommand(UserCommand.importFile);
        final TodoList todoList = todoListDao.get();

        // THEN todo list is returned with a test task

        if (expectedOutput.equals(todoList.toString())) {
            System.out.println("testImportTodoListFromFile OK");
        } else {
            throw new IllegalStateException("testImportTodoListFromFile: not the expected output: " + todoList.toString() + " instead of: " + expectedOutput);
        }
    }
}
