package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usage {
    @JsonProperty("prompt_tokens")
    public Integer promptTokens;
    @JsonProperty("completion_tokens")
    public Integer completionTokens;
    @JsonProperty("total_tokens")
    public Integer totalTokens;
}
