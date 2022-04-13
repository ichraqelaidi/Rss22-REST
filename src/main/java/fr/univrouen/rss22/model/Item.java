package fr.univrouen.rss22.model;

import java.sql.Date;

import javax.persistence.*;


@Entity
@Table(name = "item")
public class Item {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int guid;
	private String title;
	private Date date;
	
	public int getGuid() {
		return guid;
	}
	public void setGuid(int guid) {
		this.guid = guid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Item(int guid, String title, Date date) {
		super();
		this.guid = guid;
		this.title = title;
		this.date = date;
	}
	public Item() {}
	@Override
	public String toString() {
		return ("Article : " + title + "\n(" + guid + ") Le = " + date );
	}
}
