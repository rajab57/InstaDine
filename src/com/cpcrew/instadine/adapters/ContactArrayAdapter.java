package com.cpcrew.instadine.adapters;

import java.util.ArrayList;
import java.util.HashSet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.cpcrew.instadine.R;
import com.cpcrew.instadine.models.User;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;

public class ContactArrayAdapter extends ArrayAdapter<User> {
	
	HashSet<String> mSelContacts;
	public ArrayList<Boolean> checkedUsers; //workaround

	public ContactArrayAdapter(Context context, ArrayList<User> contacts , HashSet<String> selContacts) {
		super(context, 0, contacts);
		mSelContacts = new HashSet<String>(selContacts);
		//mSelContacts = selContacts;
		checkedUsers = new ArrayList<Boolean>();
	}

	private static class ViewHolder {
		TextView tvName;
		CircularImageView pivPhoto;
		ImageView ivSelected;
		CheckBox cbSelect;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		User user = getItem(position);
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflator = LayoutInflater.from(getContext());
			convertView = inflator
					.inflate(R.layout.contact_item, parent, false);
			viewHolder.pivPhoto = (CircularImageView) convertView
					.findViewById(R.id.ivPhoto);
			viewHolder.pivPhoto.setBorderWidth(2);
			viewHolder.pivPhoto.addShadow();
			viewHolder.tvName = (TextView) convertView
					.findViewById(R.id.tvName);
			viewHolder.ivSelected = (ImageView)convertView.findViewById(R.id.ivSelected);
			viewHolder.cbSelect = (CheckBox)convertView.findViewById(R.id.cbSelect);
			convertView.setTag(viewHolder);
		}
		viewHolder = (ViewHolder) convertView.getTag();
		viewHolder.tvName.setText(user.getFirstName() + " "
				+ user.getLastName());
		
		if (isAlreadySelected(user)) {
			viewHolder.cbSelect.setVisibility(View.INVISIBLE);
			viewHolder.ivSelected.setVisibility(View.VISIBLE);
			checkedUsers.set(position, true);
		} else {
			viewHolder.ivSelected.setVisibility(View.INVISIBLE);
			viewHolder.cbSelect.setChecked(false);
			checkedUsers.set(position, false);
			viewHolder.cbSelect.setTag(position);
			addClickHandlerToCheckBox(viewHolder.cbSelect);
		}
		// Load the image TODO - Verify the right API

		ImageLoader imageLoader = ImageLoader.getInstance();
		//populate view with tweet data
		imageLoader.displayImage("https://graph.facebook.com/" + user.getFacebookId() + "/picture?type=large", viewHolder.pivPhoto);
		return convertView;
	}
	
	private boolean isAlreadySelected(User user) {
		
		if ( mSelContacts != null ) {
			if ( mSelContacts.contains(user.getId()))
				return true;
		}
		return false;
	}
	
	// wrokaround
	// nightmare because getcheckedItems wont work
	// http://stackoverflow.com/questions/3996938/why-is-listview-getcheckeditempositions-not-returning-correct-values
	private void addClickHandlerToCheckBox(CheckBox checkbox)
	{
	    checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	    public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
	        CheckBox checkbox = (CheckBox)arg0; 
	        boolean isChecked = checkbox.isChecked();
	        int position = (Integer) checkbox.getTag();
	        checkedUsers.set(position,isChecked);
	        
	    }
	      });
	}
	
	// wrokaround after the adapter is populated
	public void selectNone() {
		int cnt = this.getCount();
		for (int i = 0; i < cnt ; ++ i) {
			checkedUsers.add(false);
		}
	}
	
	public void addSelectedUsers(HashSet<String> users) {
		mSelContacts = users;
	}
	
}
