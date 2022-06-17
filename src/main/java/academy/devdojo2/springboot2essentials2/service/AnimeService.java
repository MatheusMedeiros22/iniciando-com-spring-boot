package academy.devdojo2.springboot2essentials2.service;

import academy.devdojo2.springboot2essentials2.domain.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AnimeService {

    private static List<Anime> animes;

    static{
        animes = new ArrayList<>(List.of(new Anime(1L, "Berserk"), new Anime(2L, "Cavaleiros do Zodiaco")));
    }
    public List<Anime> listAll() {
        return animes;
    }

    public Anime findById(long id){
        return animes.stream()
                .filter(x -> Objects.equals(x.getId(), id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }


    public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(3 ,1000000));
        animes.add(anime);
        return anime;
    }

    public boolean delete(long id) {
        return animes.remove(findById(id));
    }
}
