package co.edu.uco.crosscuting.util.date;


import static co.edu.uco.crosscutting.util.object.UtilObject.getUtilObject;

import java.util.Date;

public class UtilDate {
		
		private static final UtilDate INSTANCE = new UtilDate();

		private UtilDate() {
			
		}
		
		public static UtilDate getUtilDate() {
			return INSTANCE;
		}
		
		public boolean isNull(Date date) {
			return getUtilObject().isNull(date);
		}
		
		public Date getDefault(Date value) {
			return getUtilObject().getDefault(value, new Date());
		}
		
		public boolean isBetween(Date date, Date init, Date end) {
			return (date.after(init) && date.before(end));
		}
		
		public boolean isBetweenIncludingInit(Date date, Date init, Date end) {
			return (isBetween(date,init,end) || date.equals(init));
		}
		
		public boolean isBetweenIncludingEnd(Date date, Date init, Date end) {
			return (isBetween(date,init,end) || date.equals(end));
		}
		
		public boolean isBetweenIncludingRanges(Date date, Date init, Date end) {
			return (isBetweenIncludingEnd(date,init,end) || isBetweenIncludingInit(date, init, end));
		}
		
	
}
