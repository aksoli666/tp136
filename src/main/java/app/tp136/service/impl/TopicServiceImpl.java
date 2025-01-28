package app.tp136.service.impl;

import app.tp136.dto.TopicDto;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.mapper.TopicMapper;
import app.tp136.model.Topic;
import app.tp136.repository.TopicRepository;
import app.tp136.service.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Override
    public TopicDto get(Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> {
            log.warn("Topic not found with ID: {}", id);
            return new EntityNotFoundException("Can`t find topic. Id: " + id);
        });
        return topicMapper.toTopicDto(topic);
    }

    @Override
    public Page<TopicDto> getAll(Pageable pageable) {
        return topicMapper.toTopicDtoPage(
                topicRepository.findAll(pageable));
    }

    @Transactional
    @Override
    public TopicDto save(TopicDto dto) {
        Topic topic = topicMapper.toTopic(dto);
        Topic savedTopic = topicRepository.save(topic);
        log.info("Successfully saved topic with ID: {}", savedTopic.getId());
        return topicMapper.toTopicDto(savedTopic);
    }

    @Transactional
    @Override
    public TopicDto update(Long id, TopicDto dto) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> {
            log.warn("Topic not found with ID: {}", id);
            return new EntityNotFoundException("Can`t find topic. Id: " + id);
        });
        topicMapper.updateTopic(dto, topic);
        return topicMapper.toTopicDto(topicRepository.save(topic));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (topicRepository.existsById(id)) {
            topicRepository.deleteById(id);
            log.info("Successfully deleted topic with ID: {}", id);
        } else {
            log.warn("Attempted to delete non-existing topic with ID: {}", id);
            throw new EntityNotFoundException("Can`t delete topic. Id: " + id);
        }
    }
}
