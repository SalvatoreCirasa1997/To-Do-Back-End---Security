package colloquio.coll.service;
import colloquio.coll.dto.TestDTO;
import java.util.List;

public interface TestService {

    List<TestDTO> getAll();

    TestDTO getById(Long testId);

    TestDTO create(TestDTO testDTO);

    TestDTO update(Long testId, TestDTO testDTO);

    void delete(Long testId);
}
