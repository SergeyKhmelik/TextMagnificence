package web.data_utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koloturka
 * @version 18.08.2015 14:31
 */
public class ResponseObject<T> {

	private ArrayList<T> data;

	public ResponseObject(T genericData) {
		if(genericData instanceof List){
			this.data = (ArrayList<T>) genericData;
		} else {
			this.data = new ArrayList<T>();
			data.add(genericData);
		}
	}

	public ArrayList<T> getData() {
		return data;
	}

	public void setData(ArrayList<T> data) {
		this.data = data;
	}
}
