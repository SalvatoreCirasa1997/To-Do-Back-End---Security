package colloquio.coll.controller;

import colloquio.coll.controller.api.TestApi;
import colloquio.coll.dto.TestDTO;
import colloquio.coll.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController implements TestApi {

    private final TestService testService;

    @Override
    public ResponseEntity<List<TestDTO>> getAll() {
        return ResponseEntity.ok(testService.getAll());
    }

    @Override
    public ResponseEntity<TestDTO> getById(@PathVariable(name = "testId") Long testId) {
        return ResponseEntity.ok(testService.getById(testId));
    }

    @Override
    public ResponseEntity<TestDTO> create(TestDTO testDTO) {
        return ResponseEntity.ok(testService.create(testDTO));
    }

    @Override
    public ResponseEntity<TestDTO> update(@PathVariable(name = "testId") Long testId, TestDTO testDTO) {
        return ResponseEntity.ok(testService.update(testId, testDTO));
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable(name = "testId") Long testId) {
        testService.delete(testId);
        return ResponseEntity.ok().build();
    }
}
