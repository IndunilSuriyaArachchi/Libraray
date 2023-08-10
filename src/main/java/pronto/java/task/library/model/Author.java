package pronto.java.task.library.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="author")
public class Author {
	
	@Id
	@Column(name="authorid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String authorName;
	
	@ManyToMany(mappedBy = "authors")
	private Set<Book> books=new HashSet<>();
	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@Override
	public String toString() {
		return "Author [id=" + id + ", authorName=" + authorName + "]";
	}

	
	

}
