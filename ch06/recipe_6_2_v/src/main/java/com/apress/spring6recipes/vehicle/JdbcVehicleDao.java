package com.apress.spring6recipes.vehicle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcVehicleDao implements VehicleDao {

	private static final String INSERT_SQL = "INSERT INTO VEHICLE (COLOR, WHEEL, SEAT, VEHICLE_NO) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_SQL = "UPDATE VEHICLE SET COLOR=?,WHEEL=?,SEAT=? WHERE VEHICLE_NO=?";
	private static final String SELECT_ALL_SQL = "SELECT * FROM VEHICLE";
	private static final String SELECT_ONE_SQL = "SELECT * FROM VEHICLE WHERE VEHICLE_NO = ?";
	private static final String DELETE_SQL = "DELETE FROM VEHICLE WHERE VEHICLE_NO=?";

	private final DataSource dataSource;

	public JdbcVehicleDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Vehicle vehicle) {
		var jdbcTemplate = new JdbcTemplate(this.dataSource);
		jdbcTemplate.update(INSERT_SQL, vehicle.getColor(), vehicle.getWheel(), vehicle.getSeat(),
						vehicle.getVehicleNo());
	}

	@Override
	public void insert(Collection<Vehicle> vehicles) {
		var jdbcTemplate = new JdbcTemplate(this.dataSource);
		jdbcTemplate.batchUpdate(INSERT_SQL, vehicles, vehicles.size(), this::prepareStatement);
	}

	@Override
	public Vehicle findByVehicleNo(String vehicleNo) {
		var jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.queryForObject(SELECT_ONE_SQL, BeanPropertyRowMapper.newInstance(Vehicle.class), vehicleNo);
	}

	@Override
	public List<Vehicle> findAll() {
		var jdbcTemplate = new JdbcTemplate(dataSource);
		var rows = jdbcTemplate.queryForList(SELECT_ALL_SQL);
		return rows.stream().map(row -> {
			var vehicle = new Vehicle();
			vehicle.setVehicleNo((String) row.get("VEHICLE_NO"));
			vehicle.setColor((String) row.get("COLOR"));
			vehicle.setWheel((Integer) row.get("WHEEL"));
			vehicle.setSeat((Integer) row.get("SEAT"));
			return vehicle;
		}).collect(Collectors.toList());
	}

	@Override
	public void update(Vehicle vehicle) {
		var jdbcTemplate = new JdbcTemplate(this.dataSource);
		jdbcTemplate.update(UPDATE_SQL, ps -> prepareStatement(ps, vehicle));
	}

	@Override
	public void delete(Vehicle vehicle) {
		var jdbcTemplate = new JdbcTemplate(this.dataSource);
		jdbcTemplate.update(DELETE_SQL, vehicle.getVehicleNo());
	}

	private void prepareStatement(PreparedStatement ps, Vehicle vehicle) throws SQLException {
		ps.setString(1, vehicle.getColor());
		ps.setInt(2, vehicle.getWheel());
		ps.setInt(3, vehicle.getSeat());
		ps.setString(4, vehicle.getVehicleNo());
	}

}
