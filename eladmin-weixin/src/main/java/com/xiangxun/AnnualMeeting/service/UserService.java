package com.xiangxun.AnnualMeeting.service;

import com.xiangxun.AnnualMeeting.bean.UserInfo;
import com.xiangxun.AnnualMeeting.mapper.UserInfoMapper;
import com.xiangxun.common.help.Award;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserInfoMapper userInfoMapper;

    public List<UserInfo> getAllUserInfo()
    {
        List<UserInfo> list = new ArrayList();
        list = this.userInfoMapper.selectAll();
        return list;
    }

    public Integer addUser(UserInfo userInfo)
    {
        return this.userInfoMapper.addUser(userInfo);
    }

    public UserInfo getSignByuserId(String userid)
    {
        return this.userInfoMapper.getSignByuserId(userid);
    }

    public Integer[] getLeftPerson()
    {
        return this.userInfoMapper.getLeftPerson();
    }

    public Integer[] getDengjiPerson(String dengji)
    {
        return this.userInfoMapper.getDengjiPerson(dengji);
    }

    public Integer[] random(Integer count, String dengji)
    {
        Integer[] left_persons = this.userInfoMapper.getLeftPerson();
        Integer[] left_biaojipersons = this.userInfoMapper.getDengjiPerson(dengji);
        Integer[] ret = Award.getRandom(count.intValue(), new Integer[][] { left_persons, left_biaojipersons });
        Map saveData = new HashMap();
        saveData.put("list", ret);
        saveData.put("dengji", dengji);
        this.userInfoMapper.saveTheData(saveData);
        return ret;
    }

    public List<UserInfo> getAllUserInfoByConditation(String name)
    {
        return this.userInfoMapper.getAllUserInfoByConditation(name);
    }

    public List<UserInfo> getAllPersonInfoByConditation()
    {
        return this.userInfoMapper.getAllUserInfoByConditation();
    }

    public Integer sedengji(Integer id, String dengji)
    {
        Map saveData = new HashMap();
        saveData.put("id", id);
        saveData.put("dengji", dengji);
        return this.userInfoMapper.sedengji(saveData);
    }

    public Integer chongzhi()
    {
        return this.userInfoMapper.chongzhi();
    }
}
