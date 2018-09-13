package com.ssi.mvc.domains.user;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/1/31
 * Time:14:14
*/



import com.ssi.mvc.domains.user.entity.User;

import java.util.List;

public interface UserService {

    /**
     *
     * @return
     */
    List<com.ssi.mvc.domains.user.entity.User> getAllUsers();

    /**
     *
     * @param id
     * @return
     */
    List<com.ssi.mvc.domains.user.entity.User> getUserById(String id);

    /**
     *
     * @param user
     * @return
     */
    int insert(User user);

    /**
     *
     * @param user
     * @return
     */
    int update(User user);

    /**
     *
     * @param id
     * @return
     */
    int delete(String id);

    /**
     *
     * @param ids
     * @return
     */
    int deleteIds(String[] ids);
}
