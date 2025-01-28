package app.tp136.repository;

import app.tp136.model.Article;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @EntityGraph(attributePaths = "tags")
    Optional<Article> findById(Long id);

    @EntityGraph(attributePaths = "tags")
    Page<Article> findAll(Pageable pageable);

    @Query("SELECT a FROM Article a "
            + "JOIN FETCH a.tags t "
            + "WHERE t.id = :tagId AND a.isDeleted = false AND t.isDeleted = false")
    Page<Article> findAllByTagId(Long tagId, Pageable pageable);
}
