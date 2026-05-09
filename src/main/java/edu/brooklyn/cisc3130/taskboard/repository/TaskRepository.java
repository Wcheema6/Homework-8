package edu.brooklyn.cisc3130.taskboard.repository;

import edu.brooklyn.cisc3130.taskboard.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByCompletedTrue();

    List<Task> findByCompletedFalse();

    List<Task> findByPriority(Task.Priority priority);

    List<Task> findByTitleContainingIgnoreCase(String title);

    List<Task> findByCompletedAndPriority(Boolean completed, Task.Priority priority);

    List<Task> findByDeletedFalse();

    List<Task> findByDeletedTrue();

    @Query("SELECT t FROM Task t WHERE t.deleted = false")
    List<Task> findAllActive();

    @Query("SELECT t FROM Task t WHERE t.title LIKE %:keyword% OR t.description LIKE %:keyword%")
    List<Task> searchTasks(@Param("keyword") String keyword);

    Page<Task> findByCompletedTrue(Pageable pageable);

    long countByPriority(Task.Priority priority);
}