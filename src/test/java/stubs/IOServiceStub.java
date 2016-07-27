package stubs;

import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.service.IOService;

import java.io.IOException;

/**
 * Created by jcharlet on 19/07/16.
 */
public class IOServiceStub implements IOService {

    @Override
    public TodoList importTodoListFromFile(String filePath) throws IOException {
        return null;
    }
}
