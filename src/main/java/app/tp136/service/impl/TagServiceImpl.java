package app.tp136.service.impl;

import app.tp136.dto.TagDto;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.mapper.TagMapper;
import app.tp136.model.Tag;
import app.tp136.repository.TagRepository;
import app.tp136.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public TagDto get(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can`t get tag. Id: " + id));
        return tagMapper.toDto(tag);
    }

    @Override
    public Page<TagDto> getAll(Pageable pageable) {
        return tagMapper.toTagDtoPage(
                tagRepository.findAll(pageable));
    }

    @Transactional
    @Override
    public TagDto save(TagDto dto) {
        Tag tag = tagMapper.toTag(dto);
        Tag savedTag = tagRepository.save(tag);
        return tagMapper.toDto(savedTag);
    }

    @Transactional
    @Override
    public TagDto update(Long id, TagDto dto) {
        Tag tag = tagRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can`t get tag. Id: " + id));
        tagMapper.updateTag(dto, tag);
        return tagMapper.toDto(tagRepository.save(tag));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Can`t delete tag. Id: " + id);
        }
    }
}
