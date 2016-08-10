package calisthenics.todolist.dao.impl;

import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.model.Task;
import calisthenics.todolist.model.TodoList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by jcharlet on 28/07/16.
 */
public class FileTodoListDaoImpl implements TodoListDao {

    static private String filePath = "todolist.txt";

    @Override
    public TodoList get() {
        final List<String> fileLines;
        try {
            fileLines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TodoList importedTodoList = new TodoList();
        for (String line : fileLines) {
            final String[] fields = line.split(";");
            if(fields.length==1){
                importedTodoList.addTask(new Task(fields[0]));
            }
            if(fields.length==2){
                importedTodoList.addTask(new Task(fields[0],Boolean.valueOf(fields[1])));
            }
        }
        return importedTodoList;
    }

    @Override
    public void save(TodoList todoList) {
        try {
            File file = Paths.get(filePath).toFile();
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : todoList.getTasks()) {
                writer.write(task.getName());
                writer.write(";");
                writer.write(String.valueOf(task.isDone()));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
