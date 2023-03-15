package com.nader.repository;

import com.nader.dto.CountType;
import com.nader.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "select * from task order by due_date desc",nativeQuery = true)
    public List<Task> getAllTaskDueDateDesc();

    @Query(value = "select new com.nader.dto.CountType(COUNT(*)/ (select COUNT(*) from Task) * 100 , type) from Task GROUP BY type ")
    public List<CountType> getPercentageGroupByType();

}
