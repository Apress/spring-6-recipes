package com.apress.spring6recipes.bookshop;

import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcBookShop implements BookShop {

	private final DataSource dataSource;

	public JdbcBookShop(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void purchase(String isbn, String username) {
		try (var conn = dataSource.getConnection()) {
			try {
			conn.setAutoCommit(false);
				int price;
				var BOOK_SQL = "SELECT PRICE FROM BOOK WHERE ISBN = ?";
				try (var stmt1 = conn.prepareStatement(BOOK_SQL)) {
					stmt1.setString(1, isbn);
					try (var rs = stmt1.executeQuery()) {
						rs.next();
						price = rs.getInt("PRICE");
					}
				}

				var STOCK_SQL = "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?";
				try (var stmt2 = conn
								.prepareStatement(STOCK_SQL)) {
					stmt2.setString(1, isbn);
					stmt2.executeUpdate();
				}

				var ACCOUNT_SQL = "UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?";
				try (var stmt3 = conn
								.prepareStatement(ACCOUNT_SQL)) {
					stmt3.setInt(1, price);
					stmt3.setString(2, username);
					stmt3.executeUpdate();
				}
				conn.commit();
			} catch (SQLException ex) {
				conn.rollback();
				throw ex;
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
}
