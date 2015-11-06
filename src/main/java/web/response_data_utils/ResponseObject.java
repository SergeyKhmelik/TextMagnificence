package web.response_data_utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is needed to make all responding
 * data match a single pattern, which is:
 * {
 *    data:
 *    		[
 *    			... here is response data
 *    		]
 * }
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
