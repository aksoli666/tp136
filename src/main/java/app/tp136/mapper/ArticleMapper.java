package app.tp136.mapper;

import app.tp136.config.MapperConfig;
import app.tp136.dto.ArticleDto;
import app.tp136.dto.request.CreateArticleRequestDto;
import app.tp136.dto.request.UpdateArticleRequestDto;
import app.tp136.model.Article;
import app.tp136.model.Tag;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(config = MapperConfig.class)
public interface ArticleMapper {
    ArticleDto toDto(Article article);

    Article toArticle(CreateArticleRequestDto dto);

    Article toArticle(ArticleDto dto);

    List<ArticleDto> toDtoList(List<Article> articles);

    void updateArticle(UpdateArticleRequestDto dto, @MappingTarget Article article);

    default Page<ArticleDto> toDtoPage(Page<Article> articles) {
        List<ArticleDto> dtos = toDtoList(articles.getContent());
        return new PageImpl<>(dtos, articles.getPageable(), articles.getTotalElements());
    }

    @AfterMapping
    default void setTagIds(@MappingTarget ArticleDto dto, Article article) {
        Set<Tag> tags = article.getTags();
        Set<Long> tagIds = new HashSet<>();
        for (Tag tag : tags) {
            tagIds.add(tag.getId());
        }
        dto.setTagIds(tagIds);
    }
}
