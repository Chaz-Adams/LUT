package com.chaz.repository;

import com.chaz.domain.Classroom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class ClassroomRepositoryWithBagRelationshipsImpl implements ClassroomRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String CLASSROOMS_PARAMETER = "classrooms";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Classroom> fetchBagRelationships(Optional<Classroom> classroom) {
        return classroom.map(this::fetchStudents);
    }

    @Override
    public Page<Classroom> fetchBagRelationships(Page<Classroom> classrooms) {
        return new PageImpl<>(fetchBagRelationships(classrooms.getContent()), classrooms.getPageable(), classrooms.getTotalElements());
    }

    @Override
    public List<Classroom> fetchBagRelationships(List<Classroom> classrooms) {
        return Optional.of(classrooms).map(this::fetchStudents).orElse(Collections.emptyList());
    }

    Classroom fetchStudents(Classroom result) {
        return entityManager
            .createQuery(
                "select classroom from Classroom classroom left join fetch classroom.students where classroom.id = :id",
                Classroom.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Classroom> fetchStudents(List<Classroom> classrooms) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, classrooms.size()).forEach(index -> order.put(classrooms.get(index).getId(), index));
        List<Classroom> result = entityManager
            .createQuery(
                "select classroom from Classroom classroom left join fetch classroom.students where classroom in :classrooms",
                Classroom.class
            )
            .setParameter(CLASSROOMS_PARAMETER, classrooms)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
