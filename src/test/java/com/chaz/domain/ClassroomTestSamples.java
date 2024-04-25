package com.chaz.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ClassroomTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Classroom getClassroomSample1() {
        return new Classroom().id(1L).classroomId(1L).className("className1");
    }

    public static Classroom getClassroomSample2() {
        return new Classroom().id(2L).classroomId(2L).className("className2");
    }

    public static Classroom getClassroomRandomSampleGenerator() {
        return new Classroom()
            .id(longCount.incrementAndGet())
            .classroomId(longCount.incrementAndGet())
            .className(UUID.randomUUID().toString());
    }
}
