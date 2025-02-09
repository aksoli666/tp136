package app.tp136.service.impl;

import app.tp136.dto.ExhibitionDto;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.mapper.ExhibitionMapper;
import app.tp136.model.Exhibition;
import app.tp136.repository.ExhibitionRepository;
import app.tp136.service.ExhibitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExhibitionsServiceImpl implements ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionMapper exhibitionMapper;

    @Override
    public ExhibitionDto get(Long id) {
        Exhibition exhibition = exhibitionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can`t find exhibition. Id: " + id));
        return exhibitionMapper.toDto(exhibition);
    }

    @Override
    public Page<ExhibitionDto> getAll(Pageable pageable) {
        return exhibitionMapper.toDtoPage(
                exhibitionRepository.findAll(pageable));
    }

    @Override
    public Page<ExhibitionDto> getExhibitionsByEvent(String eventName, Pageable pageable) {
        Exhibition.Event event = Exhibition.Event.valueOf(eventName.toUpperCase());
        return exhibitionMapper.toDtoPage(
                exhibitionRepository.findAllByEvent(event, pageable));
    }

    @Transactional
    @Override
    public ExhibitionDto save(ExhibitionDto dto) {
        Exhibition exhibition = exhibitionMapper.toExhibition(dto);
        Exhibition savedExhibition = exhibitionRepository.save(exhibition);
        return exhibitionMapper.toDto(savedExhibition);
    }

    @Transactional
    @Override
    public ExhibitionDto update(Long id, ExhibitionDto dto) {
        Exhibition exhibition = exhibitionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can`t find exhibition. Id: " + id));
        exhibitionMapper.updateExhibition(dto, exhibition);
        return exhibitionMapper.toDto(exhibitionRepository.save(exhibition));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (exhibitionRepository.existsById(id)) {
            exhibitionRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Can`t find exhibition. Id: " + id);
        }
    }
}
