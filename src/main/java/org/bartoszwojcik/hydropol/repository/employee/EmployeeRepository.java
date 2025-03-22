package org.bartoszwojcik.hydropol.repository.employee;

import java.util.Optional;
import org.bartoszwojcik.hydropol.model.classes.Employee;
import org.bartoszwojcik.hydropol.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends CrudRepository<Employee, Long>,
        JpaSpecificationExecutor<Employee> {

    Optional<Employee> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.workerRole = :role WHERE e.user.id = :id")
    int updateByUserId(Long id, UserRole role);
}
