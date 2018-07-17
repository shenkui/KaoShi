package test;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by user on 2018/7/17.
 */
public class shujiguanlixitong {
    private Connection getconntion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2.创建连接
            String dbURL = "jdbc:mysql://localhost:3306/hnb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            //3.建立数据库连接

            Connection connection = DriverManager.getConnection(dbURL, "root", "19990303iou");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    private void testConnection() {
        //1.加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2.创建连接
            String dbURL = "jdbc:mysql://localhost:3306/hnb";
            //3.建立数据库连接
            try {
                Connection connection = DriverManager.getConnection(dbURL, "root", "19990303iou");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void InsertData(int id,int book_name,String book_publishers,String book_author) throws SQLException {
        //1创建数据库连接
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/hnb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "19990303iou");

            String sql ="insert into book(book_name, book_publishers,book_author)"+
                    " values('" + book_name+ "','" + book_publishers+ "','" + book_author+ "')";
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println("所受影响的行为" + rows);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
        }finally {
            Class(connection,statement,null);
        }

    }
    private void DelateDate(int id) throws SQLException {
        Connection connection = null;
        Statement  statement = null;
        try {
            connection =  getconntion();
            String sql = "delete from book where id="+id;
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println("有" + rows + "行被删除成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Class(connection,statement,null);
        }
    }
    private void UpdateDate(int id, String account, String password) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        //创建数据库连接
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection
                    ("jdbc:mysql://127.0.0.1/hnb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"
                            , "root", "19990303iou");
            String sql = "Update book set book_name='" + account + "',book_publishers='" + password + "',book_author='" + password + "'where id=" + id;
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println("更新结果为" + (rows > 0));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Class(connection, statement, null);
        }

    }

    private  String [] [] bestFindAllData() throws SQLException {
        String [][] datas = new String[52][3];
        Connection connection = null;
        Statement  statement = null;
        ResultSet resultSet = null;
        //获取数据库连接
        connection =  getconntion();
        //构建查询的sql 语句
        String sql = "select * from book ";
        //执行sql 语句，并获得结果集
        statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            //遍历结果集，输出每条记录信息
            int index = 0;
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int book_name = resultSet.getInt(2);
                String book_publishers = resultSet.getString(3);
                String book_author = resultSet.getString(4);
                datas [index] [0] = id + "";
                datas [index] [1] = book_name + "";
                datas [index] [2] = book_publishers + "";
                datas [index] [2] = book_author + "";

                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Class(connection,statement,resultSet);
        }return datas;
    }


    private  void findAllDataFormaOutput() throws SQLException {
        String [] [] datas = bestFindAllData();
        StringBuffer buffer = new StringBuffer();
        buffer.append("");
        buffer.append("id\t\t\tbook_name\t\t\tbook_publishers\t\t\tbook_auther"+System.lineSeparator());
        buffer.append("");
        for (int i = 0; i < datas.length;i++){
            String [] values = datas[i];
            if (values[0] != null && values[1] != null && values[2] != null &&  values[3] != null){
                buffer.append(String.format("%s\t\t\t|%s\t\t\t|%s",values[0],values[1],values[2], values[3]));
                buffer.append(System.lineSeparator());
            }
        }
        System.out.println(buffer.toString());
    }


    private  void findXuehsengDataLikeKeyWord(String keyWord) throws SQLException {
        //yuwen,shuxue
        //获取数据库连接
        Connection connection = null;
        Statement  statement = null;
        ResultSet resultSet = null;
        connection =  getconntion();
        connection =  getconntion();
        String sql = "select id ,book_name,book_publishers,book_author from book  " +
                "where id like '%"+ keyWord +"%'or book_name like '%"+ keyWord +"%' or book_publishers like '%"+ keyWord +"%' or book_author like '%"+ keyWord +"%'";
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            StringBuffer buffer = new StringBuffer();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String  book_name = resultSet.getString(2);
                String book_publishers = resultSet.getString(3);
                String book_author = resultSet.getString(4);
                buffer.append(id+"\t|"+book_name+"|\t"+book_publishers+"|"+book_author+"|"+System.lineSeparator());



            }System.out.println(buffer.toString());


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            Class(connection,statement,resultSet);
        }
    }
    private  void Class( Connection connection,Statement statement,ResultSet resultSet) throws SQLException {

        //2.创建连接
        //3.建立数据库连接
        try {

            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private  void findAllData() throws SQLException {
        Connection connection = null;
        Statement  statement = null;
        ResultSet resultSet = null;
        //获取数据库连接
        connection =  getconntion();
        //构建查询的sql 语句
        String sql = "select * from book";
        //执行sql 语句，并获得结果集
        statement = null;
        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);
            //遍历结果集，输出每条记录信息
            StringBuffer buffer = new StringBuffer();
            buffer.append("");
            buffer.append("id\t\t\tbook_name\t\t\tbook_publishers\t\t\tbook_author"+System.lineSeparator());
            buffer.append("");
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String book_name = resultSet.getString(2);
                String book_publishers = resultSet.getString(3);
                String book_author = resultSet.getString(4);
                buffer.append(id+"\t|"+book_name+"|\t"+book_publishers+"|"+book_author+"|"+System.lineSeparator());



            }
            System.out.println(buffer.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Class(connection,statement,resultSet);
        }
    }





    public  static void main (String [] args) throws SQLException {
       shujiguanlixitong demo = new  shujiguanlixitong();
        demo. findAllData() ;
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("欢迎进入ZM系统");
            System.out.println("1、添加书籍2、修改书籍3、删除书籍 4、请输入要查询书籍的模糊数据 5、查询所有书籍 6、退出系统");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("请选择你要操作的选择");
            int select = 0;//接收用户的选项
            select = scanner.nextInt();
            while (select < 1 ||select > 5){

                System.out.println("选择的操作不正确，请重新选");
                select = scanner.nextInt();}
            String value = null;
            shujiguanlixitong  shujiguanlixitong = new  shujiguanlixitong();
            if (select == 1){//添加数据
                System.out.println("请输入书籍名，出版商，书籍作者");
                value = scanner.next();
                String [] values = value.split(",");
                shujiguanlixitong.InsertData((int)System.currentTimeMillis(),(int)System.currentTimeMillis(),values[0],values[1]);

            }
            else if (select == 2){//修改数据
                System.out.println( "请输入要修改的书籍名，出版商，书籍作者");
                value = scanner.next();
                String [] values = value.split(",");
                shujiguanlixitong.UpdateDate(Integer.parseInt(values[0]),values[1],values[2]);

            }
            else if (select == 3){//删除数据
                System.out.println("请输入要删除的id");
                value = scanner.next();
                shujiguanlixitong.DelateDate(Integer.parseInt(value));
                System.out.println("是否继续删除");
                System.out.println("1 继续，2 取消");
                int count;
                count = Integer.parseInt(value);
                while(count == 1){
                    System.out.println("请继续输入要删除的id");
                    value = scanner.next();
                    shujiguanlixitong.DelateDate(Integer.parseInt(value));
                }

            }
            else if (select == 4){//请输入要查询的模糊数据
                System.out.println("请输入要查询书籍的模糊数据");
                value = scanner.next();
                shujiguanlixitong.findXuehsengDataLikeKeyWord(String.valueOf(Integer.parseInt(value)));

            }
            else if (select == 5){//查询所有书籍
                shujiguanlixitong.findAllData();
            }
            else if (select == 6){//退出系统
                System.exit(-1);
            }
        }}}




