package com.chaz.repository;

import com.chaz.domain.Classroom;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ClassroomRepositoryWithBagRelationships {
    Optional<Classroom> fetchBagRelationships(Optional<Classroom> classroom);

    List<Classroom> fetchBagRelationships(List<Classroom> classrooms);

    Page<Classroom> fetchBagRelationships(Page<Classroom> classrooms);
}
