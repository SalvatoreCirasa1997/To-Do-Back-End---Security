package colloquio.coll.controller.api;

import colloquio.coll.dto.TestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Test Controller", description = "API per la gestione dei test")
@RequestMapping("/test")
public interface TestApi {

    @Operation(
            summary = "Get all tests",
            description = "Retrieve a list of all tests"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tests"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/get-all")
    ResponseEntity<List<TestDTO>> getAll();

    @Operation(
            summary = "Get test by ID",
            description = "Retrieve a test by its ID"
    )
    @GetMapping("/get-by-id/{testId}")
    ResponseEntity<TestDTO> getById(@PathVariable(name = "testId") Long testId);

    @Operation(
            summary = "Create a new test",
            description = "Create a new test"
    )
    @PostMapping
    ResponseEntity<TestDTO> create(@RequestBody TestDTO testDTO);

    @Operation(
            summary = "Update an existing test",
            description = "Update an existing test"
    )
    @PutMapping("/{testId}")
    ResponseEntity<TestDTO> update(@PathVariable(name = "testId") Long testId, @RequestBody TestDTO testDTO);

    @Operation(
            summary = "Delete an existing test",
            description = "Delete an existing test"
    )
    @DeleteMapping("/{testId}")
    ResponseEntity<Void> delete(@PathVariable(name = "testId") Long testId);
}