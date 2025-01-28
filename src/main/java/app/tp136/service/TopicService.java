package app.tp136.service;

import app.tp136.dto.TopicDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicService {
    TopicDto get(Long id);

    Page<TopicDto> getAll(Pageable pageable);

    TopicDto save(TopicDto dto);

    TopicDto update(Long id, TopicDto dto);

    void delete(Long id);
}
