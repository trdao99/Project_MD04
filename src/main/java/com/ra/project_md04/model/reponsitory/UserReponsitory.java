package com.ra.project_md04.model.reponsitory;


import com.ra.project_md04.model.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
@Repository
public interface UserReponsitory extends JpaRepository<Users, Integer>, PagingAndSortingRepository<Users, Integer> {
    Optional<Users> findUserByUsername(String userName);
    List<Users> findUsersByFullName(String userName);
    Users findUserById(int id);
    @Query("select s from Users s where s.fullName like concat('%',:fullName,'%')")
    Page<Users> findUsersByFullNameAndSorting(String fullName, Pageable pageable);
}
