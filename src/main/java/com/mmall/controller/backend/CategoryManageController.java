package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/manage/category/")
public class CategoryManageController {

 @Autowired
 private ICategoryService iCategoryService;
 @Autowired
 private IUserService iUserService;

    /**
     * 添加新品类
     * @param request
     * @param categoryName
     * @param parentId
     * @return
     */
    @RequestMapping(value = "add_category.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(HttpServletRequest request, String categoryName, @RequestParam(value = "parentId",defaultValue = "0") int parentId){
//        String loginToken = CookieUtil.readLoginToken(request);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//
//        String userStr = RedisShardedPoolUtil.get(loginToken);
//
//        User user = JsonUtil.str2Obj(userStr,User.class);
//        if(user==null){
//            return ServerResponse.createByErrorCodeAndMessage(Const.ResponseCode.NEED_LOGIN.getCode(),Const.ResponseCode.NEED_LOGIN.getDesc());
//        }
//        if(iUserService.checkAdminRole(user).isSuccess()){ //是管理员
//              return iCategoryService.addCategory(categoryName,parentId);
//        }else {
//            return ServerResponse.createByErrorMessage("非管理员权限");
//        }
        return iCategoryService.addCategory(categoryName,parentId);
    }

    /**
     *  获取品类子节点(平级)
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "get_category.do",method = RequestMethod.POST)
    @ResponseBody
    public  ServerResponse getCategory(HttpServletRequest request,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
//
//        String loginToken = CookieUtil.readLoginToken(request);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//
//        String userStr = RedisShardedPoolUtil.get(loginToken);
//
//        User user = JsonUtil.str2Obj(userStr,User.class);
//         if(user==null){
//             return ServerResponse.createByErrorMessage("用户未登录，请登录！");
//         }
//         if(iUserService.checkAdminRole(user).isSuccess()){
//             return iCategoryService.getChildParallelCategory(categoryId);
//         }else {
//             return ServerResponse.createByErrorMessage("无操作权限，需要管理员权限");
//         }

        return iCategoryService.getChildParallelCategory(categoryId);
    }

    /**
     * 修改品类名字
     * @param categoryId
     * @param categoryName
     * @return
     */
    @RequestMapping(value = "set_category_name.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setCategoryName(HttpServletRequest request,Integer categoryId,String categoryName){

//        String loginToken = CookieUtil.readLoginToken(request);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//
//        String userStr = RedisShardedPoolUtil.get(loginToken);
//
//        User user = JsonUtil.str2Obj(userStr,User.class);
//        if(user==null){
//            return ServerResponse.createByErrorCodeAndMessage(Const.ResponseCode.NEED_LOGIN.getCode(),Const.ResponseCode.NEED_LOGIN.getDesc());
//        }
//
//        if(iUserService.checkAdminRole(user).isSuccess()){
//
//            return iCategoryService.updateCategoryName(categoryId,categoryName);
//        }
//
//        return ServerResponse.createByErrorMessage("用户无管理员权限");
        return iCategoryService.updateCategoryName(categoryId,categoryName);

    }


    /***
     *  获取当前分类id及递归子节点categoryId
     * @param request
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "get_deep_category.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDeepCategory(HttpServletRequest request,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){

//        String loginToken = CookieUtil.readLoginToken(request);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//
//        String userStr = RedisShardedPoolUtil.get(loginToken);
//
//        User user = JsonUtil.str2Obj(userStr,User.class);
//        if(user==null){
//            return ServerResponse.createByErrorCodeAndMessage(Const.ResponseCode.NEED_LOGIN.getCode(),Const.ResponseCode.NEED_LOGIN.getDesc());
//        }
//
//        if(iUserService.checkAdminRole(user).isSuccess()){
//
//            return iCategoryService.selectCategoryAndChildrenById(categoryId);
//        }
//
//        return ServerResponse.createByErrorMessage("无权限操作，需要无管理员权限");


        return iCategoryService.selectCategoryAndChildrenById(categoryId);
    }










































































}
