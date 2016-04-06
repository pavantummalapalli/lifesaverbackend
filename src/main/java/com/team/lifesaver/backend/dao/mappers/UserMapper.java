package com.team.lifesaver.backend.dao.mappers;

import com.team.lifesaver.backend.representations.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pavan.t on 11/03/16.
 */


public class UserMapper implements ResultSetMapper<User> {

    @Override
    public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        User user = new User();
        user.setUsername(r.getString("username"));
        user.setName(r.getString("name"));
        user.setEmail(r.getString("email"));
        user.setMobile(r.getString("mobile"));
        user.setDateOfBirth(r.getString("dob"));
        user.setAddress(r.getString("address"));
        user.setBloodgroup(r.getString("bloodgroup"));
        user.setGender(r.getString("gender"));
        user.setCity(r.getString("city"));
        user.setPincode(r.getInt("pincode"));
        user.setState(r.getString("state"));
        user.setAddress(r.getString("address"));
       return user;
    }
}
