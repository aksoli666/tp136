package app.tp136.repository;

import app.tp136.model.Exhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
    @Query("SELECT e FROM Exhibition e "
            + "WHERE e.event = :event AND e.isDeleted = false")
    Page<Exhibition> findAllByEvent(Exhibition.Event event, Pageable pageable);
}
