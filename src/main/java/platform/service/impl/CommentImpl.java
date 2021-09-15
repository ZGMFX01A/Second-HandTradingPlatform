package platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.dao.CommentDao;
import platform.entity.Comment;
import platform.entity.User;
import platform.service.CommentService;
import platform.service.UserService;

import java.util.ArrayList;
import java.util.List;
@Service
public class CommentImpl implements CommentService {
    @Autowired
    CommentDao commentDao;
    @Autowired
    UserService userService;

    @Override
    public List<Comment> findComments(Integer productId) {
        List<Comment> commentList=commentDao.findByProductId(productId);
        List<Comment> comments=new ArrayList<>();
        for (Comment comment:commentList){
            Comment com=new Comment();
            com.setComment(comment.getComment());
            User user=userService.findById(comment.getUserId());
            //com.setUser(user);
            com.setUsername(user.getUsername());
          //comment.getUser().setUsername(user.getUsername());
          //comment.setUser(userService.findById(comment.getUserId()));
            comments.add(com);
        }
        return comments;
    }

    @Override
    public void write(Integer pid, Integer userid, String com) {
         commentDao.writeComment(pid,userid,com);
    }
}
