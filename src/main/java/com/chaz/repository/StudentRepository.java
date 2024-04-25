package com.chaz.repository;

import com.chaz.domain.Student;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Student entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select student from Student student where student.user.login = ?#{authentication.name}")
    List<Student> findByUserIsCurrentUser();
}
