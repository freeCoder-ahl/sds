package com.didiglobal.sds.web.test.dao;

import com.didiglobal.sds.web.dao.AppInfoDao;
import com.didiglobal.sds.web.dao.bean.AppInfoDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yizhenqiang on 18/2/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppInfoDaoTest {

    @Autowired
    private AppInfoDao appInfoDao;

    @Test
    public void addAppInfoTest() {
        AppInfoDO appInfoDO = new AppInfoDO();
        appInfoDO.setAppGroupName("黑马1");
        appInfoDO.setAppName("bh-order");
        appInfoDO.setCreatorEmail("manzhizhen@163.com");
        appInfoDO.setCreatorName("yizhenqiang");
        appInfoDO.setOperatorEmail("manzhizhen@163.com");
        appInfoDO.setOperatorName("yizhenqiang");
        Assert.assertEquals(1, appInfoDao.addAppInfo(appInfoDO));

        appInfoDO.setAppGroupName("黑马1");
        appInfoDO.setAppName("bh-order1");
        Assert.assertEquals(1, appInfoDao.addAppInfo(appInfoDO));

        appInfoDO.setAppGroupName("黑马1");
        appInfoDO.setAppName("bh-order1");
        try {
            appInfoDao.addAppInfo(appInfoDO);
            Assert.fail();
        } catch (Exception e) {
        }

        appInfoDO.setAppGroupName("黑马2");
        appInfoDO.setAppName("bh-order");
        appInfoDO.setStrategyGroupName("FIRST_GREP");
        Assert.assertEquals(1, appInfoDao.addAppInfo(appInfoDO));
    }

    @Test
    public void queryAppInfoTest() {
        System.out.println(appInfoDao.queryAppInfo("黑马", "bh-order"));
    }

    @Test
    public void queryAppInfoByPageTest() {
        System.out.println(appInfoDao.queryAppInfoByPage(null, null, 1, 10));
    }
}
