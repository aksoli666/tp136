package app.tp136.service;

import app.tp136.dto.ArticleDto;
import app.tp136.dto.request.CreateArticleRequestDto;
import app.tp136.dto.request.UpdateArticleRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {
    ArticleDto get(Long id);

    Page<ArticleDto> getAll(Pageable pageable);

    ArticleDto save(CreateArticleRequestDto dto);

    ArticleDto update(Long id, UpdateArticleRequestDto dto);

    void delete(Long id);
}
