package com.mmall.service.impl;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("iUserService")
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserMapper userMapper;

    /**
     *  登陆
     * @param username
     * @param password
     * @return
     */
    public ServerResponse<User> login(String username,String password){

        int usernameCount = userMapper.checkUsername(username);
        if(usernameCount==0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.checkLogin(username,md5Password);

        if(user==null){
           return ServerResponse.createByErrorMessage("密码错误");
        }

        user.setPassword(StringUtils.EMPTY);

        return ServerResponse.createBySuccessMsgAndData("登陆成功",user);

    }

    /**
     *  用户注册
     * @param user
     * @return
     */
    public ServerResponse<String> register(User user){

       ServerResponse usernameServerResponse = this.checkVaild(user.getUsername(),Const.USERNAME);
       if(!usernameServerResponse.isSuccess()){ // 已注册
            return usernameServerResponse;
       }

       ServerResponse emailServerResponse = this.checkVaild(user.getEmail(),Const.EMAIL);

        if(!usernameServerResponse.isSuccess()){// 已注册
            return emailServerResponse;
        }

        user.setRole(Const.Role.ROLE_CUSTOMER);
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

        int userCount = userMapper.insert(user);
        if(userCount==0){
            return ServerResponse.createByErrorMessage("注册失败");
        }

        return ServerResponse.createBySuccessMessage("注册成功");


    }


    /***
     *  校验用户名和email的唯一性
     * @param name
     * @param type
     * @return
     */
    public ServerResponse checkVaild(String name,String type){

        if(StringUtils.isNotBlank(type)){

           if(Const.USERNAME.equals(type)){

               int count = userMapper.checkUsername(name);
               if(count>0){
                    return  ServerResponse.createByErrorMessage("用户名已存在");
               }

           }

           if(Const.EMAIL.equals(type)){
               int count = userMapper.checkEmail(name);
               if(count>0){
                   return  ServerResponse.createByErrorMessage("邮箱已存在");
               }

           }

        }else{

          return ServerResponse.createByErrorMessage("type这个参数有误");
        }

        return ServerResponse.createBySuccessMessage("校验成功");

    }


    /**
     *  获取用户信息
     * @param userId
     * @return
     */
   public ServerResponse<User> getUserInformation(Integer userId){

       User user = userMapper.selectByPrimaryKey(userId);

       if (user==null){
           return ServerResponse.createByErrorMessage("用户不存在");
       }
       user.setPassword(StringUtils.EMPTY);
       return ServerResponse.createBySuccessMsgAndData("已获取到用户信息",user);

   }

    /**
     *  忘记密码的情况，根据用户名获取问题
     * @param username
     * @return
     */
   public ServerResponse<String> forgetGetQuestion(String username){

       if(StringUtils.isBlank(username)){
           return ServerResponse.createByErrorMessage("用户名不能为空");
       }

       String question = userMapper.selectQuestionByUsername(username);
       if(StringUtils.isBlank(question)){

           return ServerResponse.createByErrorMessage("问题不存在");
       }

       return ServerResponse.createBySuccessData(question);


   }


    /***
     * 验证问题答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
   public ServerResponse<String> checkAnswer(String username,String question,String answer){

       int count = userMapper.checkAnswer(username,question,answer);
       if(count==0){
           return ServerResponse.createByErrorMessage("答案错误");
       }
       return ServerResponse.createBySuccessData(answer);
   }















































































}
