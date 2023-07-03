package com.jp.module.user.repository;
import com.jp.module.user.entity.UsersAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : xh
 * @date : Created in 2022/2/8 17:01
 */
@Repository
public interface UserAvatarRepository extends JpaRepository<UsersAvatar,Integer> {

    /**
     * 获取头像
     * @param id
     * @return
     */
     UsersAvatar getUsersAvatarById(Integer id);
}
