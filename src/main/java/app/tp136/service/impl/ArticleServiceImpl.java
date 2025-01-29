package app.tp136.service.impl;

import app.tp136.dto.ArticleDto;
import app.tp136.dto.request.CreateArticleRequestDto;
import app.tp136.dto.request.UpdateArticleRequestDto;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.mapper.ArticleMapper;
import app.tp136.model.Article;
import app.tp136.model.Tag;
import app.tp136.repository.ArticleRepository;
import app.tp136.repository.TagRepository;
import app.tp136.service.ArticleService;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;
    private final ArticleMapper articleMapper;

    @Override
    public ArticleDto get(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can`t get article. Id: " + id));
        return articleMapper.toDto(article);
    }

    @Override
    public Page<ArticleDto> getAll(Pageable pageable) {
        return articleMapper.toDtoPage(articleRepository.findAll(pageable));
    }

    @Override
    public Page<ArticleDto> getAllByTagId(Long tagId, Pageable pageable) {
        return articleMapper.toDtoPage(
                articleRepository.findAllByTagId(tagId, pageable));
    }

    @Transactional
    @Override
    public ArticleDto save(CreateArticleRequestDto dto) {
        Article article = articleMapper.toArticle(dto);
        fetchTagsAndSetToArticle(dto.getTagIds(), article);
        article.setPublished(LocalDateTime.now());
        Article savedArticle = articleRepository.save(article);
        return articleMapper.toDto(savedArticle);
    }

    @Transactional
    @Override
    public ArticleDto update(Long id, UpdateArticleRequestDto dto) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't get article. Id: " + id));
        fetchTagsAndSetToArticle(dto.getTagIds(), article);
        articleMapper.updateArticle(dto, article);
        return articleMapper.toDto(articleRepository.save(article));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Can't delete article. Id: " + id);
        }
    }

    private void fetchTagsAndSetToArticle(Set<Long> tagIds, Article article) {
        Set<Tag> tags = tagRepository.findByIds(tagIds);
        article.setTags(tags);
    }
}
