package Meter;

import java.util.ArrayList;

import com.alibaba.fastjson.annotation.JSONField;

public class Meter_Band_Headers {
	public ArrayList<Meter_Band_Header> getMeter_Band_Headers() {
		return meter_Band_Headers;
	}
	public Meter_Band_Headers setMeter_Band_Headers(
			ArrayList<Meter_Band_Header> meter_Band_Headers) {
		this.meter_Band_Headers = meter_Band_Headers;
		return this;
	}
	@JSONField(name="meter-band-header")
	private ArrayList<Meter_Band_Header> meter_Band_Headers=new ArrayList<Meter_Band_Header>();
    public Meter_Band_Headers addMeter_Band_Header(Meter_Band_Header meter_Band_Header){
    	this.meter_Band_Headers.add(meter_Band_Header);
    	return this;
    }

}
