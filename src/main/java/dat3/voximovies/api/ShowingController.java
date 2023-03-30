package dat3.voximovies.api;

import dat3.voximovies.dto.ShowingRequest;
import dat3.voximovies.dto.ShowingResponse;
import dat3.voximovies.entity.Showing;
import dat3.voximovies.service.ShowingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/showings/")
public class ShowingController {

  ShowingService showingService;

  public ShowingController(ShowingService showingService){
    this.showingService=showingService;
  }
  //All
  @GetMapping("{id}")
  public ShowingResponse getShowById(@PathVariable long id){
    return showingService.getShowingById(id);
  }

  @PreAuthorize("hasAuthority('USER')")
  @GetMapping("movie/{movieId}")
  public List<ShowingResponse> getAllShowingsOfMovie(@PathVariable long movieId){
    return showingService.getAllShowingsWithMovieId(movieId);
  }

  @PreAuthorize("hasAuthority('USER') or hasAuthority('CINEMATICER')")
  @GetMapping("cinema/{cinemaId}")
  public List<ShowingResponse> getAllCinemaShows(@PathVariable long cinemaId){
    return showingService.getAllShowingsForCinema(cinemaId);
  }

  @PreAuthorize("hasAuthority('USER')")
  @GetMapping("user")
  public List<ShowingResponse> getAllCurrentShows(){
    return showingService.getAllShowings();
  }

  @PreAuthorize("hasAuthority('CINEMATICER')")
  @GetMapping("owner")
  public List<ShowingResponse> getAllOwnerShows(Principal p){
    return showingService.getAllShowingsOfOwner(p.getName());
  }

  @PreAuthorize("hasAuthority('CINEMATICER')")
  @PostMapping
  public ShowingResponse createShowing(Principal p, @RequestBody ShowingRequest body){
    return showingService.createShowing(p.getName(),body);
  }

  @PreAuthorize("hasAuthority('CINEMATICER')")
  @PutMapping("{showId}")
  public ShowingResponse updateShowing(Principal p, @RequestBody ShowingRequest body, @PathVariable int showId){
    return showingService.updateShowing(p.getName(),body, showId);
  }

  @PreAuthorize("hasAuthority('CINEMATICER')")
  @DeleteMapping("{showId}")
  public void deleteShowing(Principal p, @PathVariable long showId){
    showingService.deleteShowing(p.getName(),showId);
  }

}
