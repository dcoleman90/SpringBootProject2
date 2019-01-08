package io.javabrains.springbootstarter.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

	@Autowired
	private TopicService topicService;

	@RequestMapping("/topics")
	public List<Topic> getAllTopics() {
		return this.topicService.getAllTopics();
	}
	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable String id) {
		return this.topicService.getTopic(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/topics")
	public void addTopic(@RequestBody Topic addedTopic) {
		this.topicService.addTopic(addedTopic);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value = "/topics/{name}")
	public void updateTopic(@RequestBody Topic addedTopic, @PathVariable String name) {
		this.topicService.updateTopic(addedTopic, name);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value = "/topics/{name}")
	public void deleteTopic(@PathVariable String name) {
		this.topicService.deleteTopic(name);
	}
}
