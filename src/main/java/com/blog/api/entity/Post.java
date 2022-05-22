package com.blog.api.entity;

import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name = "title",length = 100,nullable = false)
	private String title;
	
	@Column(length = 1000)
	private String content;
	private String imageName;
	private Date addedDate;
	
	@ManyToOne()
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "post")
	private Set<Comment> comments=new HashSet<Comment>();
}