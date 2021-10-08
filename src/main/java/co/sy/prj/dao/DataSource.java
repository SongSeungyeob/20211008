package co.sy.prj.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
   private static DataSource dataSource = new DataSource();
   private Connection conn;
   private String driver;
   private String url;
   private String user;
   private String password;

   private DataSource() { // 외부에서 생성할 수 없도록 private로 생성자 만듦
      dbconfig();
   }

   public static DataSource getInstance() {
      return dataSource;
   }

   private void dbconfig() { 	// 외부 properties 파일을 읽어서 값을 저장한다
      Properties properties = new Properties();
      String resource = getClass().getResource("/db.properties").getPath();
      try {
         properties.load(new FileReader(resource));
         driver = properties.getProperty("driver");
         url = properties.getProperty("url");
         user = properties.getProperty("user");
         password = properties.getProperty("password");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public Connection getConnection() {
      try {
         Class.forName(driver);
         conn = DriverManager.getConnection(url, user, password);
         //System.out.println("DB 연결 성공");
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      }
      return conn; // 생성된 커넥션 객체를 돌려준다
   }
}