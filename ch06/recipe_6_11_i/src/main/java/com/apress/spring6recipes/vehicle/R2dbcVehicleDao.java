package com.apress.spring6recipes.vehicle;

import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Result;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.Statement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class R2dbcVehicleDao implements VehicleDao {

	private static final String INSERT_SQL = "INSERT INTO VEHICLE (COLOR, WHEEL, SEAT, VEHICLE_NO) VALUES ($1, $2, $3, $4)";
	private static final String SELECT_ALL_SQL = "SELECT * FROM VEHICLE";
	private static final String SELECT_ONE_SQL = "SELECT * FROM VEHICLE WHERE VEHICLE_NO = $1";
	private static final String DELETE_SQL = "DELETE FROM VEHICLE WHERE VEHICLE_NO=$1";

	private final ConnectionFactory connectionFactory;

	public R2dbcVehicleDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public Mono<Vehicle> save(Vehicle vehicle) {
		return Mono.usingWhen(
						connectionFactory.create(),
						c -> Mono.from(prepareStatement(c.createStatement(INSERT_SQL), vehicle).execute())
										.flatMap(res -> Mono.from(res.getRowsUpdated()))
										.doOnNext( (cnt) -> System.out.printf("Rows inserted: %d%n", cnt)),
						c -> c.close())
						.then(this.findByVehicleNo(vehicle.getVehicleNo()));
	}

	@Override
	public Mono<Vehicle> findByVehicleNo(String vehicleNo) {
		return Mono.usingWhen(
						connectionFactory.create(),
						con ->  Mono.from(con.createStatement(SELECT_ONE_SQL).bind("$1", vehicleNo).execute())
										.flatMap((rs) -> Mono.from(rs.map((row, meta) -> toVehicle(row)))),
						c -> c.close());
	}

	@Override
	public Flux<Vehicle> findAll() {
		return Flux.usingWhen(
						connectionFactory.create(),
						con -> Flux.from(con.createStatement(SELECT_ALL_SQL).execute())
										.flatMap((rs) -> Flux.from(rs.map((row, meta) -> toVehicle(row)))),
						con -> con.close());
	}

	@Override
	public Mono<Void> delete(Vehicle vehicle) {
		return Mono.usingWhen(
						connectionFactory.create(),
						con ->  Mono.from(con.createStatement(DELETE_SQL).bind("$1", vehicle.getVehicleNo()).execute())
										.flatMap(res -> Mono.from(res.getRowsUpdated()))
										.doOnNext( (cnt) -> System.out.printf("Rows deleted: %d%n", cnt)),
						c -> c.close()).then();
	}

	private Statement prepareStatement(Statement st, Vehicle vehicle) {
		return st.bind("$1", vehicle.getColor())
						.bind("$2", vehicle.getWheel())
						.bind("$3", vehicle.getSeat())
						.bind("$4", vehicle.getVehicleNo());
	}

	private Vehicle toVehicle(Row row) {
		return new Vehicle(row.get("VEHICLE_NO", String.class),
						row.get("COLOR", String.class),
						row.get("WHEEL", Integer.class),
						row.get("SEAT", Integer.class));
	}
}
