package dat3.voximovies.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dat3.voximovies.api_facade.ChatRobotFacade;
import dat3.voximovies.dto.ChatRequest;
import dat3.voximovies.dto.ChatResponse;
import dat3.voximovies.dto.ChatRobotDTO;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public ChatRobotFacade chatRobotFacade;

    public ChatService() {
        this.chatRobotFacade = new ChatRobotFacade();
    }

    public ChatResponse chatAnswer(ChatRequest cr) throws JsonProcessingException {
        ChatResponse chatResponse = new ChatResponse(chatRobotFacade.chatHelp(cr.getChatMessage(),2000,cr.getChatStarted()));
        return chatResponse;
    }
}
