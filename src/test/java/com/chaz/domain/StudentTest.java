package com.chaz.domain;

import static com.chaz.domain.ClassroomTestSamples.*;
import static com.chaz.domain.StudentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.chaz.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Student.class);
        Student student1 = getStudentSample1();
        Student student2 = new Student();
        assertThat(student1).isNotEqualTo(student2);

        student2.setId(student1.getId());
        assertThat(student1).isEqualTo(student2);

        student2 = getStudentSample2();
        assertThat(student1).isNotEqualTo(student2);
    }

    @Test
    void classroomsTest() throws Exception {
        Student student = getStudentRandomSampleGenerator();
        Classroom classroomBack = getClassroomRandomSampleGenerator();

        student.addClassrooms(classroomBack);
        assertThat(student.getClassrooms()).containsOnly(classroomBack);
        assertThat(classroomBack.getStudents()).containsOnly(student);

        student.removeClassrooms(classroomBack);
        assertThat(student.getClassrooms()).doesNotContain(classroomBack);
        assertThat(classroomBack.getStudents()).doesNotContain(student);

        student.classrooms(new HashSet<>(Set.of(classroomBack)));
        assertThat(student.getClassrooms()).containsOnly(classroomBack);
        assertThat(classroomBack.getStudents()).containsOnly(student);

        student.setClassrooms(new HashSet<>());
        assertThat(student.getClassrooms()).doesNotContain(classroomBack);
        assertThat(classroomBack.getStudents()).doesNotContain(student);
    }
}
