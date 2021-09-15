package platform.service;
import platform.entity.Comment;
import java.util.List;

public interface CommentService {

    /**
     * 评论列表
     * @param productId
     * @return
     */
    List<Comment> findComments(Integer productId);

    void write(Integer pid, Integer userid, String com);

}
