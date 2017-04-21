package com.ssj.manage.biz.user.repositories;

import com.slyak.spring.jpa.GenericJpaRepository;
import com.slyak.spring.jpa.TemplateQuery;
import com.ssj.manage.biz.user.model.UserBean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends GenericJpaRepository<UserBean, String> {

    @TemplateQuery
    UserBean queryUserById(@Param("userId") String userId, @Param("password") String password);

    @Query(nativeQuery = true, value = "SELECT * FROM t_user WHERE userId=?1")
    UserBean queryById(String id);

}
