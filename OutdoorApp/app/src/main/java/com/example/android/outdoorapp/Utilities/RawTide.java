package com.example.android.outdoorapp.Utilities;

import java.util.ArrayList;

public class RawTide {
	private String tideInfoSite;
	private String tideInfoLat;
	private String tideInfoLon;
	private String tideInfoUnits;
	private String tideInfoType;
	private String tideInfoTZName;
	private String tideInfoObsEpoch;
	private String tideInfoHeight;
	private String tideInfoMaxHeight;
	private String tideInfoMinHeight;
	private ArrayList<RawTideObs> rawTideObsList = new ArrayList<RawTideObs>();
	
	
	
	
	public ArrayList<RawTideObs> getRawTideObsList() {
		return rawTideObsList;
	}
	public void setRawTideObsList(ArrayList<RawTideObs> rawTideObsList) {
		this.rawTideObsList = rawTideObsList;
	}
	public String getTideInfoSite() {
		return tideInfoSite;
	}
	public void setTideInfoSite(String tideInfoSite) {
		this.tideInfoSite = tideInfoSite;
	}
	public String getTideInfoLat() {
		return tideInfoLat;
	}
	public void setTideInfoLat(String tideInfoLat) {
		this.tideInfoLat = tideInfoLat;
	}
	public String getTideInfoLon() {
		return tideInfoLon;
	}
	public void setTideInfoLon(String tideInfoLon) {
		this.tideInfoLon = tideInfoLon;
	}
	public String getTideInfoUnits() {
		return tideInfoUnits;
	}
	public void setTideInfoUnits(String tideInfoUnits) {
		this.tideInfoUnits = tideInfoUnits;
	}
	public String getTideInfoType() {
		return tideInfoType;
	}
	public void setTideInfoType(String tideInfoType) {
		this.tideInfoType = tideInfoType;
	}
	public String getTideInfoTZName() {
		return tideInfoTZName;
	}
	public void setTideInfoTZName(String tideInfoTZName) {
		this.tideInfoTZName = tideInfoTZName;
	}
	public String getTideInfoObsEpoch() {
		return tideInfoObsEpoch;
	}
	public void setTideInfoObsEpoch(String tideInfoObsEpoch) {
		this.tideInfoObsEpoch = tideInfoObsEpoch;
	}
	public String getTideInfoHeight() {
		return tideInfoHeight;
	}
	public void setTideInfoHeight(String tideInfoHeight) {
		this.tideInfoHeight = tideInfoHeight;
	}
	public String getTideInfoMaxHeight() {
		return tideInfoMaxHeight;
	}
	public void setTideInfoMaxHeight(String tideInfoMaxHeight) {
		this.tideInfoMaxHeight = tideInfoMaxHeight;
	}
	public String getTideInfoMinHeight() {
		return tideInfoMinHeight;
	}
	public void setTideInfoMinHeight(String tideInfoMinHeight) {
		this.tideInfoMinHeight = tideInfoMinHeight;
	}
	
	
}
