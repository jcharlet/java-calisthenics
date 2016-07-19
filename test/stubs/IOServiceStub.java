package stubs;

import calisthenics.todolist.model.IOMessage;
import calisthenics.todolist.service.IOService;

/**
 * Created by jcharlet on 19/07/16.
 */
public class IOServiceStub implements IOService{

    public String stubMessage ="";

    @Override
    public void writeToConsole(IOMessage message) {

    }

    @Override
    public IOMessage readFromConsole() {
        return new IOMessage(stubMessage);
    }

    @Override
    public void writeNewLineToConsole() {

    }
}
