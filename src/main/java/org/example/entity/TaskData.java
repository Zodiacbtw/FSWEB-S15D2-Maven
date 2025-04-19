package org.example.entity;
import java.util.*;

public class TaskData {
    private Set<Task> annsTasks;
    private Set<Task> bobsTasks;
    private Set<Task> carolsTasks;
    private Set<Task> unassignedTasks;

    public TaskData(Set<Task> annsTasks, Set<Task> bobsTasks, Set<Task> carolsTasks, Set<Task> unassignedTasks) {
        this.annsTasks = annsTasks;
        this.bobsTasks = bobsTasks;
        this.carolsTasks = carolsTasks;
        this.unassignedTasks = unassignedTasks;
    }


    public Set<Task> getTasks(String person) {
        switch (person.toLowerCase()) {
            case "ann": return annsTasks;
            case "bob": return bobsTasks;
            case "carol": return carolsTasks;
            case "all":
                return getUnion(annsTasks, bobsTasks, carolsTasks); // Burada set'leri doğrudan gönderiyoruz
            default: return Collections.emptySet();
        }
    }

    public Set<Task> getUnassignedTasks() {
        return unassignedTasks;
    }

    public Set<Task> getUnion(Set<Task>... sets) {
        Set<Task> result = new HashSet<>();
        for (Set<Task> set : sets) {
            result.addAll(set);
        }
        return result;
    }

    public Set<Task> getIntersection(Set<Task> set1, Set<Task> set2) {
        Set<Task> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    public Set<Task> getDifferences(Set<Task> set1, Set<Task> set2) {
        Set<Task> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }

    public Set<Task> getTasksAssignedToMultiplePeople() {
        Set<Task> result = new HashSet<>();
        Set<Task> annAndBob = getIntersection(annsTasks, bobsTasks);
        Set<Task> annAndCarol = getIntersection(annsTasks, carolsTasks);
        Set<Task> bobAndCarol = getIntersection(bobsTasks, carolsTasks);

        result.addAll(annAndBob);
        result.addAll(annAndCarol);
        result.addAll(bobAndCarol);

        return result;
    }

}
