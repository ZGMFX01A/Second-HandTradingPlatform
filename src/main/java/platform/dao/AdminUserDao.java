package platform.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.entity.AdminUser;

public interface AdminUserDao extends JpaRepository<AdminUser, Integer> {
    AdminUser findByUsernameAndPassword(String username, String pwd);
}
