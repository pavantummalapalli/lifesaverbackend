package com.team.lifesaver.backend.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team.lifesaver.backend.dao.mappers.UserMapper;
import com.team.lifesaver.backend.representations.User;
import com.team.lifesaver.backend.utils.LogSqlFactory;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import java.util.List;

/**
 * Created by pavan.t on 10/03/16.
 */


@UseStringTemplate3StatementLocator
@LogSqlFactory
public interface LifeSaverDAO {

    String userTable ="user";

    @SqlUpdate("insert into " +userTable+
              " (name ,username ,password,gender,dob,bloodgroup,mobile,email,state,city,pincode,address,lng,lat) "+
              " values(:user.name,:user.username,:user.password,:user.gender,:user.dateOfBirth,:user.bloodgroup,:user.mobile,:user.email,:user.state,:user.city,:user.pincode,:user.address,:user.lang,:user.lat);")
    void userSignUp(@BindBean("user")User user);

     @SqlQuery("select count(1) as count from "+userTable+ "  where username=:userName")
     String checkUserName(@Bind("userName") String userName);

    @SqlQuery("select count(1) as count from "+userTable+ "  where email=:email")
    String checkEmail(@Bind("email") String email);

    @SqlQuery("select count(1) as count from " +userTable+ " where username=:user.username and password=:user.password")
    int userLogin(@BindBean("user")User user);

    @RegisterMapper(UserMapper.class)
    @SqlQuery("select * from " +userTable+ " where city=:user.city and bloodgroup=:user.bloodgroup")
    List<User> getUsers(@BindBean("user")User user);

    @RegisterMapper(UserMapper.class)
    @SqlQuery("select * from " +userTable+ " where username=:username")
    User getUser(@Bind("username")String username);

    @SqlUpdate("update "+userTable+" set name=:user.name ,gender=:user.gender,dob=:user.dateOfBirth, bloodgroup=:user.bloodgroup,mobile=:user.mobile,email=:user.email,city=:user.city,pincode=:user.pincode,address=:user.address,lng=:user.lang,lat=:user.lat where username=:username")
    void updateUser(@BindBean("user")User user,@Bind("username")String username);


    void getUsersBasedOnGeocode(@Bind("lang")String lang,@Bind("lat")String lat);

}
