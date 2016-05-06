package io.pivotal.tempProducer;
/**
 * @author patelj17
 *
 */
import com.google.gson.Gson;

public class TempSensor {

	private String id;
	private int alt;
	private float temp;

	public TempSensor() {
		
	}
	
	public TempSensor(String id, int alt, float temp) {
		this.id=id;
		this.alt=alt;
		this.temp=temp;
	}	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the date to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the altitude
	 */
	public int getAlt() {
		return alt;
	}
	/**
	 * @param alt the altitude to set
	 */
	public void setAlt(int alt) {
		this.alt = alt;
	}
	/**
	 * @return the temp
	 */
	public float getTemp() {
		return temp;
	}
	/**
	 * @param temp the temp to set
	 */
	public void setTemp(float temp) {
		this.temp = temp;
	}

	public String toString() {
		Gson gson = new Gson();
		String result = gson.toJson(this);
		return result;
	}
	
}
