package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ChoiceDTO {
    @JsonProperty("text")
    public String text;
    @JsonProperty("index")
    public Integer index;
    @JsonProperty("logprobs")
    public Object logprobs;
    @JsonProperty("finish_reason")
    public String finishReason;
}
