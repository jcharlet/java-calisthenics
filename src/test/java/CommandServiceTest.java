import calisthenics.todolist.dao.TodoListImportInterface;
import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.dao.impl.FileTodoListDaoImpl;
import calisthenics.todolist.dao.impl.MemoryTodoListDaoImpl;
import calisthenics.todolist.model.Task;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.command.UserCommand;
import calisthenics.todolist.service.command.impl.CreateCommandStrategy;
import calisthenics.todolist.service.impl.CommandServiceImpl;
import stubs.CommunicationServiceStub;
import stubs.TodoListImportInterfaceStub;

/**
 * Created by jcharlet on 18/07/16.
 */
public class CommandServiceTest {

    public static void main(String[] args) {
        CommandServiceTest testRunner = new CommandServiceTest();
        testRunner.testCreateNewList();
        testRunner.testCreateNewHouseChoresList();
        testRunner.testAddTaskToList();
        testRunner.testGetHelp();
        testRunner.testShowTodoList();
        testRunner.testImportTodoListFromFile();
        testRunner.testToggleStatusWithValidTaskNumber();
        testRunner.testToggleStatusWithInvalidTaskNumber();
    }

    private void testCreateNewList() {
        //given the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        TodoListImportInterface todoListImportInterface = new TodoListImportInterfaceStub();
        TodoListDao todoListDao = new MemoryTodoListDaoImpl();
        final CommandServiceImpl commandService = new CommandServiceImpl(todoListDao, communicationServiceStub, todoListImportInterface);

        //with a not empty to-do list
        TodoList initTodoList = new TodoList();
        initTodoList.addTask(new Task("test"));
        todoListDao.save(initTodoList);

        //and the expected result
        TodoList expectedTodoList = new TodoList();
        final String expectedOutput = expectedTodoList.toString();

        //with our user message prepared, to ask for an empty todolist
        communicationServiceStub.stubInputMessage.add(CreateCommandStrategy.TodoListCreationType.empty.name());

        // when I ask to create a to-do list
        commandService.executeUserCommand(UserCommand.create);
        final TodoList todoList = todoListDao.get();

        // to-do list is returned with a test task

        if (expectedOutput.equals(todoList.toString())) {
            System.out.println("testCreateNewList OK");
        } else {
            throw new IllegalStateException("createNewList: not the expected output: " + todoList.toString() + " instead of: " + expectedOutput);
        }
    }

    private void testCreateNewHouseChoresList() {
        //given the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        TodoListImportInterface todoListImportInterface = new TodoListImportInterfaceStub();
        TodoListDao todoListDao = new MemoryTodoListDaoImpl();
        final CommandServiceImpl commandService = new CommandServiceImpl(todoListDao, communicationServiceStub, todoListImportInterface);

        //with a not empty to-do list
        TodoList initTodoList = new TodoList();
        initTodoList.addTask(new Task("test"));
        todoListDao.save(initTodoList);

        //and the expected result
        TodoList expectedTodoList = new TodoList();
        expectedTodoList.addTask(new Task("remove the dust"));
        expectedTodoList.addTask(new Task("Vacuum the floor"));
        expectedTodoList.addTask(new Task("wash the floor"));
        final String expectedOutput = expectedTodoList.toString();

        //with our user message prepared, to ask for an house chores todolist
        communicationServiceStub.stubInputMessage.add(CreateCommandStrategy.TodoListCreationType.house_chores.name());

        // when I ask to create a to-do list for house chores
        commandService.executeUserCommand(UserCommand.create);
        final TodoList todoList = todoListDao.get();

        // to-do list is returned with a test task

        if (expectedOutput.equals(todoList.toString())) {
            System.out.println("testCreateNewHouseChoresList OK");
        } else {
            throw new IllegalStateException("testCreateNewHouseChoresList: not the expected output: " + todoList.toString() + " instead of: " + expectedOutput);
        }
    }

    private void testAddTaskToList() {
        //GIVEN the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        TodoListImportInterface todoListImportInterface = new TodoListImportInterfaceStub();
        TodoListDao todoListDao = new MemoryTodoListDaoImpl();
        final CommandServiceImpl commandService = new CommandServiceImpl(todoListDao, communicationServiceStub, todoListImportInterface);

        //with our stub prepared
        communicationServiceStub.stubInputMessage.add("test");

        // and one empty to-do list was created
        todoListDao.save(new TodoList());

        //and the expected result
        Task testTask = new Task("test");
        TodoList expectedTodoList = new TodoList();
        expectedTodoList.addTask(testTask);
        final String expectedOutput = expectedTodoList.toString();

        // WHEN I ask to add a task
        commandService.executeUserCommand(UserCommand.add);
        final TodoList todoList = todoListDao.get();

        // THEN to-do list is returned with a test task

        if (expectedOutput.equals(todoList.toString())) {
            System.out.println("testAddTaskToList OK");
        } else {
            throw new IllegalStateException("add task to list: not the expected output: " + todoList.toString() + " instead of: " + expectedOutput);
        }
    }

    private void testShowTodoList() {
        //given the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        TodoListImportInterface todoListImportInterface = new TodoListImportInterfaceStub();
        TodoListDao todoListDao = new MemoryTodoListDaoImpl();
        final CommandServiceImpl commandService = new CommandServiceImpl(todoListDao, communicationServiceStub, todoListImportInterface);

        // with a to-do list initialized
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

        // to-do list is returned with a test task

        if (expectedOutput.equals(communicationServiceStub.stubOutputMessage.poll()) && todoList.toString().equals(expectedTodoList.toString())) {
            System.out.println("testShowTodoList OK");
        } else {
            throw new IllegalStateException("testShowTodoList: not the expected output: " + communicationServiceStub.stubOutputMessage + " instead of: " + expectedOutput);
        }
    }

    private void testGetHelp() {
        //given the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        TodoListImportInterface todoListImportInterface = new TodoListImportInterfaceStub();
        TodoListDao todoListDao = new MemoryTodoListDaoImpl();
        final CommandServiceImpl commandService = new CommandServiceImpl(todoListDao, communicationServiceStub, todoListImportInterface);

        // with no to-do list initialized
        todoListDao.save(null);

        //and the expected result
        String expectedOutput = "";
        for (UserCommand availableCommand : UserCommand.values()) {
            expectedOutput += availableCommand.name() + " ";
        }

        // when I ask to get help
        commandService.executeUserCommand(UserCommand.help);

        // We introduce the list of commands
//        communicationServiceStub.stubOutputMessage.poll();

        // then the list of commands is returned
        if (expectedOutput.equals(communicationServiceStub.stubOutputMessage.getLast())) {
            System.out.println("testGetHelp OK");
        } else {
            throw new IllegalStateException("get help: not the expected output: " + communicationServiceStub.stubOutputMessage + " instead of: " + expectedOutput);
        }
    }

    private void testImportTodoListFromFile() {
        //GIVEN the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        //FIXME todoListImportInterface should be tested somewhere else, we should only use stubs here
        TodoListImportInterface todoListImportInterface = new FileTodoListDaoImpl();
        TodoListDao todoListDao = new MemoryTodoListDaoImpl();
        final CommandServiceImpl commandService = new CommandServiceImpl(todoListDao, communicationServiceStub, todoListImportInterface);

        //with our stub prepared
        communicationServiceStub.stubInputMessage.add("src/test/resources/todolistToImport.txt");

        // and one empty to-do list was created
        // with a to-do list initialized
        todoListDao.save(new TodoList());

        //and the expected result
        TodoList expectedTodoList = new TodoList();
        expectedTodoList.addTask(new Task("task1"));
        expectedTodoList.addTask(new Task("task2"));
        final String expectedOutput = expectedTodoList.toString();

        // WHEN I ask to add a task
        commandService.executeUserCommand(UserCommand.importFile);
        final TodoList todoList = todoListDao.get();

        // THEN to-do list is returned with a test task

        if (expectedOutput.equals(todoList.toString())) {
            System.out.println("testImportTodoListFromFile OK");
        } else {
            throw new IllegalStateException("testImportTodoListFromFile: not the expected output: " + todoList.toString() + " instead of: " + expectedOutput);
        }
    }

    private void testToggleStatusWithValidTaskNumber() {
        //GIVEN the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        TodoListImportInterface todoListImportInterface = new TodoListImportInterfaceStub();
        TodoListDao todoListDao = new MemoryTodoListDaoImpl();
        final CommandServiceImpl commandService = new CommandServiceImpl(todoListDao, communicationServiceStub, todoListImportInterface);

        //with our user message prepared, to ask for the right task number
        communicationServiceStub.stubInputMessage.add("0");

        // with a to-do list initialized
        TodoList initTodoList = new TodoList();
        initTodoList.addTask(new Task("taskToComplete"));
        initTodoList.addTask(new Task("taskNeverCompleted"));
        todoListDao.save(initTodoList);

        //and the expected result: the task test is done
        final Task taskToComplete = new Task("taskToComplete", true);
        final Task taskNeverCompleted = new Task("taskNeverCompleted");
        TodoList expectedTodoList = new TodoList();
        expectedTodoList.addTask(taskToComplete);
        expectedTodoList.addTask(taskNeverCompleted);
        final String expectedOutput = expectedTodoList.toString();

        // WHEN I ask to add a task
        commandService.executeUserCommand(UserCommand.toggleStatus);
        final TodoList todoList = todoListDao.get();

        // THEN to-do list is returned with a test task

        if (expectedOutput.equals(todoList.toString())) {
            System.out.println("testToggleStatusWithValidTaskNumber OK");
        } else {
            throw new IllegalStateException("add task to list: not the expected output: " + todoList.toString() + " instead of: " + expectedOutput);
        }
    }


    private void testToggleStatusWithInvalidTaskNumber() {
        //GIVEN the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        TodoListImportInterface todoListImportInterface = new TodoListImportInterfaceStub();
        TodoListDao todoListDao = new MemoryTodoListDaoImpl();
        final CommandServiceImpl commandService = new CommandServiceImpl(todoListDao, communicationServiceStub, todoListImportInterface);

        //with our user message prepared, to ask for some invalid task number
        communicationServiceStub.stubInputMessage.add("2");
        //with our user message prepared, to ask after for one valid task number
        communicationServiceStub.stubInputMessage.add("1");

        // with a to-do list initialized
        TodoList initTodoList = new TodoList();
        initTodoList.addTask(new Task("taskToComplete"));
        initTodoList.addTask(new Task("taskNeverCompleted"));
        todoListDao.save(initTodoList);

        //and the last expected message: ask again for a number to provide

        // WHEN I ask to add a task
        commandService.executeUserCommand(UserCommand.toggleStatus);

        // THEN the first task is assigned the number 0
        String expectedOutputMessage = "[0] " + initTodoList.getTasks().get(0).toString();
        if (!communicationServiceStub.stubOutputMessage.pollFirst().contains(expectedOutputMessage)) {
            throw new IllegalStateException("testToggleStatusWithInvalidTaskNumber: not the expected output: " +
                    communicationServiceStub.stubOutputMessage + " instead of: " + expectedOutputMessage);
        }

        // AND we ask the user to provide a task
        communicationServiceStub.stubOutputMessage.pollFirst();

        // AND we return to the user that we could not find his invalid task
        expectedOutputMessage = "There is no such task.";
        if (!communicationServiceStub.stubOutputMessage.pollFirst().contains(expectedOutputMessage)) {
            throw new IllegalStateException("testToggleStatusWithInvalidTaskNumber: not the expected output: " +
                    communicationServiceStub.stubOutputMessage + " instead of: " + expectedOutputMessage);
        }
        System.out.println("testToggleStatusWithInvalidTaskNumber OK");
    }
}
