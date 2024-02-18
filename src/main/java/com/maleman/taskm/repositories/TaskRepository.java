package com.maleman.taskm.repositories;

import com.maleman.taskm.dto.CountType;
import com.maleman.taskm.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "select * from TASK order by due_date asc", nativeQuery = true)
    public List<Task> getAllTaskDueDateDesc();

    @Query(value="Select new com.maleman.taskm.dto.CountType(COUNT(*)/(Select COUNT(*) from Task) *100,type) from Task GROUP BY type")
    public List<CountType> getPercentageGroupByType();


}
