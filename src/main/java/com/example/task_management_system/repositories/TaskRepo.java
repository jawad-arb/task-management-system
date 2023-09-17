package com.example.task_management_system.repositories;

import com.example.task_management_system.dto.CountType;
import com.example.task_management_system.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Long> {

    @Query(value = "SELECT * FROM task order by due_date desc",nativeQuery = true)
    List<Task> getAllTaskDueDateDesc();


    @Query(value="Select new com.sam.dto.CountType(COUNT(*)/(Select COUNT(*) from Task) *100,type) from Task GROUP BY type")
    List<CountType> getPercentageGroupByType();
}
