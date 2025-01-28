package app.tp136.service.impl;

import app.tp136.dto.InspectionDto;
import app.tp136.dto.responce.InspectionResponseDto;
import app.tp136.exception.EntityNotFoundException;
import app.tp136.mapper.InspectionMapper;
import app.tp136.model.Inspection;
import app.tp136.model.User;
import app.tp136.repository.InspectionRepository;
import app.tp136.security.CustomUserDetailsService;
import app.tp136.service.InspectionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InspectionServiceImpl implements InspectionService {
    private static final Logger log = LoggerFactory.getLogger(InspectionServiceImpl.class);
    private final InspectionRepository inspectionRepository;
    private final InspectionMapper inspectionMapper;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public InspectionResponseDto get(Long id) {
        Inspection inspection = inspectionRepository.findById(id).orElseThrow(() -> {
            log.warn("Inspection not found with ID: {}", id);
            return new EntityNotFoundException("Can`t find inspection. Id: " + id);
        });
        return inspectionMapper.toResponseDto(inspection);
    }

    @Override
    public Page<InspectionResponseDto> getAll(Pageable pageable) {
        return inspectionMapper.toDtoPage(
                inspectionRepository.findAll(pageable));
    }

    @Transactional
    @Override
    public void basicAssessment(Authentication authentication,
                                InspectionDto dto) {
        createAndSaveInspection(authentication, dto, Inspection.Type.ASSESSMENT);
        log.info("Basic assessment successfully created for inspection: {}", dto);
    }

    @Transactional
    @Override
    public void expertise(Authentication authentication, InspectionDto dto) {
        createAndSaveInspection(authentication, dto, Inspection.Type.EXPERTISE);
        log.info("Expertise successfully created for inspection: {}", dto);
    }

    private void createAndSaveInspection(Authentication authentication,
                                         InspectionDto dto,
                                         Inspection.Type type) {
        User user = customUserDetailsService.getUserFromAuthentication(authentication);
        Inspection inspection = inspectionMapper.toInspection(dto);
        inspection.setUserEmail(user.getEmail());
        inspection.setType(type);
        inspectionRepository.save(inspection);
    }
}
