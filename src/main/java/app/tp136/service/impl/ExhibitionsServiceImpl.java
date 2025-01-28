package app.tp136.service.impl;

import app.tp136.dto.ExhibitionDto;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.mapper.ExhibitionMapper;
import app.tp136.model.Exhibition;
import app.tp136.repository.ExhibitionRepository;
import app.tp136.service.ExhibitionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExhibitionsServiceImpl implements ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionMapper exhibitionMapper;

    @Override
    public ExhibitionDto get(Long id) {
        Exhibition exhibition = exhibitionRepository.findById(id).orElseThrow(() -> {
            log.warn("Exhibition not found with ID: {}", id);
            return new EntityNotFoundException("Can`t find exhibition. Id: " + id);
        });
        return exhibitionMapper.toDto(exhibition);
    }

    @Override
    public Page<ExhibitionDto> getAll(Pageable pageable) {
        return exhibitionMapper.toDtoPage(
                exhibitionRepository.findAll(pageable));
    }

    @Transactional
    @Override
    public ExhibitionDto save(ExhibitionDto dto) {
        Exhibition exhibition = exhibitionMapper.toExhibition(dto);
        Exhibition savedExhibition = exhibitionRepository.save(exhibition);
        log.info("Successfully saved exhibition with ID: {}", savedExhibition.getId());
        return exhibitionMapper.toDto(savedExhibition);
    }

    @Transactional
    @Override
    public ExhibitionDto update(Long id, ExhibitionDto dto) {
        Exhibition exhibition = exhibitionRepository.findById(id).orElseThrow(() -> {
            log.warn("Exhibition not found with ID: {}", id);
            return new EntityNotFoundException("Can`t find exhibition. Id: " + id);
        });
        exhibitionMapper.updateExhibition(dto, exhibition);
        return exhibitionMapper.toDto(exhibitionRepository.save(exhibition));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (exhibitionRepository.existsById(id)) {
            exhibitionRepository.deleteById(id);
            log.info("Successfully deleted exhibition with ID: {}", id);
        } else {
            log.warn("Attempted to delete non-existing exhibition with ID: {}", id);
            throw new EntityNotFoundException("Can`t find exhibition. Id: " + id);
        }
    }
}
