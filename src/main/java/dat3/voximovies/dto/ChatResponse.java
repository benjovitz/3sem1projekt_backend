package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatResponse {

    String chatMessage;

    public ChatResponse(String chatMessage) {
        this.chatMessage = chatMessage;
    }
}
