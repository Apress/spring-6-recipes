package com.apress.spring6recipes.court.web.view;

import com.apress.spring6recipes.court.domain.Reservation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class ExcelReservationSummary extends AbstractXlsxView {

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
																		HttpServletRequest request, HttpServletResponse response) {
		@SuppressWarnings({ "unchecked" })
		var reservations = (List<Reservation>) model.get("reservations");
		var sheet = workbook.createSheet();

		addHeaderRow(sheet);

		reservations.forEach(reservation -> createRow(sheet, reservation));
	}

	private void addHeaderRow(Sheet sheet) {
		var header = sheet.createRow(0);
		header.createCell(0).setCellValue("Court Name");
		header.createCell(1).setCellValue("Date");
		header.createCell(2).setCellValue("Hour");
		header.createCell(3).setCellValue("Player Name");
		header.createCell(4).setCellValue("Player Phone");
	}

	private void createRow(Sheet sheet, Reservation reservation) {
		var row = sheet.createRow(sheet.getLastRowNum() + 1);
		row.createCell(0).setCellValue(reservation.getCourtName());
		row.createCell(1).setCellValue(DATE_FORMAT.format(reservation.getDate()));
		row.createCell(2).setCellValue(reservation.getHour());
		row.createCell(3).setCellValue(reservation.getPlayer().getName());
		row.createCell(4).setCellValue(reservation.getPlayer().getPhone());
	}
}
