package com.kanban.userAuthService.repository;

import com.kanban.userAuthService.domain.KanbanUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<KanbanUser,String> {
}
