package dat3.voximovies.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dat3.voximovies.api_facade.ChatRobotFacade;
import dat3.voximovies.dto.ChatRobotDTO;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public ChatRobotFacade chatRobotFacade;

    public ChatService() {
        this.chatRobotFacade = new ChatRobotFacade();
    }

    public String chatAnswer(String q) throws JsonProcessingException {
        return chatRobotFacade.chatHelp(q,2000);
    }
}
