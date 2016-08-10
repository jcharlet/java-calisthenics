package stubs;

import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.CommunicationService;

import java.util.*;

/**
 * Created by jcharlet on 19/07/16.
 */
public class CommunicationServiceStub implements CommunicationService {

    public Deque<String> stubInputMessage = new LinkedList<>();
    public Deque<String> stubOutputMessage= new LinkedList<>();

    @Override
    public void tellUser(Message message) {
        this.stubOutputMessage.add(message.text);
    }

    @Override
    public Message getUserInput() {
        return new Message(stubInputMessage.poll());
    }

    @Override
    public void writeNewLineToConsole() {

    }
}
