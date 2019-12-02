package terraform.resources.aws.database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import exceptions.InvalidTypeException;

public class TimeWindow {

	private Date startTime;//hh:mm
	private Date endTime;//hh:mm	
	private String day;///ddd
	
	public static final Set<String> DAYS;
	static {
		DAYS = new HashSet<String>();
		DAYS.add("mon");
		DAYS.add("tue");
		DAYS.add("wed");
		DAYS.add("thu");
		DAYS.add("fri");
		DAYS.add("sat");
		DAYS.add("sun");
	}
	
	public TimeWindow(String startTime, String endTime) throws InvalidTypeException {
		super();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		try{
			this.startTime = format.parse(startTime);
			this.endTime = format.parse(endTime);
		}catch(ParseException e){
			System.out.println("startTime and endTime should be formatted as HH:mm");
			e.printStackTrace();
		}		
		if(this.startTime.getTime() - this.endTime.getTime() >= 0){
//			System.out.println(this.startTime.getTime() - this.endTime.getTime());
//			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");	    
//			System.out.println(formatter.format(startTime) + "-" + formatter.format(endTime));
			throw new InvalidTypeException("startTime must be less than startTime");
		}
	}
	
	/**
	 * 
	 * @param startHour
	 * @param endHour
	 * @param startMins
	 * @param endMins
	 * @param day
	 * @throws InvalidTypeException 
	 */
	public TimeWindow(String startTime, String endTime, String day) throws InvalidTypeException {
		super();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		try{
			this.startTime = format.parse(startTime);
			this.endTime = format.parse(endTime);
		}catch(ParseException e){
			System.out.println("startTime and endTime should be formatted as hh:mm");
			e.printStackTrace();
		}		
		if(this.startTime.getTime() - this.endTime.getTime() >= 0){
			throw new InvalidTypeException("startTime must be less than startTime");
		}
		if(!DAYS.contains(day.toLowerCase())){
			throw new InvalidTypeException("Invalid day value. Possible values are week days in ddd format");
		}
		this.day = day;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the days
	 */
	public static Set<String> getDays() {
		return DAYS;
	}

	public String getTimeWindow(){
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");	    
	    return formatter.format(startTime) + "-" + formatter.format(endTime);
	}
	
	public String getDayTimeWindow(){
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");	
		return day + ":" + formatter.format(startTime) + "-" + day + ":" + formatter.format(endTime);
	}
	
	/**
	 * Checks if this time-window overlaps with another one
	 * @param tw
	 * @return
	 */
	public boolean hasOverlap(TimeWindow tw){
		if(!startTime.after(tw.getEndTime()) && !tw.getStartTime().after(endTime)){
			return true;
		}
		return false;
	}
}
