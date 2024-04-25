package com.chaz.domain;

import static com.chaz.domain.ClassroomTestSamples.*;
import static com.chaz.domain.StudentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.chaz.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ClassroomTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Classroom.class);
        Classroom classroom1 = getClassroomSample1();
        Classroom classroom2 = new Classroom();
        assertThat(classroom1).isNotEqualTo(classroom2);

        classroom2.setId(classroom1.getId());
        assertThat(classroom1).isEqualTo(classroom2);

        classroom2 = getClassroomSample2();
        assertThat(classroom1).isNotEqualTo(classroom2);
    }

    @Test
    void studentsTest() throws Exception {
        Classroom classroom = getClassroomRandomSampleGenerator();
        Student studentBack = getStudentRandomSampleGenerator();

        classroom.addStudents(studentBack);
        assertThat(classroom.getStudents()).containsOnly(studentBack);

        classroom.removeStudents(studentBack);
        assertThat(classroom.getStudents()).doesNotContain(studentBack);

        classroom.students(new HashSet<>(Set.of(studentBack)));
        assertThat(classroom.getStudents()).containsOnly(studentBack);

        classroom.setStudents(new HashSet<>());
        assertThat(classroom.getStudents()).doesNotContain(studentBack);
    }
}
