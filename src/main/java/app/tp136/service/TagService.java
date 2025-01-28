package app.tp136.service;

import app.tp136.dto.TagDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {
    TagDto get(Long id);

    Page<TagDto> getAll(Pageable pageable);

    TagDto save(TagDto dto);

    TagDto update(Long id, TagDto dto);

    void delete(Long id);
}
