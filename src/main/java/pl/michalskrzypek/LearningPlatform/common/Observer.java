package pl.michalskrzypek.LearningPlatform.common;

import pl.michalskrzypek.LearningPlatform.entities.Course;

@FunctionalInterface
public interface Observer {

    void notify(Course course);
}
