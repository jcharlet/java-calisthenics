import calisthenics.todolist.Main;
import calisthenics.todolist.Task;
import calisthenics.todolist.TodoList;

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
        Task[] tasks = new Task[1];
        tasks[0] = testTask;
        TodoList expectedTodoList = new TodoList(tasks);
        final String expectedOutput = expectedTodoList.toString();

        // when I ask to create a todo list
        final String userCommandOutput = main.executeUserCommand(Main.CREATE_TODO_LIST);

        // todo list is returned with a test task

        if (userCommandOutput.equals(expectedOutput)) {
            System.out.println("OK");
        } else {
            throw new IllegalStateException("createNewList: not the expected output: " + userCommandOutput + " instead of: " + expectedOutput);
        }
    }

}
