package com.jp.module.user.repository;
import com.jp.module.user.entity.UsersVip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : xh
 * @date : Created in 2022/2/8 17:01
 */
@Repository
public interface UserVipRepository extends JpaRepository<UsersVip,Integer> {

    /**
     * 获取会员
     * @param userId
     * @return
     */
     UsersVip getUsersVipByUid(Integer userId);
}
