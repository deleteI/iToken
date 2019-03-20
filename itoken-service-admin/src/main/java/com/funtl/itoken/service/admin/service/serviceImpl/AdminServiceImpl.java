package com.funtl.itoken.service.admin.service.serviceImpl;

import com.funtl.itoken.service.admin.domain.TbSysUser;
import com.funtl.itoken.service.admin.mapper.TbSysUserMapper;
import com.funtl.itoken.service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    /**
     * 注册
     * @param tbSysUser
     */
    @Override
    @Transactional(readOnly = false)
    public void register(TbSysUser tbSysUser) {
        tbSysUserMapper.insert(tbSysUser);
    }

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        Example example = new Example(TbSysUser.class);
        example.createCriteria().andEqualTo("loginCode", loginCode);
        TbSysUser user = tbSysUserMapper.selectOneByExample(example);
        //若有该账户，继续验证密码；没有直接返回null
        if (user!=null){
            //获取MD5加密后的密匙
            String pswd = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
            if(pswd.equals(user.getPassword())){
                return  user;
            }
        }
        return null;
    }
}
