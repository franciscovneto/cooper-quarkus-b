package br.cooper.quarkus.b;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Util {

	public static final SimpleDateFormat FMT_DT = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
	
	public static Date incrementarOuDecrementarDia(Date date, int i) {
		GregorianCalendar calendar = (GregorianCalendar) getCalendarFromDate(date);
		calendar.add(GregorianCalendar.DAY_OF_MONTH, i);
		return new Date(calendar.getTimeInMillis());
	}

	private static Calendar getCalendarFromDate(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;
	}
	
	public static String formatarData(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-YYYY");
		return sdf.format(data);
	}

	public static Boolean validarDataSeDiaUtil(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer isFinaldeSemana = cal.get(Calendar.DAY_OF_WEEK);
		return isFinaldeSemana.compareTo(Calendar.SATURDAY) != 0 && isFinaldeSemana.compareTo(Calendar.SUNDAY) != 0;
	}
	
	public static Date getDateFromXlsx(String dateStr) throws ParseException {
		String[] data = dateStr.split("-");
		String mes = data[0];
		String dia = data[1];
		String ano = data[2];
		return FMT_DT.parse(dia + "/" + mes + "/" + ano);
	}
	
	public static Date recuperaUltimoDiaUtil(Date date) {
		
		Date novaData = incrementarOuDecrementarDia(date, -1);

		if(validarDataSeDiaUtil(novaData)) {
			return novaData;
		} else {
			return recuperaUltimoDiaUtil(incrementarOuDecrementarDia(novaData, -1));
		}
	}
}