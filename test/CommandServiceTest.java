import calisthenics.todolist.model.Task;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.command.UserCommand;
import calisthenics.todolist.model.command.UserCommandOutput;
import calisthenics.todolist.service.command.impl.CommandServiceImpl;
import stubs.CommunicationServiceStub;

/**
 * Created by jcharlet on 18/07/16.
 */
public class CommandServiceTest {

    public static void main(String[] args) {
        CommandServiceTest main = new CommandServiceTest();
        main.testCreateNewList();
        main.testAddTaskToList();
        main.testGetHelp();
    }

    private void testCreateNewList() {
        //given the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        final CommandServiceImpl commandService = new CommandServiceImpl(communicationServiceStub);

        //with an empty todo list
        final TodoList todoList = null;

        //and the expected result
        TodoList expectedTodoList = new TodoList();
        final String expectedOutput = expectedTodoList.toString();

        // when I ask to create a todo list
        final UserCommandOutput userCommandOutput = commandService.executeUserCommand(UserCommand.create, todoList);

        // todo list is returned with a test task

        if (userCommandOutput.text.equals(expectedOutput)) {
            System.out.println("testCreateNewList OK");
        } else {
            throw new IllegalStateException("createNewList: not the expected output: " + userCommandOutput.text + " instead of: " + expectedOutput);
        }
    }

    private void testAddTaskToList() {
        //GIVEN the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        final CommandServiceImpl commandService = new CommandServiceImpl(communicationServiceStub);

        //with our stub prepared
        communicationServiceStub.stubMessage="test";

        // and one empty todo list was created
        final TodoList todoList = new TodoList();

        //and the expected result
        Task testTask = new Task("test");
        TodoList expectedTodoList = new TodoList();
        expectedTodoList.addTask(testTask);
        final String expectedOutput = expectedTodoList.toString();

        // WHEN I ask to add a task
        final UserCommandOutput userCommandOutput = commandService.executeUserCommand(UserCommand.add, todoList);

        // THEN todo list is returned with a test task

        if (userCommandOutput.text.equals(expectedOutput)) {
            System.out.println("testAddTaskToList OK");
        } else {
            throw new IllegalStateException("add task to list: not the expected output: " + userCommandOutput.text + " instead of: " + expectedOutput);
        }
    }

    private void testGetHelp(){
        //given the program started
        CommunicationServiceStub communicationServiceStub = new CommunicationServiceStub();
        final CommandServiceImpl commandService = new CommandServiceImpl(communicationServiceStub);

        //and the expected result
        String expectedOutput="";
        for (UserCommand availableCommand:UserCommand.values()){
            expectedOutput+=availableCommand.name() + " ";
        }

        // when I ask to get help
        final UserCommandOutput userCommandOutput = commandService.executeUserCommand(UserCommand.help, null);

        // todo list is returned with a test task

        if (userCommandOutput.text.equals(expectedOutput)) {
            System.out.println("testGetHelp OK");
        } else {
            throw new IllegalStateException("get help: not the expected output: " + userCommandOutput.text + " instead of: " + expectedOutput);
        }
    }

}
