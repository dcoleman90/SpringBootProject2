package io.javabrains.springbootstarter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepostitory;
	
	private List<Topic> topics = new ArrayList<>(Arrays.asList(new Topic("Spring", "Spring Framework", "Spring Framework Description"),
			new Topic("A New Hope", "Episode 4", " It is a period of Civil war"),
			new Topic("Empire Strikes Back", "Episode 5", "It is a dark time for the Rebellion"),
			new Topic("Return of the Jedi", "Episode 6", "Luke Skywalker has returned to his home planet of Tatooine")));

	
	
	public List<Topic> getAllTopics() {
		List<Topic> topicList = new ArrayList<>();
		topicRepostitory.findAll().forEach(topicList::add);
		
		return topicList;
	}
	
	
	public void addTopic(Topic addedTopic) {
		this.topicRepostitory.save(addedTopic);
	}

	public Topic getTopic(String id) {
		return topicRepostitory.findById(id).orElse(null);
		
	}
	
	public void updateTopic(Topic updatedTopic, String id) {
		for (int count = 0; count < this.topics.size(); count ++) {
			if (this.topics.get(count).getId().equals(id)) {
				this.topics.set(count, updatedTopic);
			}
		}
	}
	
	public void deleteTopic(String id) {
		this.topics.removeIf(topic -> topic.getId().equals(id));
	}

}
