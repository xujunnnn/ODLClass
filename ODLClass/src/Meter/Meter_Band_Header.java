package Meter;

import com.alibaba.fastjson.annotation.JSONField;

public class Meter_Band_Header {
	@JSONField(name="band-id")
	private String band_id;
	@JSONField(name="band-rate")
	private String band_rate;
	@JSONField(name="band-burst-size")
	private String band_burst_size;
	@JSONField(name="drop-rate")
	private String drop_rate;
	@JSONField(name="drop-burst-size")
	private String drop_burst_size;
	@JSONField(name="dscp-remark-burst-size")
	private String dscp_remark_burst_size;
	@JSONField(name="dscp-remark-rate")
	private String dscp_remark_rate;
	@JSONField(name="meter-band-types")
	private Meter_Band_Types meter_Band_Types;
	public String getDscp_remark_burst_size() {
		return dscp_remark_burst_size;
	}
	public Meter_Band_Header setDscp_remark_burst_size(String dscp_remark_burst_size) {
		this.dscp_remark_burst_size = dscp_remark_burst_size;
		return this;
	}
	public String getDscp_remark_rate() {
		return dscp_remark_rate;
	}
	public Meter_Band_Header setDscp_remark_rate(String dscp_remark_rate) {
		this.dscp_remark_rate = dscp_remark_rate;
		return this;
	}
	public String getPrec_level() {
		return prec_level;
	}
	public Meter_Band_Header setPrec_level(String prec_level) {
		this.prec_level = prec_level;
		return this;
	}
	private String prec_level;
	public String getBand_id() {
		return band_id;
	}
	public Meter_Band_Header setBand_id(String band_id) {
		this.band_id = band_id;
		return this;
	}
	public String getBand_rate() {
		return band_rate;
		
	}
	public Meter_Band_Header setBand_rate(String band_rate) {
		this.band_rate = band_rate;
		return this;
	}
	public String getBand_burst_size() {
		return band_burst_size;
		
	}
	public Meter_Band_Header setBand_burst_size(String band_burst_size) {
		this.band_burst_size = band_burst_size;
		return this;
	}
	public String getDrop_rate() {
		return drop_rate;
		
	}
	public Meter_Band_Header setDrop_rate(String drop_rate) {
		this.drop_rate = drop_rate;
		return this;
	}
	public String getDrop_burst_size() {
		return drop_burst_size;
	}
	public Meter_Band_Header setDrop_burst_size(String drop_burst_size) {
		this.drop_burst_size = drop_burst_size;
		return this;
	}
	public Meter_Band_Types getMeter_Band_Types() {
		return meter_Band_Types;
	}
	public Meter_Band_Header setMeter_Band_Types(Meter_Band_Types meter_Band_Types) {
		this.meter_Band_Types = meter_Band_Types;
        return this;
	}
	public Meter_Band_Header Set_Type(String type){
		Meter_Band_Types meter_Band_Types=new Meter_Band_Types();
		meter_Band_Types.setFlags(type);
		this.meter_Band_Types=meter_Band_Types;getClass();
		return this; 
	}

}
class Meter_Band_Types{
	private String flags;

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}
}
