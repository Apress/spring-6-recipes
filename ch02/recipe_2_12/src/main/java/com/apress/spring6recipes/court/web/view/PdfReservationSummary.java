package com.apress.spring6recipes.court.web.view;

import com.apress.spring6recipes.court.domain.Reservation;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class PdfReservationSummary extends AbstractPdfView {

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
																	HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		var reservations = (List<Reservation>) model.get("reservations");
		var table = new Table(5);
		addTableHeader(table);
		reservations.forEach(reservation -> addContent(table, reservation));
		document.add(table);
	}

	private void addContent(Table table, Reservation reservation) throws BadElementException {
		table.addCell(reservation.getCourtName());
		table.addCell(DATE_FORMAT.format(reservation.getDate()));
		table.addCell(Integer.toString(reservation.getHour()));
		table.addCell(reservation.getPlayer().getName());
		table.addCell(reservation.getPlayer().getPhone());
	}

	private void addTableHeader(Table table) throws BadElementException {
		table.addCell("Court Name");
		table.addCell("Date");
		table.addCell("Hour");
		table.addCell("Player Name");
		table.addCell("Player Phone");
	}
}
