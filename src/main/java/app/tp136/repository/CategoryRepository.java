package app.tp136.repository;

import app.tp136.model.Category;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.id IN :ids")
    Set<Category> findByIds(Set<Long> ids);
}
