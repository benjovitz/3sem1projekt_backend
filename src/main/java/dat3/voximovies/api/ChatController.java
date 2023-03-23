package dat3.voximovies.api;

import dat3.voximovies.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/chat/")
public class ChatController {

    ChatService chatService;

    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

}
