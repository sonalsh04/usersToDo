package com.usersToDo.repo;

import com.usersToDo.model.ToDoItm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToDoRepo extends JpaRepository<ToDoItm,String> {

    @Query(value="select * from tasks t where t.uid = :userid",nativeQuery = true)
    List<ToDoItm> findAllTasksByUser(@Param("userid")String uid);

    @Query(value="select * from tasks t where t.id = :taskid and t.uid = :userid",nativeQuery = true)
    ToDoItm findUserTaskById(@Param("taskid")String tid, @Param("userid")String uid);

    @Query(value="select * from tasks t where t.id = :taskid",nativeQuery = true)
    ToDoItm findTaskById(@Param("taskid")String tid);


}
