package com.qsp.UserLoginLogout.Repository;

import com.qsp.UserLoginLogout.Entity.Visitlogs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitlogsRepository extends JpaRepository<Visitlogs, Integer> {
  List<Visitlogs> findByUserId(Integer userId);

}