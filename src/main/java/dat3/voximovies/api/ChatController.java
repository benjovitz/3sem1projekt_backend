package dat3.voximovies.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import dat3.voximovies.dto.ChatRequest;
import dat3.voximovies.dto.ChatResponse;
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
    @PostMapping
    ChatResponse getChatResponse(@RequestBody ChatRequest request) throws JsonProcessingException {
        return chatService.chatAnswer(request);
    }
}
