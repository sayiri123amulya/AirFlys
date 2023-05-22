
public class booking {
    
	private int id;
    private int booking_ID;
    private String flight_number;
    private String passenger_name;
    private int passenger_age;
    private String passenger_unique_id;
    private String passenger_gender;
    private int seat_number;
    private String username;
    private int seq;
    
    
    public booking() {
    	
    }
    public booking(int Id,int booking_ID,
    String flight_number,
    String passenger_name,
    int passenger_age,
    String passenger_unique_id,
    String passenger_gender,
    int seat_number, String username, int seq) {
    	
    	id=Id;
    	this.username=username;
    	this.booking_ID=booking_ID;
    	this.flight_number=flight_number;
    	this.passenger_name=passenger_name;
    	this.passenger_age=passenger_age;
    	this.passenger_unique_id=passenger_unique_id;
    	this.passenger_gender=passenger_gender;
    	this.seat_number=seat_number;
    	this.setSeq(seq);
    	
    }
	public int getBooking_ID() {
		return booking_ID;
	}
	public void setBooking_ID(int booking_ID) {
		this.booking_ID = booking_ID;
	}
	public String getFlight_number() {
		return flight_number;
	}
	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}
	public String getPassenger_name() {
		return passenger_name;
	}
	public void setPassenger_name(String passenger_name) {
		this.passenger_name = passenger_name;
	}
	public int getPassenger_age() {
		return passenger_age;
	}
	public void setPassenger_age(int passenger_age) {
		this.passenger_age = passenger_age;
	}
	public String getPassenger_unique_id() {
		return passenger_unique_id;
	}
	public void setPassenger_unique_id(String passenger_unique_id) {
		this.passenger_unique_id = passenger_unique_id;
	}
	public String getPassenger_gender() {
		return passenger_gender;
	}
	public void setPassenger_gender(String passenger_gender) {
		this.passenger_gender = passenger_gender;
	}
	public int getSeat_number() {
		return seat_number;
	}
	public void setSeat_number(int seat_number) {
		this.seat_number = seat_number;
	}
	public int getId() {
		return id;
	}
	public void setId(int Id) {
		id = Id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
}
