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

	private List<Topic> topics = new ArrayList<>(
			Arrays.asList(new Topic("Spring", "Spring Framework", "Spring Framework Description"),
					new Topic("A New Hope", "Episode 4", " It is a period of Civil war"),
					new Topic("Empire Strikes Back", "Episode 5", "It is a dark time for the Rebellion"),
					new Topic("Return of the Jedi", "Episode 6",
							"Luke Skywalker has returned to his home planet of Tatooine")));

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

	/**
	 * TopicRepiository is advanced enough that it looks for identical Id and can update them 
	 * 
	 * An issue came when a user updated the id - this method would simply create a NEW topic with the id and leave the old one
	 * The if loop deletes any issues with this and uses the more traditional update method - removing the old id and replacing it with a new one
	 */
	public void updateTopic(Topic updatedTopic, String id) {
		if(this.topicRepostitory.findById(id).isPresent()) {
			this.topicRepostitory.deleteById(id);
		}
		this.topicRepostitory.save(updatedTopic);
	}

	public void deleteTopic(String id) {
		this.topicRepostitory.deleteById(id);
	}

}
