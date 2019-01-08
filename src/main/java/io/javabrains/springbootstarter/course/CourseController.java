package io.javabrains.springbootstarter.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping("/courses")
	public List<Course> getAllCourses() {
		return this.courseService.getAllCourses();
	}
	@RequestMapping("/courses/{id}")
	public Course getCourse(@PathVariable String id) {
		return this.courseService.getCourse(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/courses")
	public void addCourse(@RequestBody Course addedCourse) {
		this.courseService.addCourse(addedCourse);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value = "/courses/{name}")
	public void updateCourse(@RequestBody Course addedCourse, @PathVariable String name) {
		this.courseService.updateCourse(addedCourse, name);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value = "/courses/{name}")
	public void deleteCourse(@PathVariable String name) {
		this.courseService.deleteCourse(name);
	}
}
