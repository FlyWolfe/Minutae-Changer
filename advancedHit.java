package createHit;

import java.io.IOException;

import com.amazonaws.mturk.service.axis.RequesterService;
import com.amazonaws.mturk.service.exception.ServiceException;
import com.amazonaws.mturk.util.PropertiesClientConfig;
import com.amazonaws.mturk.requester.HIT;
import com.amazonaws.mturk.addon.HITDataOutput;
import com.amazonaws.mturk.addon.HITDataWriter;
import com.amazonaws.mturk.addon.HITProperties;
import com.amazonaws.mturk.addon.HITQuestion;
import com.amazonaws.mturk.addon.HITResults;
import com.amazonaws.mturk.addon.QAPValidator;

public class advancedHit {
	private RequesterService service;
	
	private String title = "Advanced Hit";
	private String description = "This is me testing java hits";
	private int maxAssignments = 100;
	private double reward = .05;
	private int selAnswer;
	private String questionFile ="advancedHit.question";
	private String id;
	
	//constructor
	public advancedHit()
	{
		service = new RequesterService(new PropertiesClientConfig("../mturk.properties"));
	}
	
	public void createHit()
	{
		try {
			HITQuestion question = new HITQuestion(questionFile);  
			QAPValidator.validate(question.getQuestion());
			HIT hit = service.createHIT(title, description, reward, question.getQuestion(), maxAssignments);
			System.out.println("Created HIT: " + hit.getHITId());
			System.out.println("HIT location: ");
			System.out.println(service.getWebsiteURL() + "/mturk/preview?groupId=" + hit.getHITTypeId());
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
		
		
	}
	
	public static void main(String[] args)
	{
		advancedHit app = new advancedHit();
		app.createHit();
		
	}
}
