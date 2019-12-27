package com.xiaoxin.gdata.data.rider;

import java.sql.*;
import java.text.SimpleDateFormat;

import com.xiaoxin.gdata.utils.JDBCUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class xiaoxinParameGetsDatabase {
    private static Logger LOG = LoggerFactory.getLogger(xiaoxinParameGetsDatabase.class) ;


    public String getaMenuId(long sellerId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String menu_id="" ;

        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date date = new Date(l);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nyr = dateFormat.format(date)+" 00:00:00";
        try {
            //1、获取连接
            conn = JDBCUtils.getConnection();
            //2、编写执行sql语句
            String sql = "SELECT menu_id FROM menu_calendar WHERE seller_id=? AND sell_start_time=? AND menu_type=?  ";
            //3、获取执行sql语句对象
            pstmt = conn.prepareStatement(sql);
            //4、设置参数
            pstmt.setLong(1, sellerId);
            pstmt.setString(2,nyr);
            pstmt.setInt(3,1);
            //5、执行查询操作
            rs= pstmt.executeQuery();
            //6、处理结果集
            while(rs.next()){
                menu_id = rs.getString("menu_id");
                LOG.info("==============获取当天menu_id:{}=============",menu_id);
            }
//            return menu_id ;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //6、释放资源
            JDBCUtils.release(conn, pstmt, rs);
        }
        return menu_id ;

    }

    public static String getRiderToken(String uuid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String token="" ;

        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date date = new Date(l);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nyr = dateFormat.format(date)+" 00:00:00";
        try {
            //1、获取连接
            conn = JDBCUtils.getConnection();
            //2、编写执行sql语句
            String sql = "SELECT token_code from dc_user_code.biz_user_token WHERE uuid = ? ORDER BY create_time DESC LIMIT 1  ";
            //3、获取执行sql语句对象
            pstmt = conn.prepareStatement(sql);
            //4、设置参数
            pstmt.setString(1, uuid);
            //5、执行查询操作
            rs= pstmt.executeQuery();
            //6、处理结果集
            while(rs.next()){
                token = rs.getString("token_code");
                LOG.info("==============获取token:{}=============",token);
            }
//            return menu_id ;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //6、释放资源
            JDBCUtils.release(conn, pstmt, rs);
        }
        return token ;

    }
}
