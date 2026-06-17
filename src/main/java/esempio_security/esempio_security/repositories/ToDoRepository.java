package esempio_security.esempio_security.repositories;

import esempio_security.esempio_security.models.ToDoModel;
import esempio_security.esempio_security.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoModel, Long> {

    void deleteByIdAndUserModel(Long id, UserModel userModel);

    Optional<ToDoModel> getToDoByIdAndUserModel(Long id, UserModel userModel);

    List<ToDoModel> getAllByUserModel(UserModel userModel);

    Page<ToDoModel> getAllByUserModel(UserModel userModel, Pageable pageable);

}
