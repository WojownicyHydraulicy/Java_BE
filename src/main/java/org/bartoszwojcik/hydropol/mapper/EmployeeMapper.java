package org.bartoszwojcik.hydropol.mapper;

import org.bartoszwojcik.hydropol.config.MapperConfig;
import org.bartoszwojcik.hydropol.dto.employee.EmployeeDto;
import org.bartoszwojcik.hydropol.model.classes.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for converting {@link Employee} entity to {@link EmployeeDto}.
 */
@Mapper(config = MapperConfig.class)
public interface EmployeeMapper {

    /**
     * Converts an {@link Employee} entity to an {@link EmployeeDto}.
     *
     * @param employee the employee entity to convert
     * @return the converted Employee DTO
     */
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "workerRole", source = "workerRole")
    EmployeeDto toDto(Employee employee);
}
