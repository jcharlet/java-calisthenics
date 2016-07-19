import calisthenics.todolist.Main;
import calisthenics.todolist.model.Task;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.UserCommand;
import calisthenics.todolist.model.UserCommandOutput;
import stubs.IOServiceStub;

/**
 * Created by jcharlet on 18/07/16.
 */
public class MainTest {

    public static void main(String[] args) {
        MainTest main = new MainTest();
        main.testCreateNewList();
        main.testAddTaskToList();
        main.testGetHelp();
    }

    private void testCreateNewList() {
        //given the program started
        Main main = new Main();

        //and the expected result
        TodoList expectedTodoList = new TodoList();
        final String expectedOutput = expectedTodoList.toString();

        // when I ask to create a todo list
        final UserCommandOutput userCommandOutput = main.executeUserCommand(UserCommand.create);

        // todo list is returned with a test task

        if (userCommandOutput.text.equals(expectedOutput)) {
            System.out.println("testCreateNewList OK");
        } else {
            throw new IllegalStateException("createNewList: not the expected output: " + userCommandOutput + " instead of: " + expectedOutput);
        }
    }

    private void testAddTaskToList() {
        //GIVEN the program started
        IOServiceStub ioServiceStub = new IOServiceStub();
        Main main = new Main(ioServiceStub);

        //with our stub prepared
        ioServiceStub.stubMessage="test";

        // and one todo list was created
        main.executeUserCommand(UserCommand.create);

        //and the expected result
        Task testTask = new Task("test");
        TodoList expectedTodoList = new TodoList();
        expectedTodoList.addTask(testTask);
        final String expectedOutput = expectedTodoList.toString();

        // WHEN I ask to add a task
        final UserCommandOutput userCommandOutput = main.executeUserCommand(UserCommand.addTask);

        // THEN todo list is returned with a test task

        if (userCommandOutput.text.equals(expectedOutput)) {
            System.out.println("testAddTaskToList OK");
        } else {
            throw new IllegalStateException("add task to list: not the expected output: " + userCommandOutput + " instead of: " + expectedOutput);
        }
    }

    private void testGetHelp(){
        //given the program started
        Main main = new Main(new IOServiceStub());

        //and the expected result
        String expectedOutput="";
        for (UserCommand availableCommand:UserCommand.values()){
            expectedOutput+=availableCommand.name() + " ";
        }

        // when I ask to get help
        final UserCommandOutput userCommandOutput = main.executeUserCommand(UserCommand.help);

        // todo list is returned with a test task

        if (userCommandOutput.text.equals(expectedOutput)) {
            System.out.println("testGetHelp OK");
        } else {
            throw new IllegalStateException("get help: not the expected output: " + userCommandOutput + " instead of: " + expectedOutput);
        }
    }

}
