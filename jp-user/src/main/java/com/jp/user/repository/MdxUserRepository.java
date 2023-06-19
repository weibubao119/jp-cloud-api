package com.jp.user.repository;
import com.jp.user.entity.MdxUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : xh
 * @date : Created in 2022/2/8 17:01
 */
@Repository
public interface MdxUserRepository extends JpaRepository<MdxUser,Long> {

    /**
     * 获取用户信息
     * @param userName
     * @return
     */
    MdxUser findByUserName(String userName);
}
