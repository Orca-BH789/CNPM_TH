package Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {

    public static void main(String[] args) {
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            
            String serverName = "BEECUTE"; // Tên máy chủ SQL Server
            String portNumber = "1433"; // Cổng kết nối SQL Server
            String databaseName = "QLBanSach";
            String username = "sa";
            String password = "admin";

         
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + databaseName+";encrypt=false";

          
            Connection connection = DriverManager.getConnection(url, username, password);
            
          
            if (connection != null) {
                System.out.println("Kết nối thành công!");
                
             
                System.out.println("Câu lệnh SELECT");
                String selectQuery = "SELECT top 2 * FROM KHACHHANG";
                Statement selectStatement = connection.createStatement();
                ResultSet resultSet = selectStatement.executeQuery(selectQuery);
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));
                }
                
                // Thực thi câu lệnh INSERT
                System.out.println("Câu lệnh INSERT");
                String insertQuery = "INSERT [dbo].[KHACHHANG] ( [HoTenKH], [DiaChiKH], [DienThoaiKH], [TenDN], [MatKhau], [NgaySinh], [GioiTinh], [Email], [DaDuyet]) VALUES ( N'NGuyễn Quốc Tuấn', N'Phú AN', N'0349937898', N'kytbhhđ', N'36548', CAST(N'1972-02-08T00:00:00' AS SmallDateTime), 1, N'Tftgj@tdmu.edu.vn', 0)\r\n";
                Statement insertStatement = connection.createStatement();
                int rowsInserted = insertStatement.executeUpdate(insertQuery);
                System.out.println(rowsInserted + " dòng được chèn thành công.");
                
                // Thực thi câu lệnh UPDATE
                System.out.println("Câu lệnh UPDATE");
                String updateQuery = "UPDATE KHACHHANG SET HoTenKH = 'Da doi ten' WHERE MAKH = 10";
                Statement updateStatement = connection.createStatement();
                int rowsUpdated = updateStatement.executeUpdate(updateQuery);
                System.out.println(rowsUpdated + " dòng được cập nhật thành công.");
                
                // Thực thi câu lệnh DELETE
                System.out.println("Câu lệnh DELETE");
                String deleteQuery = "DELETE FROM KHACHHANG WHERE MaKH='19' ";
                Statement deleteStatement = connection.createStatement();
                int rowsDeleted = deleteStatement.executeUpdate(deleteQuery);
                System.out.println(rowsDeleted + " dòng được xóa thành công.");
                
                // Đóng kết nối
                connection.close();
            } else {
                System.out.println("Kết nối thất bại!");
            }
        } catch (ClassNotFoundException e) {
            // Xử lý nếu không tìm thấy driver JDBC
            System.out.println("Không tìm thấy driver JDBC!");
            e.printStackTrace();
        } catch (SQLException e) {
            // Xử lý nếu có lỗi khi kết nối cơ sở dữ liệu
            System.out.println("Kết nối thất bại!");
            e.printStackTrace();
        }
    }
}
