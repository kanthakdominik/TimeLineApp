package pl.kanthak.timelineapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kanthak.timelineapp.model.Cover;

@Repository
public interface CoverRepository extends JpaRepository<Cover, Long> {
}
