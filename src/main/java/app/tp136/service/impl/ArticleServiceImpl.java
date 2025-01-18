package app.tp136.service.impl;

import app.tp136.dto.ArticleDto;
import app.tp136.dto.request.CreateArticleRequestDto;
import app.tp136.dto.request.UpdateArticleRequestDto;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.mapper.ArticleMapper;
import app.tp136.model.Article;
import app.tp136.repository.ArticleRepository;
import app.tp136.service.ArticleService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public ArticleDto get(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can`t get article. Id: " + id));
        return articleMapper.toDto(article);
    }

    @Override
    public Page<ArticleDto> getAll(Pageable pageable) {
        return articleMapper.toDtoPage(articleRepository.findAll(pageable));
    }

    @Override
    public ArticleDto save(CreateArticleRequestDto dto) {
        Article article = articleMapper.toArticle(dto);
        article.setPublished(LocalDateTime.now());
        return articleMapper.toDto(articleRepository.save(article));
    }

    @Override
    public ArticleDto update(Long id, UpdateArticleRequestDto dto) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can`t get article. Id: " + id));
        articleMapper.updateArticle(dto, article);
        return articleMapper.toDto(articleRepository.save(article));
    }

    @Override
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}
