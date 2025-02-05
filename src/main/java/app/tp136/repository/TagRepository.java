package app.tp136.repository;

import app.tp136.model.Tag;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("SELECT t FROM Tag t WHERE t.id IN :ids AND t.isDeleted = false")
    Set<Tag> findByIds(Set<Long> ids);
}
