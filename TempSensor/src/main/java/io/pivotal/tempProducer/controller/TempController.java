package io.pivotal.tempProducer.controller;



import io.pivotal.tempProducer.TempProducer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {
	
	//@Autowired
	//ServletContext context;
	static TempProducer generator = new TempProducer();
	static Thread threadSender = new Thread (generator);
	static final Logger logger = LogManager.getLogger(TempController.class);
	
	
	public TempController() {
		if (threadSender.isAlive()) {
			logger.warn("Thread is alive and running");
		}
		
	}
		
	@RequestMapping("/")
	public String index() {
		generator.stopGen();
		return "Stopped generating!";
	}
	
	@RequestMapping("/start")
	public void start() {
		generator.startGen();
		generator.run();
	}
	
	@RequestMapping("/stop")
	public String stop() {
		generator.stopGen();
		return "Stopped generating!";
	}

}




