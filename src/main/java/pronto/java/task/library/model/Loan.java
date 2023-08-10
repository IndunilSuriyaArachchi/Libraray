package pronto.java.task.library.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="loan")
public class Loan {

	@Id
	@Column(name="loanid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userid", nullable=false)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bookid", nullable=false)
	private Book book;
	
	@Column(nullable = false)
	private Date borrowDate;
	
	private Date returnDate;
	private double fineAmount;
	private boolean pending;
	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public double getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}
	
	public boolean isPending() {
		return pending;
	}
	public void setPending(boolean pending) {
		this.pending = pending;
	}
	@Override
	public String toString() {
		return "Loan [id=" + id + ", user=" + user + ", book=" + book + ", borrowDate=" + borrowDate + ", returnDate="
				+ returnDate + ", fineAmount=" + fineAmount + ", pending=" + pending + "]";
	}

	
	

	
	
	
	
	
}
