package dat3.voximovies.service;

import dat3.voximovies.repository.ShowingRepository;
import org.springframework.stereotype.Service;


@Service
public class ShowingService {

  ShowingRepository showingRepository;

  public ShowingService(ShowingRepository showingRepository){
    this.showingRepository=showingRepository;
  }

}
