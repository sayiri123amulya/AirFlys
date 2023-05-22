import java.util.Date;

public class Flights {
   
	private int id;
	private String flight_number; 
	private String flight_name;
	private String start_source ;
	private String final_destination ;
	private Date date_of_travel ;
	private int seats_available;
	private int ticket_price;
	
	public Flights(int iD,String flight_number,String flight_name,String start_source,String final_destination,Date date_of_travel,int seats_available,int ticket_price) {
		id=iD;
		this.flight_number = flight_number;
		this.flight_name = flight_name;
		this.start_source = start_source;
		this.final_destination = final_destination;
		this.date_of_travel = date_of_travel;
		this.seats_available = seats_available;
		this.setTicket_price(ticket_price);
	}
	
	public Flights() {
		
	}
	public String getFlight_number() {
		return flight_number;
	}
	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}
	public String getFlight_name() {
		return flight_name;
	}
	public void setFlight_name(String flight_name) {
		this.flight_name = flight_name;
	}
	public String getStart_source() {
		return start_source;
	}
	public void setStart_source(String start_source) {
		this.start_source = start_source;
	}
	public String getFinal_destination() {
		return final_destination;
	}
	public void setFinal_destination(String final_destination) {
		this.final_destination = final_destination;
	}
	public Date getDate_of_travel() {
		return date_of_travel;
	}
	public void setDate_of_travel(Date date_of_travel) {
		this.date_of_travel = date_of_travel;
	}
	public int getSeats_available() {
		return seats_available;
	}
	public void setSeats_available(int seats_available) {
		this.seats_available = seats_available;
	}

	public int getId() {
		return id;
	}

	public void setId(int iD) {
		this.id = iD;
	}

	public int getTicket_price() {
		return ticket_price;
	}

	public void setTicket_price(int ticket_price) {
		this.ticket_price = ticket_price;
	}
	
	
}
