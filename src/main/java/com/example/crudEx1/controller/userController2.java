package com.example.crudEx1.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.crudEx1.Exception.usernotfoundException;
import com.example.crudEx1.Repository.postRepository;
import com.example.crudEx1.Repository.userRepository;
import com.example.crudEx1.bean.Posts;
import com.example.crudEx1.bean.user;

@RestController

public class userController2 {
	@Autowired
	userRepository repo;
	@Autowired
	postRepository prepo;

	@RequestMapping(value = "/jpa/users", method = RequestMethod.GET)
	public List<user> getallusers(user usr) {
		return repo.findAll();	}

	@RequestMapping(value = "/jpa/users/{id}")
	//Resource<user> for hateous
	public Optional<user> finone(@PathVariable int id) {
		Optional<user> user = repo.findById(id);
		if (user == null)
			throw new usernotfoundException("id" + id);
		//HATEOAS
		//Resource<user> res=new Resource<user>();
	//	ControllerLinkBuilder linkto=linkTo(methodOn(this.getClass()).getallusers());
		//res.add(linkTo.withrEl("all-users"));
		return user;
	}

	@RequestMapping(value = "/jpa/users", method = RequestMethod.POST)
	public ResponseEntity<Object> save(@Valid @RequestBody user usr) {
		user saved = repo.save(usr);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	/*
	 * @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE) public
	 * void delete(@PathVariable int id) { user usr=dao.delte(id); if(usr==null)
	 * throw new usernotfoundException("id"+id);
	 * 
	 * URI location =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
	 * (deleted.getId()) .toUri();
	 * 
	 * }
	 */
	
	
	@RequestMapping(value = "/jpa/users/{id}", method = RequestMethod.DELETE)
	public  void delete(@PathVariable int id) {
		repo.deleteById(id);

		/*
		 * URI location =
		 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
		 * (deleted.getId()) .toUri();
		 */
	}

	@RequestMapping(value = "/jpa/users/{id}/posts", method = RequestMethod.GET)
	public List<Posts> getalluserspost(@PathVariable int id) {
		Optional<user> userop=repo.findById(id);
		if(!userop.isPresent()) {
			throw new usernotfoundException("id-" +id);
		}
		return userop.get().getPost();		
		}
	
	@RequestMapping(value = "/jpa/users/{id}/posts", method = RequestMethod.POST)
	public ResponseEntity<Object> savePost(@PathVariable int id,@RequestBody Posts post) {
		Optional<user> userop=repo.findById(id);
		if(!userop.isPresent()) {
			throw new usernotfoundException("id-" +id);
		}
		user saved = userop.get();
		post.setUsr(saved);
prepo.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getPostId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
