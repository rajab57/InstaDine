package com.cpcrew.instadine.models;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Event")
public class Event extends ParseObject {
	
	public Event() {
		super();
	}
//	public void setId(int id ) {
//		put("eid",id);
//	}
	
	public String getId() {
		return getObjectId();
	}
	
	public void setEventName(String eventName) {
		put("ename" , eventName);
	}
	
	public String getEventName() {
		return getString("ename");
	}
	
	public void setDate(String date) {
		put("date", date);
	}
	
	public String getDate() {
		return getString("date");
	}
	
	public void setGroup(Group group) {
		put("group", group);
	}
	
	public Group getGroup() {
		return (Group) getParseObject("group");
	}
	
	public void addSelection(String userid, String resid) {
		JSONObject value = new JSONObject();
		try {
			value.put("userid", userid);
			value.put("resid", resid);
			add("selections" ,value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public  List<JSONObject> getSelection() {
		return getList("selections");
	}
	
	public void addRejectedUser(User user) {
		add("rejectedusers" ,user);
	}
	
	public List<User> getRejectedUsers() {
		return getList("rejectedusers");
	}
	
//	public ParseRelation<Restaurant> getRestaurantRelation() {
//	      return getRelation("restaurants");
//	  }
//
//	  public void addRestaurant(Restaurant restaurant) {
//	    getRestaurantRelation().add(restaurant);
//	    saveInBackground();
//	  }
//
//	  public void removeRestaurant(Restaurant restaurant) {
//	     getRestaurantRelation().remove(restaurant);
//	     saveInBackground();
//	  }

}
