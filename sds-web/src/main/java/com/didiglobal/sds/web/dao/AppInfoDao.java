package com.didiglobal.sds.web.dao;

import com.didiglobal.sds.web.dao.bean.AppInfoDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by yizhenqiang on 18/2/11.
 */
@Mapper
public interface AppInfoDao {

    /**
     * 新增应用信息
     *
     * @param appInfoDO
     * @return
     */
    @Insert("<script> insert into app_info(app_group_name, app_name, operator_name, operator_email, creator_name, "
            + "creator_email " +
            " <if test=\"strategyGroupName != null and strategyGroupName.length > 0 \">, strategy_group_name </if>" +
            ") " +
            "values(#{appGroupName}, #{appName}, #{operatorName}, #{operatorEmail}, #{creatorName}, #{creatorEmail} " +
            " <if test=\"strategyGroupName != null and strategyGroupName.length > 0 \">, #{strategyGroupName} </if>" +
            ")" +
            " </script>")
    int addAppInfo(AppInfoDO appInfoDO);

    /**
     * 更新应用信息
     *
     * @param appGroupName
     * @param appName
     * @param newAppName
     * @param newStrategyGroupName
     * @param operatorName
     * @param operatorEmail
     * @return
     */
    @Update("update app_info set app_name = #{newAppName}, strategy_group_name = #{newStrategyGroupName}, version = "
            + "version + 1, operator_name = #{operatorName}, operator_email = #{operatorEmail} " +
            "where app_group_name = #{appGroupName} and app_name = #{appName}")
    int updateAppInfo(@Param("appGroupName") String appGroupName, @Param("appName") String appName, @Param(
            "newAppName") String newAppName,
                      @Param("newStrategyGroupName") String newStrategyGroupName,
                      @Param("operatorName") String operatorName, @Param("operatorEmail") String operatorEmail);

    /**
     * 删除应用信息
     *
     * @param appGroupName
     * @param appName
     * @return
     */
    @Delete("delete from app_info where app_group_name = #{appGroupName} and app_name = #{appName}")
    int deleteAppInfo(@Param("appGroupName") String appGroupName, @Param("appName") String appName);

    /**
     * 应用信息版本+1
     *
     * @param appGroupName
     * @param appName
     * @param operatorName
     * @param operatorEmail
     * @return
     */
    @Update("update app_info set version = version + 1, operator_name = #{operatorName}, operator_email = "
            + "#{operatorEmail} where app_group_name = #{appGroupName} and app_name = #{appName}")
    int increaseAppVersion(@Param("appGroupName") String appGroupName, @Param("appName") String appName, @Param(
            "operatorName") String operatorName, @Param("operatorEmail") String operatorEmail);

    /**
     * 分页查询应用信息
     *
     * @param appGroupName
     * @param appName
     * @param start
     * @param size
     * @return
     */
    @Select("<script> select * from app_info " +
            "<where> " +
            " <if test=\"appGroupName != null and appGroupName.length > 0 \"> app_group_name = #{appGroupName} </if> " +
            " <if test=\"appName != null  and appName.length > 0 \"> and app_name like concat('%', #{appName}, '%') "
            + "</if> " +
            "</where>" +
            " order by modify_time desc " +
            " limit #{start}, #{size} </script> ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "appGroupName", column = "app_group_name"),
            @Result(property = "appName", column = "app_name"),
            @Result(property = "strategyGroupName", column = "strategy_group_name"),
            @Result(property = "version", column = "version"),
            @Result(property = "operatorName", column = "operator_name"),
            @Result(property = "operatorEmail", column = "operator_email"),
            @Result(property = "creatorName", column = "creator_name"),
            @Result(property = "creatorEmail", column = "creator_email"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifiedTime", column = "modify_time")
    })
    List<AppInfoDO> queryAppInfoByPage(@Param("appGroupName") String appGroupName, @Param("appName") String appName,
                                       @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 通过应用组名称和应用名称来查询应用信息
     *
     * @param appGroupName
     * @param appName
     * @return
     */
    @Select("select * from app_info where app_group_name = #{appGroupName} and app_name = #{appName} order by "
            + "modify_time desc")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "appGroupName", column = "app_group_name"),
            @Result(property = "appName", column = "app_name"),
            @Result(property = "strategyGroupName", column = "strategy_group_name"),
            @Result(property = "version", column = "version"),
            @Result(property = "operatorName", column = "operator_name"),
            @Result(property = "operatorEmail", column = "operator_email"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifiedTime", column = "modify_time")
    })
    AppInfoDO queryAppInfo(@Param("appGroupName") String appGroupName, @Param("appName") String appName);

    /**
     * 通过应用组名称来查询所有应用
     *
     * @param appGroupName
     * @return
     */
    @Select("select * from app_info where app_group_name = #{appGroupName} order by modify_time desc ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "appGroupName", column = "app_group_name"),
            @Result(property = "appName", column = "app_name"),
            @Result(property = "strategyGroupName", column = "strategy_group_name"),
            @Result(property = "version", column = "version"),
            @Result(property = "operatorName", column = "operator_name"),
            @Result(property = "operatorEmail", column = "operator_email"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifiedTime", column = "modify_time")
    })
    List<AppInfoDO> queryAppInfoByAppGroup(@Param("appGroupName") String appGroupName);
}
