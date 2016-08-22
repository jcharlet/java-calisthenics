package stubs;

import calisthenics.todolist.dao.TodoListImportInterface;
import calisthenics.todolist.model.TodoList;

/**
 * Created by jcharlet on 19/07/16.
 */
public class TodoListImportInterfaceStub implements TodoListImportInterface {

    @Override
    public TodoList importFromFile(String filePath) {
        return null;
    }
}
