package app.tp136.repository;

import app.tp136.model.Topic;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Query("SELECT t FROM Topic t WHERE t.id IN :ids")
    Set<Topic> findByIds(Set<Long> ids);
}
