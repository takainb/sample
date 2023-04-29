package sec9_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseAccess {

	public static void main(String[] args) {
		
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("ドライバのロードに失敗しました");
		}
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection("jdbc:h2:~/rpgdb");
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM MONSTERS HP <= ? OR NAME = ?");
			ps.setInt(1, 10);
			ps.setString(2, "ゾンビ");
			int r = ps.executeUpdate();
			
			if(r!=0) {
				System.out.println(r + "件のモンスターを削除しました");
			} else {
				System.out.println("該当するモンスターはありませんでした");
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("データベースアクセス完了");
		
	}

}
