package com.itoken.service.admin.service;

import com.funtl.itoken.service.admin.ServiceAdminApplication;
import com.funtl.itoken.service.admin.domain.TbSysUser;
import com.funtl.itoken.service.admin.service.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceAdminApplication.class)
@ActiveProfiles(value = "prod")
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    /**
     * 注册
     */
    @Test
    public void testRegister() {
        TbSysUser tbSysUser = new TbSysUser();
        tbSysUser.setUserCode(UUID.randomUUID().toString());
        tbSysUser.setLoginCode("lusifer@funtl.com");
        tbSysUser.setUserName("Lusifer");
        tbSysUser.setUserType("1");
        tbSysUser.setMgrType("1");
        tbSysUser.setStatus("0");
        tbSysUser.setCreateBy(tbSysUser.getUserCode());
        tbSysUser.setCreateDate(new Date());
        tbSysUser.setUpdateBy(tbSysUser.getUserCode());
        tbSysUser.setUpdateDate(new Date());
        tbSysUser.setCorpCode("0");
        tbSysUser.setCorpName("iToken");
        tbSysUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        adminService.register(tbSysUser);
    }

    /**
     * 登录
     */
    @Test
    public void testLogin() {
        TbSysUser tbSysUser = adminService.login("lusifer@funtl.com", "123456");
        Assert.assertNotNull(tbSysUser);
    }
}