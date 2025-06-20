package org.bartoszwojcik.hydropol.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.NullValueCheckStrategy;

/**
 * Central MapStruct configuration for all mapper interfaces in the application.
 * <p>
 * This configuration ensures that:
 * <ul>
 *   <li>Mappers use Spring's dependency injection model ({@code componentModel = "spring"})</li>
 *   <li>Constructor injection is used instead of field or setter injection</li>
 *   <li>Null values are always checked before mapping to avoid NullPointerExceptions</li>
 *   <li>Generated implementations are placed in a specific implementation package</li>
 * </ul>
 * </p>
 *
 * <p>
 * To apply this configuration, annotate a mapper interface with:
 * {@code @Mapper(config = MapperConfig.class)}
 * </p>
 *
 * @see org.mapstruct.Mapper
 * @see InjectionStrategy
 * @see NullValueCheckStrategy
 */
@org.mapstruct.MapperConfig(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl"
)
public interface MapperConfig {
}
