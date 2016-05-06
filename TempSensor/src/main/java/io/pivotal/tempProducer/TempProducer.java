/**
 * 
 */
package io.pivotal.tempProducer;

import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author patelj17
 *
 */
public class TempProducer implements Runnable {
	static final Logger logger = LogManager.getLogger(TempProducer.class);

	private boolean generating = false;
	static final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


	public void startGen(){
		this.generating = true;
	}

	public void stopGen(){
		this.generating = false;
	}

	public String randomString( int len ) 
	{
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		return sb.toString();
	}
	/**
	 * 
	 */
	public TempProducer() {
		// TODO Auto-generated constructor stub
	}

	public synchronized void run() {
		logger.info("In Run");

		while (true){
			if (!generating){
				try {
					Thread.sleep(1000);
					break;
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				//return;
			}else if (generating){
				Random random = new Random();
				while (random.nextInt(100) != 23) {

					try {
						float minX=30.0f;
						float maxX=100.0f;

						logger.debug("Generating");
						
						int id = (1+random.nextInt(99));
						logger.debug("id : " + id);
						String vin = randomString(2);					
						logger.debug("vin :" + String.valueOf(id) + vin);
						int alt = 3000+random.nextInt(2500);
						logger.debug("alt :" + alt);
						float temp = random.nextFloat() * (maxX - minX) + minX;
						logger.debug("temp :" + temp);
						TempSensor t = new TempSensor(String.valueOf(id) + vin, alt, temp);
						logger.info(t.toString());
						//Need to do this correctly in PCF
						HttpClient httpClient = new DefaultHttpClient();
						HttpPost request = new HttpPost("http://localhost:8888");
						StringEntity strTemp = new StringEntity(t.toString());
						request.setEntity(strTemp);
						request.setHeader("Content-Type", "application/json");
						logger.info(strTemp);
						HttpResponse response = httpClient.execute(request);
						logger.info(response);

					} catch (Exception e1) {
						throw new RuntimeException(e1);
					} 


				}//end while with counter
				this.stopGen();
			} //end else if


		}//end while
	} //end run 

}