package pl.kanthak.timelineapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kanthak.timelineapp.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
