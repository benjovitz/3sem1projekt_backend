package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ChatRobotDTO {
    @JsonProperty("id")
    public String id;
    @JsonProperty("object")
    public String object;
    @JsonProperty("created")
    public Integer created;
    @JsonProperty("model")
    public String model;
    @JsonProperty("choices")
    public List<ChoiceDTO> choices;
    //@JsonIgnore
    @JsonProperty("usage")
    public Usage usage;
}
