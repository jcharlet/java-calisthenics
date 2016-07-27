package stubs;

import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.CommunicationService;

/**
 * Created by jcharlet on 19/07/16.
 */
public class CommunicationServiceStub implements CommunicationService {

    public String stubInputMessage ="";
    public String stubOutputMessage="";

    @Override
    public void tellUser(Message message) {
        this.stubOutputMessage = message.text;
    }

    @Override
    public Message getUserInput() {
        return new Message(stubInputMessage);
    }

    @Override
    public void writeNewLineToConsole() {

    }
}
