import calisthenics.todolist.*;
import calisthenics.todolist.model.Task;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.UserCommand;

/**
 * Created by jcharlet on 18/07/16.
 */
public class MainTest {

    public static void main(String[] args) {
        MainTest main = new MainTest();
        main.testCreateNewList();
    }

    private void testCreateNewList() {
        //given the program started
        Main main = new Main();

        //and the expected result
        Task testTask = new Task("test");
        TodoList expectedTodoList = new TodoList();
        expectedTodoList.addTask(testTask);
        final String expectedOutput = expectedTodoList.toString();

        // when I ask to create a todo list
        final UserCommandOutput userCommandOutput = main.executeUserCommand(UserCommand.CREATE_TODO_LIST);

        // todo list is returned with a test task

        if (userCommandOutput.text.equals(expectedOutput)) {
            System.out.println("OK");
        } else {
            throw new IllegalStateException("createNewList: not the expected output: " + userCommandOutput + " instead of: " + expectedOutput);
        }
    }

}
