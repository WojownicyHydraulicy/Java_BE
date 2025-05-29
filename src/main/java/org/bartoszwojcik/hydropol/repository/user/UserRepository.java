package org.bartoszwojcik.hydropol.repository.user;

import java.util.List;
import java.util.Optional;
import org.bartoszwojcik.hydropol.model.classes.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>,
        JpaSpecificationExecutor<User> {

    @EntityGraph(attributePaths = {"role"})
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);

    List<User> findByUsername(String username, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.citiesId.id = :cityId WHERE u.id = :id")
    int updateCity(Long id, Long cityId);
}
