package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
    private String title;
    private String memo;
    private String current_date;
	private String category;
	private String due_date;
	private int id;


    public TodoItem(String title,String memo, String category,String due_date){
    	super();
        this.category=category;
    	this.title=title;
        this.memo=memo;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.due_date=due_date;
        this.current_date=f.format(new Date());
    }
    
    public TodoItem(String title,String memo, String category,String due_date,String time){
    	this.category=category;
        this.title=title;
        this.memo=memo;
        this.due_date=due_date;
        this.current_date=time;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return memo;
    }

    public void setDesc(String memo) {
        this.memo = memo;
    }

    public String toSaveString() {
		return category+"##"+title+"##"+memo+"##"+due_date+"##"+current_date+"\n";
	}

	public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
	public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
	public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
    
    @Override
    public String toString() {
    	return id+" ["+category+"] " +title + " - "+ memo + " - " + due_date + " - " +current_date;
    }

	public void setId(int id) {
		this.id=id;
	}

	public int getId() {
		return id;
	}
}
