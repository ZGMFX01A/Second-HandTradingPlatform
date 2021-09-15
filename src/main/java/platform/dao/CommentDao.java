package platform.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import platform.entity.Comment;

import javax.transaction.Transactional;
import java.util.List;


public interface CommentDao extends JpaRepository<Comment, Integer> {

    /**
     * 根据产品ID查找用户评论
     * @param pid
     * @return
     */
    List<Comment> findByProductId(Integer pid);


    /**
     * 添加评论
     * @param com
     * @param pid
     * @param userid
     */
    @Modifying
    @Transactional
    @Query(value = "insert t_comment (product_id,user_id,comment) values(?1,?2,?3)",nativeQuery = true)
    void writeComment(Integer pid,Integer userid,String com);
}
