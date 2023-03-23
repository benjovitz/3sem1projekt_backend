package dat3.voximovies.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ShowingResponse {

  @Nonnull
  int MovieId;

  @Nonnull
  int CinemaId;

  @Nonnull
  double price;

  @Nonnull
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  LocalDateTime localDateTime;


}
