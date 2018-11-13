package com.ssi.mvc.domains.user;

/*
 * Created by Intellij IDEA
 * User:Jade.xiao
 * Date:2018/1/31
 * Time:14:14
*/



import com.ssi.mvc.domains.user.entity.User;

import java.util.List;

public interface ExtUserService {

    /**
     *
     * @return 返回的是一个User的集合
     */
    List<com.ssi.mvc.domains.user.entity.User> getAllUsers();

    /**
     *
     * @param id 根据User的id查询
     * @return 返回的是一个User的集合
     */
    List<com.ssi.mvc.domains.user.entity.User> getUserById(String id);

    /**
     *
     * @param user 一个User对象作为参数插入数据
     * @return 返回的int是插入的条数。用于判断是否插入成功。以及插入的数目
     */
    int insert(User user);

    /**
     *
     * @param user 一个User对象作为参数更新数据
     * @return 返回的int是更新的条数。用于判断是否更新成功。以及更新的数目
     */
    int update(User user);

    /**
     *
     * @param id 根据User的id删除
     * @return 返回的是删除的条数。
     */
    int delete(String id);

    /**
     *
     * @param ids ids传入的是一个id的字符串数组 然后进行删除
     * @return 返回的是删除的条数
     */
    int deleteIds(String[] ids);
}
