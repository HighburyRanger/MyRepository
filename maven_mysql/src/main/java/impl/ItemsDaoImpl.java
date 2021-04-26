package impl;

import dao.ItemsDao;
import domain.Items;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsDaoImpl implements ItemsDao {

    @Override
    public List<Items> findAll() throws Exception {

        List<Items> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pst=null;
        ResultSet rs = null;

        try{
            //加载驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");

            //获取connection对象
            connection = DriverManager.getConnection("jdbc:mysql:///maven?useSSL=false&serverTimezone=GMT%2B8","root","12345678");
            //获取操作数据库的对象
            pst=connection.prepareCall("select * from items");
            //执行数据库操作
            rs = pst.executeQuery();
            //把数据库结果集转化为list集合

            while(rs.next()){
                Items items=new Items();
                items.setId(rs.getInt("id"));
                items.setName(rs.getString("name"));
                list.add(items);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            connection.close();
            pst.close();
            rs.close();
        }


        return list;
    }
}
