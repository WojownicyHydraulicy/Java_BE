package org.bartoszwojcik.hydropol.mapper;

import org.bartoszwojcik.hydropol.config.MapperConfig;
import org.bartoszwojcik.hydropol.dto.employee.EmployeeDto;
import org.bartoszwojcik.hydropol.model.classes.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface EmployeeMapper {
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "workerRole", source = "workerRole")
    EmployeeDto toDto(Employee employee);
}
