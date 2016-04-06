package com.team.lifesaver.backend.resources;

import com.team.lifesaver.backend.dao.LifeSaverDAO;
import com.team.lifesaver.backend.representations.User;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pavan.t on 10/03/16.
 */

@Path("/services/lifesaver")
public class LifesaverResource {

    public LifesaverResource(LifeSaverDAO lifeSaverDAO) {
        this.lifeSaverDAO = lifeSaverDAO;
    }

    private LifeSaverDAO lifeSaverDAO;

    @Produces("application/json")
    @POST
    @Path("/login")
    @Consumes("application/json")
    public Response loginUser(User user){
        Map<String,String> resultMap = new LinkedHashMap<>();
        int check = lifeSaverDAO.userLogin(user);
        if(check ==1){
            resultMap.put("success","true");
            return Response.ok().header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET")
                    .entity(resultMap)
                    .build();
        }
        resultMap.put("error","invalid userName and password");
        return  Response.status(Response.Status.BAD_REQUEST)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET")
                .entity(resultMap)
                .build();
    }

    @Produces("application/json")
    @POST
    @Path("/signup")
    @Consumes("application/json")
    public Response userSignUp(User user){
        Map<String,String> resultMap = new LinkedHashMap<>();
        try{
            lifeSaverDAO.userSignUp(user);
            resultMap.put("success","true");
            return Response.ok().header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET")
                    .entity(resultMap)
                    .build();
        }catch (Exception e){
            resultMap.put("error","something went wrong please try again later");
            return  Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET")
                    .entity(resultMap)
                    .build();
        }

    }

    @Produces("application/json")
    @GET
    @Path("/checkusername/{username}")
    public Response checkUserName(@PathParam("username")String userName){
        Map<String,String> resultMap = new LinkedHashMap<>();
        String checkUserName = lifeSaverDAO.checkUserName(userName);
        int check = Integer.parseInt(checkUserName);
        try{
            if(check==1){
                resultMap.put("error","username already exists");
                return Response.status(Response.Status.CONFLICT).header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET")
                        .entity(resultMap)
                        .build();
            }else if(check==0){
                resultMap.put("success","true");
                return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET")
                        .entity(resultMap)
                        .build();
            }

        }catch (Exception e){
            resultMap.put("error","something went wrong please try again later");
            return  Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET")
                    .entity(resultMap).build();
        }
        return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET")
                .entity(resultMap)
                .build();
    }

    @Produces("application/json")
    @GET
    @Path("/checkemail/{email}")
    public Response checkEmail(@PathParam("email")String email){
        Map<String,String> resultMap = new LinkedHashMap<>();
        String checkUserName = lifeSaverDAO.checkEmail(email);
        int check = Integer.parseInt(checkUserName);
        try{
            if(check==1){
                resultMap.put("error","username already exists");
                return Response.status(Response.Status.CONFLICT).header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET")
                        .entity(resultMap)
                        .build();
            }else if(check==0){
                resultMap.put("success","true");
                return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET")
                        .entity(resultMap)
                        .build();
            }

        }catch (Exception e){
            resultMap.put("error","something went wrong please try again later");
            return  Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET")
                    .entity(resultMap).build();
        }
        return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET")
                .entity(resultMap)
                .build();
    }



    @Produces("application/json")
    @GET
    @Path("/getgeocodes")
    public Response getGeoCodes(){
        return null;
    }

    @Produces("application/json")
    @GET
    @Path("/getuserdetails/{username}")
    public Response getUserDetails(@PathParam("username")String userName){
        Map<String,Object> resultMap = new LinkedHashMap<>();
        try{
            User user= lifeSaverDAO.getUser(userName);
            resultMap.put("success","true");
            resultMap.put("user",user);
            return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET")
                    .entity(resultMap)
                    .build();
        }
        catch(Exception e){
            resultMap.put("error","something went wrong please try again later");
            return  Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET")
                    .entity(resultMap)
                    .build();
        }
    }

    @Produces("application/json")
    @POST
    @Path("/getusers/")
    @Consumes("application/json")
    public Response getUserDetails(User user){
        Map<String,Object> resultMap = new LinkedHashMap<>();
       try{
           List<User> usersList= lifeSaverDAO.getUsers(user);
           resultMap.put("success","true");
           resultMap.put("users",usersList);
           return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
                   .header("Access-Control-Allow-Methods", "GET")
                   .entity(resultMap)
                   .build();
        }
       catch(Exception e){
           resultMap.put("error","something went wrong please try again later");
           return  Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                   .header("Access-Control-Allow-Origin", "*")
                   .header("Access-Control-Allow-Methods", "GET")
                   .entity(resultMap)
                   .build();
       }
    }

    @Produces("application/json")
    @POST
    @Path("/updateuser/{username}")
    @Consumes("application/json")
    public Response updateUser(User user,@PathParam("username")String username){
        Map<String,Object> resultMap = new LinkedHashMap<>();
        try{
            lifeSaverDAO.updateUser(user,username);
            resultMap.put("success","true");
            return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET")
                    .entity(resultMap)
                    .build();
        }
        catch(Exception e){
            resultMap.put("error","something went wrong please try again later");
            return  Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET")
                    .entity(resultMap)
                    .build();
        }
    }

}
