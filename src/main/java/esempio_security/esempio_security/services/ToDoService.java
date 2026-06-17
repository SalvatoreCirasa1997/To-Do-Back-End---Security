package esempio_security.esempio_security.services;

import esempio_security.esempio_security.dto.ToDoRequest;
import esempio_security.esempio_security.dto.ToDoRequestUpdate;
import esempio_security.esempio_security.dto.ToDoResponse;
import esempio_security.esempio_security.exc.ResourceNotFoundException;
import esempio_security.esempio_security.models.ToDoModel;
import esempio_security.esempio_security.models.UserModel;
import esempio_security.esempio_security.repositories.ToDoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final ModelMapper modelMapper;

    public ToDoService(ToDoRepository toDoRepository, ModelMapper modelMapper) {
        this.toDoRepository = toDoRepository;
        this.modelMapper = modelMapper;
    }

    public ToDoResponse addToDo(ToDoRequest toDo, UserModel user) {
        ToDoModel todo = modelMapper.map(toDo, ToDoModel.class);
        todo.setUserModel(user);
        ToDoModel added = this.toDoRepository.save(todo);
        return modelMapper.map(added, ToDoResponse.class);
    }

    public void deleteByIdAndUserModel(Long id, UserModel user) {
        this.toDoRepository.deleteByIdAndUserModel(id, user);
    }

    public List<ToDoResponse> getAllByUserModel(UserModel userModel) {
        List<ToDoModel> toDoModels = this.toDoRepository.getAllByUserModel(userModel);
        return toDoModels.stream()
                .map(todo -> modelMapper.map(todo, ToDoResponse.class))
                .toList();
    }

    public ToDoResponse getToDoByIdAndUserModel(Long id, UserModel userModel) {
        ToDoModel toDoModel = this.toDoRepository
                .getToDoByIdAndUserModel(id, userModel)
                .orElseThrow(() -> new ResourceNotFoundException("ToDo not found"));
        return modelMapper.map(toDoModel, ToDoResponse.class);
    }

    public ToDoResponse updateToDo(ToDoRequestUpdate toDo, UserModel userModel) {
        ToDoModel todo = this.toDoRepository
                .getToDoByIdAndUserModel(toDo.getId(), userModel)
                .orElseThrow(() -> new ResourceNotFoundException("ToDo not found"));

        todo.setTodo(toDo.getTodo());
        todo.setExpiryDate(toDo.getExpiryDate());
        todo.setDone(toDo.isDone());
        ToDoModel todoUpdated = this.toDoRepository.save(todo);
        return modelMapper.map(todoUpdated, ToDoResponse.class);
    }

    public Page<ToDoResponse> getAllByUserModel(UserModel userModel, Pageable pageable) {
        PageRequest pages = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<ToDoModel> todos = toDoRepository.getAllByUserModel(userModel, pages);
        return todos.map(todo -> modelMapper.map(todo, ToDoResponse.class));
    }
}
