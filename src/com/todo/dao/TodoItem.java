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
	private int is_completed;
	private int importance;
	private String member;

    public TodoItem(String title,String memo, String category,String due_date,int is_completed,int importance,String member){
    	super();
        this.category=category;
    	this.title=title;
        this.memo=memo;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.due_date=due_date;
        this.current_date=f.format(new Date());
        this.is_completed=is_completed;
        this.importance=importance;
        this.member=member;
    }
    
    public TodoItem(String title,String memo, String category,String due_date,String time,int is_completed,int importance,String member){
    	this.category=category;
        this.title=title;
        this.memo=memo;
        this.due_date=due_date;
        this.current_date=time;
        this.is_completed=is_completed;
        this.importance=importance;
        this.member=member;
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
    	if(is_completed==0) {
    		return id+". 중요도:"+importance+" [X] "+" ["+category+"] " +title + " - "+ memo+" [with "+member+"] " +" - " + due_date + " - " +current_date + importance;
    	}
    	else {
    		return id+". 중요도:"+importance+" [O] "+" ["+category+"] " +title + " - "+ memo+" [with "+member+"] " +" - " + due_date + " - " +current_date + importance;
    	}
    }

	public void setId(int id) {
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}
}
