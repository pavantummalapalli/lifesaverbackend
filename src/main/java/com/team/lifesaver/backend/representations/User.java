package com.team.lifesaver.backend.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by pavan.t on 10/03/16.
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @JsonProperty
    private String name;
    @JsonProperty
    private String username;
    @JsonProperty
    private String password;
    @JsonProperty
    private String gender;
    @JsonProperty
    private String dateOfBirth;
    @JsonProperty
    private String bloodgroup;
    @JsonProperty
    private String mobile;
    @JsonProperty
    private String email;
    @JsonProperty
    private String state;
    @JsonProperty
    private String city;
    @JsonProperty
    private int pincode;
    @JsonProperty
    private String address;
    @JsonProperty
    private double lang;
    @JsonProperty
    private double lat;

}
