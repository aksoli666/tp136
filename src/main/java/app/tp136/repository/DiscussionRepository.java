package app.tp136.repository;

import app.tp136.model.Discussion;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    @Query("SELECT d FROM Discussion d "
            + "JOIN FETCH d.comments c "
            + "JOIN FETCH d.topics t "
            + "WHERE d.id = :id AND d.isDeleted = false")
    Optional<Discussion> findById(Long id);

    @Query("SELECT d FROM Discussion d "
            + "JOIN FETCH d.topics t "
            + "WHERE t.id = :topicId "
            + "AND d.isDeleted = false")
    Page<Discussion> findAllByTopicId(Long topicId, Pageable pageable);

    @Query("SELECT d FROM Discussion d "
            + "JOIN FETCH d.comments c "
            + "JOIN FETCH d.topics t "
            + "WHERE d.isDeleted = false "
            + "ORDER BY d.published DESC")
    Page<Discussion> findAllPublishedDesc(Pageable pageable);

    @Query("SELECT d FROM Discussion d "
            + "JOIN FETCH d.comments c "
            + "JOIN FETCH d.topics t "
            + "WHERE d.isDeleted = false "
            + "ORDER BY d.countComments DESC")
    Page<Discussion> findAllByPopularity(Pageable pageable);

    @Query("SELECT d FROM Discussion d "
            + "JOIN FETCH d.topics t "
            + "WHERE t.id = :topicId "
            + "AND d.isDeleted = false")
    Page<Discussion> findAllByTopicId(Long topicId, Pageable pageable);

    @Query("SELECT d FROM Discussion d "
            + "JOIN FETCH d.comments c "
            + "JOIN FETCH d.topics t "
            + "WHERE d.isDeleted = false")
    Page<Discussion> findAll(Pageable pageable);
}
