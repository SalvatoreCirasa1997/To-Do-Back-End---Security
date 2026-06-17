package colloquio.coll.service.impl;

import colloquio.coll.dto.TestDTO;
import colloquio.coll.entity.Test;
import colloquio.coll.exception.DuplicateResourceException;
import colloquio.coll.exception.ResourceNotFoundException;
import colloquio.coll.mapper.TestMapper;
import colloquio.coll.repository.TestRepository;
import colloquio.coll.service.TestService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final TestMapper testMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TestDTO> getAll() {
        return testRepository.findAll().stream()
                .map(testMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TestDTO getById(Long testId) {
        return testRepository.findById(testId)
                .map(testMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Test", testId));
    }

    @Override
    @Transactional
    public TestDTO create(TestDTO testDTO) {
        if (testRepository.existsByEmail(testDTO.getEmail())) {
            throw new DuplicateResourceException("Test", "email", testDTO.getEmail());
        }

        log.info("Creazione nuovo Test con email: {}", testDTO.getEmail());

        Test testEntity = testMapper.toEntity(testDTO);
        Test savedEntity = testRepository.save(testEntity);
        TestDTO savedDTO = testMapper.toDto(savedEntity);

        log.info("Creato Test con ID: {}", savedDTO.getId());
        return savedDTO;
    }

    @Override
    @Transactional
    public TestDTO update(Long testId, TestDTO testDTO) {
        // Prima recupero l'entità esistente
        var existingEntity = testRepository.findById(testId)
                .orElseThrow(() -> new ResourceNotFoundException("Test", testId));

        if (!existingEntity.getEmail().equals(testDTO.getEmail()) &&
                testRepository.existsByEmail(testDTO.getEmail())) {
            throw new DuplicateResourceException("Test", "email", testDTO.getEmail());
        }

        testMapper.updateEntity(testDTO, existingEntity);

        TestDTO updatedDTO = testMapper.toDto(testRepository.save(existingEntity));
        log.info("Aggiornato Test con ID: {}", testId);

        return updatedDTO;
    }

    @Override
    @Transactional
    public void delete(Long testId) {
        if (!testRepository.existsById(testId)) {
            throw new ResourceNotFoundException("Test", testId);
        }
        log.info("Eliminato Test con ID: {}", testId);
        testRepository.deleteById(testId);
    }
}