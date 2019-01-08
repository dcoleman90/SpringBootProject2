package io.javabrains.springbootstarter.course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepostitory;
	private int initializeCounter = 0;


	/**
	 * This method generates values for the server - it is a bit of a hack but it does work
	 * 
	 * It cannot be called in the constructor as the server is not up and running yet
	 */
	private void initialize() {
		if (this.initializeCounter == 0) {
			this.initializeCounter++;

			List<Course> courses = new ArrayList<>(
					Arrays.asList(new Course("Spring", "Spring Framework", "Spring Framework Description"),
							new Course("A New Hope", "Episode 4", " It is a period of Civil war"),
							new Course("Empire Strikes Back", "Episode 5", "It is a dark time for the Rebellion"),
							new Course("Return of the Jedi", "Episode 6",
									"Luke Skywalker has returned to his home planet of Tatooine")));
			for (int count = 0; count < courses.size(); count++) {
				this.courseRepostitory.save(courses.get(count));
			}
		}
	}

	public List<Course> getAllCourses() {
		this.initialize();
		List<Course> courseList = new ArrayList<>();
		courseRepostitory.findAll().forEach(courseList::add);

		return courseList;
	}

	public void addCourse(Course addedCourse) {
		this.courseRepostitory.save(addedCourse);
	}

	public Course getCourse(String id) {
		this.initialize();
		return courseRepostitory.findById(id).orElse(null);

	}

	/**
	 * CourseRepiository is advanced enough that it looks for identical Id and can
	 * update them
	 * 
	 * An issue came when a user updated the id - this method would simply create a
	 * NEW course with the id and leave the old one The if loop deletes any issues
	 * with this and uses the more traditional update method - removing the old id
	 * and replacing it with a new one
	 */
	public void updateCourse(Course updatedCourse, String id) {
		if (this.courseRepostitory.findById(id).isPresent()) {
			this.courseRepostitory.deleteById(id);
		}
		this.courseRepostitory.save(updatedCourse);
	}

	public void deleteCourse(String id) {
		this.courseRepostitory.deleteById(id);
	}

}
