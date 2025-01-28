package app.tp136.service;

import app.tp136.dto.InspectionDto;
import app.tp136.dto.responce.InspectionResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface InspectionService {
    InspectionResponseDto get(Long id);

    Page<InspectionResponseDto> getAll(Pageable pageable);

    void basicAssessment(Authentication authentication, InspectionDto inspectionDto);

    void expertise(Authentication authentication, InspectionDto inspectionDto);
}
