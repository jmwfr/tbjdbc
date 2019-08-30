package jdbctp.engineer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import jdbctp.database.DbConnexion;

public class IngenieurManager implements IngenieurInterface {

	public void insert(List<Object> args) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			if (args.size() != 10)
				throw new Exception("Insert needs 10 arguments!");

			conn = DbConnexion.connect();
			conn.setAutoCommit(false);

			String sql = "INSERT INTO ingenieur VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			int count = 1;
			for (Object entry : args) {
				if (entry.getClass() == String.class) {
					pstmt.setString(count, (String) entry);
				}
				if (entry.getClass() == Double.class) {
					pstmt.setDouble(count, (Double) entry);
				}
				count++;
			}

			int result = pstmt.executeUpdate();

			if (result == 1) {
				conn.commit();
				System.out.println("Engineer added successfully!");
			} else {
				conn.rollback();
				throw new Exception("Unhandled error while inserting engineer!");
			}
		} catch (SQLException ex) {
			System.out.printf("Error Code: %d\n", ex.getErrorCode());
			System.out.printf("Error Message: %s\n", ex.getLocalizedMessage());
			System.out.printf("SQL State: %s\n", ex.getSQLState());
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		} finally {
			try {
				if (pstmt != null && pstmt.isClosed() == false) {
					pstmt.close();
				}
				if (conn != null && conn.isClosed() == false) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public void update(int id, Map<String, Object> args) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbConnexion.connect();
			conn.setAutoCommit(false);
			String values = "SET ";
			
			for (String key : args.keySet()) {
				values += String.format("%s = ?, ", key);
			}

			values = values.substring(0, values.length() - 2);			
			
			String sql = String.format("UPDATE ingenieur %s WHERE numMatricule = %d", values, id);
			pstmt = conn.prepareStatement(sql);

			int count = 1;
			for (Map.Entry<String, Object> entry : args.entrySet()) {
				if (entry.getValue().getClass() == String.class) {
					pstmt.setString(count, (String) entry.getValue());
				}
				if (entry.getValue().getClass() == Double.class) {
					pstmt.setDouble(count, (Double) entry.getValue());
				}
				count++;
			}
			int result = pstmt.executeUpdate();
			if (result == 1) {
				conn.commit();
				System.out.println("Engineer updated successfully!");
			} else {
				conn.rollback();
				throw new Exception("Unhandled error while updating engineer!");
			}
		} catch (SQLException ex) {
			System.out.printf("Error Code: %d\n", ex.getErrorCode());
			System.out.printf("Error Message: %s\n", ex.getLocalizedMessage());
			System.out.printf("SQL State: %s\n", ex.getSQLState());
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		} finally {
			try {
				if (pstmt != null && pstmt.isClosed() == false) {
					pstmt.close();
				}
				if (conn != null && conn.isClosed() == false) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public void delete(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbConnexion.connect();
			conn.setAutoCommit(false);

			String sql = "DELETE FROM ingenieur WHERE numMatricule = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();

			if (result == 1) {
				conn.commit();
				System.out.println("Engineer deleted successfully!");
			} else {
				conn.rollback();
				throw new Exception("Unhandled error while deleting engineer!");
			}

		} catch (SQLException ex) {
			System.out.printf("Error Code: %d\n", ex.getErrorCode());
			System.out.printf("Error Message: %s\n", ex.getLocalizedMessage());
			System.out.printf("SQL State: %s\n", ex.getSQLState());
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		} finally {
			try {
				if (pstmt != null && pstmt.isClosed() == false) {
					pstmt.close();
				}
				if (conn != null && conn.isClosed() == false) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public Ingenieur findById(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbConnexion.connect();
			conn.setAutoCommit(false);
			String sql = "SELECT * from ingenieur WHERE numMatricule = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.isBeforeFirst()) {
				conn.commit();
				Ingenieur engineer = new Ingenieur();
				rs.next();

				engineer.setNumMatricule(rs.getInt(1));
				engineer.setNom(rs.getString(2));
				engineer.setPrenom(rs.getString(3));
				engineer.setDatenaissance(rs.getDate(4));
				engineer.setAdresse(rs.getString(5));
				engineer.setCp(rs.getString(6));
				engineer.setVille(rs.getString(7));
				engineer.setTel(rs.getString(8));
				engineer.setSexe(rs.getString(9));
				engineer.setSituation(rs.getString(10));
				engineer.setCodeprojet(rs.getDouble(11));

				rs.close();
				return engineer;
			} else {
				rs.close();
				conn.rollback();
				throw new Exception("Unhandled error while retrieving engineer!");
			}

		} catch (SQLException ex) {
			System.out.printf("Error Code: %d\n", ex.getErrorCode());
			System.out.printf("Error Message: %s\n", ex.getLocalizedMessage());
			System.out.printf("SQL State: %s\n", ex.getSQLState());
			return null;
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			return null;
		} finally {
			try {
				if (pstmt != null && pstmt.isClosed() == false) {
					pstmt.close();
				}
				if (conn != null && conn.isClosed() == false) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public List<Ingenieur> findAll() {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DbConnexion.connect();
			conn.setAutoCommit(false);
			String sql = "SELECT * from ingenieur";
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.isBeforeFirst()) {
				conn.commit();
				List<Ingenieur> lstEngineers = null;
				
				while (rs.next()) {
					Ingenieur engineer = new Ingenieur();

					engineer.setNumMatricule(rs.getInt(1));
					engineer.setNom(rs.getString(2));
					engineer.setPrenom(rs.getString(3));
					engineer.setDatenaissance(rs.getDate(4));
					engineer.setAdresse(rs.getString(5));
					engineer.setCp(rs.getString(6));
					engineer.setVille(rs.getString(7));
					engineer.setTel(rs.getString(8));
					engineer.setSexe(rs.getString(9));
					engineer.setSituation(rs.getString(10));
					engineer.setCodeprojet(rs.getDouble(11));
					lstEngineers.add(engineer);
				}
				rs.close();
				
				return lstEngineers;
			} else {
				rs.close();
				conn.rollback();
				throw new Exception("Unhandled error while retrieving engineer!");
			}

		} catch (SQLException ex) {
			System.out.printf("Error Code: %d\n", ex.getErrorCode());
			System.out.printf("Error Message: %s\n", ex.getLocalizedMessage());
			System.out.printf("SQL State: %s\n", ex.getSQLState());
			return null;
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			return null;
		} finally {
			try {
				if (stmt != null && stmt.isClosed() == false) {
					stmt.close();
				}
				if (conn != null && conn.isClosed() == false) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

}
