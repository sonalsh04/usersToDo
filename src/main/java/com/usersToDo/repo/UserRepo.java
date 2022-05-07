package com.usersToDo.repo;

import com.usersToDo.model.UserItm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<UserItm,String> {

    @Query(value="select * from user u where u.name = :name",nativeQuery = true)
    List<UserItm> findByUserName(@Param("name") String name);
}
