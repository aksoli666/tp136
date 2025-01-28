package app.tp136.service;

import app.tp136.dto.ExhibitionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExhibitionService {
    ExhibitionDto get(Long id);

    Page<ExhibitionDto> getAll(Pageable pageable);

    ExhibitionDto save(ExhibitionDto dto);

    ExhibitionDto update(Long id, ExhibitionDto dto);

    void delete(Long id);
}
