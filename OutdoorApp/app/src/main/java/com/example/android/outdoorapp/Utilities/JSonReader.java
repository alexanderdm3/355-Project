package com.example.android.outdoorapp.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class JSonReader {
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }
	  
	  
	  public static WeatherDataPoint createDataPoint(String url) throws IOException, JSONException {
		  WeatherDataPoint wdp = new WeatherDataPoint();
		  JSONObject json = readJsonFromUrl(url);
		  JSONObject wthr = (JSONObject) json.get("current_observation");
		  
		  wdp.setStationID(wthr.getString("station_id"));
		  wdp.setObserveTime(wthr.getString("observation_time"));
		  wdp.setObserveTimeRFC(wthr.getString("observation_time_rfc822"));
		  wdp.setObserveEpoch(wthr.getString("observation_epoch"));
		  wdp.setLocalTZShort(wthr.getString("local_tz_short"));
		  wdp.setLocalTZLong(wthr.getString("local_tz_long"));
		  wdp.setLocalTZOffset(wthr.getString("local_tz_offset"));
		  wdp.setWeather(wthr.getString("weather"));
		  wdp.setTempString(wthr.getString("temperature_string"));
		  wdp.setTempF(wthr.get("temp_f").toString());
		  wdp.setTempC(wthr.get("temp_c").toString());
		  wdp.setRelHumid(wthr.getString("relative_humidity"));
		  wdp.setWind(wthr.getString("wind_string"));
		  wdp.setWindDir(wthr.getString("wind_dir"));
		  wdp.setWindDeg(wthr.get("wind_degrees").toString());
		  wdp.setWindMPH(wthr.get("wind_mph").toString());
		  wdp.setWindGustMPH(wthr.get("wind_gust_mph").toString());
		  wdp.setWindKPH(wthr.get("wind_kph").toString());
		  wdp.setWindGustKPH(wthr.get("wind_gust_kph").toString());
		  wdp.setPressureMB(wthr.getString("pressure_mb"));
		  wdp.setPressureIN(wthr.getString("pressure_in"));
		  wdp.setPressureTrend(wthr.getString("pressure_trend"));
		  wdp.setDewpoint(wthr.getString("dewpoint_string"));
		  wdp.setDewpointF(wthr.get("dewpoint_f").toString());
		  wdp.setDewpointC(wthr.get("dewpoint_c").toString());
		  wdp.setHeatIndex(wthr.getString("heat_index_string"));
		  wdp.setHeatIndexF(wthr.get("heat_index_f").toString());
		  wdp.setHeatIndexC(wthr.get("heat_index_c").toString());
		  wdp.setWindchill(wthr.getString("windchill_string"));
		  wdp.setWindchillF(wthr.get("windchill_f").toString());
		  wdp.setWindchillC(wthr.get("windchill_c").toString());
		  wdp.setFeelsLike(wthr.getString("feelslike_string"));
		  wdp.setFeelslikeF(wthr.get("feelslike_f").toString());
		  wdp.setFeelslikeC(wthr.get("feelslike_c").toString());
		  wdp.setVisibilityMI(wthr.get("visibility_mi").toString());
		  wdp.setVisibilityKM(wthr.get("visibility_km").toString());
		  wdp.setSolorRadiation(wthr.getString("solarradiation"));
		  wdp.setuV(wthr.getString("UV"));
		  wdp.setPrecipOneHourIN(wthr.getString("precip_1hr_in"));
		  wdp.setPrecipOneHourMetric(wthr.getString("precip_1hr_metric"));
		  wdp.setPrecipToday(wthr.getString("precip_today_string"));
		  wdp.setPrecipTodayIN(wthr.getString("precip_today_in"));
		  wdp.setStationID(wthr.getString("precip_today_metric"));
		  wdp.setIcon(wthr.getString("icon"));
		  wdp.setIconURL(wthr.getString("icon_url"));
		  wdp.setForecastURL(wthr.getString("forecast_url"));
		  
		  
		  return wdp;
	  }
	  
	  public static ZipcodeData zipCodeData(String url) throws IOException, JSONException{
		  ZipcodeData zip = new ZipcodeData();
		  JSONObject json = readJsonFromUrl(url);
		  JSONObject next = (JSONObject) json.get("location");
		  
		  zip.setType(next.get("type").toString());
		  zip.setCountry(next.get("country").toString());
		  zip.setCountryISO(next.get("country_iso3166").toString());
		  zip.setCountryName(next.get("country_name").toString());
		  zip.setCity(next.get("city").toString());
		  zip.setTzShort(next.get("tz_short").toString());
		  zip.setTzLong(next.get("tz_long").toString());
		  zip.setLatitude(next.get("lat").toString());
		  zip.setLongitude(next.get("lon").toString());
		  zip.setZip(next.get("zip").toString());
		  zip.setMagic(next.get("magic").toString());
		  zip.setWmo(next.get("wmo").toString());
		  zip.setL(next.get("l").toString());
		  zip.setRequestUrl(next.get("requesturl").toString());
		  zip.setWuiURL(next.get("wuiurl").toString());
		  
		  
		  return zip;
	  }
	  
	  public static ArrayList<NearbyPWS> nBPWSData(String url) throws IOException, JSONException{
		  ArrayList<NearbyPWS> near = new ArrayList<NearbyPWS>();
		  JSONObject json = readJsonFromUrl(url);
		  JSONObject first = (JSONObject) json.get("location");
		  JSONObject next = (JSONObject) first.get("nearby_weather_stations");
		  JSONObject next2 = (JSONObject) next.get("pws");
		  JSONArray array = next2.getJSONArray("station");
		  
		  for (int i = 0; i < array.length(); i++){
			  NearbyPWS temp = new NearbyPWS();
			  temp.setPwsStationNbhd(array.getJSONObject(i).get("neighborhood").toString());
			  temp.setPwsStationCity(array.getJSONObject(i).get("city").toString());
			  temp.setPwsStationState(array.getJSONObject(i).get("state").toString());
			  temp.setPwsStationCountry(array.getJSONObject(i).get("country").toString());
			  temp.setPwsStationID(array.getJSONObject(i).get("id").toString());
			  temp.setPwsStationLat(array.getJSONObject(i).get("lat").toString());
			  temp.setPwsStationLon(array.getJSONObject(i).get("lon").toString());
			  temp.setPwsStationDisKM(array.getJSONObject(i).get("distance_km").toString());
			  temp.setPwsStationDisMI(array.getJSONObject(i).get("distance_mi").toString());
			  
			  near.add(temp);
		  }
		  return near;
	  }


	  public static ArrayList<USCity> cityData(String url) throws IOException, JSONException{
		  ArrayList<USCity> cities = new ArrayList<USCity>();
		  String u;
		  try {
			  u = readUrl(url);
			  JSONArray arr = new JSONArray(u);
			  for (int i = 0; i < arr.length(); i++){
				  JSONObject jsonobject = arr.getJSONObject(i);
				  cities.add(new USCity(jsonobject.getString("name")));


			  }
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  return cities;
	  }

	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}
	  
	  public static ArrayList<NearbyAirport> nBAirportData(String url) throws IOException, JSONException{
		  ArrayList<NearbyAirport> near = new ArrayList<NearbyAirport>();
		  JSONObject json = readJsonFromUrl(url);
		  JSONObject first = (JSONObject) json.get("location");
		  JSONObject next = (JSONObject) first.get("nearby_weather_stations");
		  JSONObject next2 = (JSONObject) next.get("airport");
		  JSONArray array = next2.getJSONArray("station");
		  
		  for (int i = 0; i < array.length(); i++){
			  NearbyAirport temp = new NearbyAirport();
			  temp.setApStationCity(array.getJSONObject(i).get("city").toString());
			  temp.setApStationState(array.getJSONObject(i).get("state").toString());
			  temp.setApStationCountry(array.getJSONObject(i).get("country").toString());
			  temp.setApStationICAO(array.getJSONObject(i).get("icao").toString());
			  temp.setApStationLat(array.getJSONObject(i).get("lat").toString());
			  temp.setApStationLong(array.getJSONObject(i).get("lon").toString());
			  
			  near.add(temp);
		  }
		  return near;
	  }
	
	
	public static ArrayList<RawTide> rawTideData(String url) throws IOException, JSONException{
		ArrayList<RawTide> tide = new ArrayList<RawTide>();
		JSONObject json = readJsonFromUrl(url);
		JSONObject first = (JSONObject) json.get("rawtide");
		JSONArray arr = first.getJSONArray("tideInfo");
		JSONArray arr2 = first.getJSONArray("rawTideObs");
		RawTide rt = new RawTide();
		
		for (int i = 0; i < arr.length(); i++){
			rt.setTideInfoSite(arr.getJSONObject(i).getString("tideSite"));
			rt.setTideInfoLat(arr.getJSONObject(i).getString("lat"));
			rt.setTideInfoLon(arr.getJSONObject(i).getString("lon"));
			rt.setTideInfoUnits(arr.getJSONObject(i).getString("units"));
			rt.setTideInfoType(arr.getJSONObject(i).getString("type"));
			rt.setTideInfoTZName(arr.getJSONObject(i).getString("tzname"));
		}
		
		for (int j = 0; j < arr2.length(); j++){
			RawTideObs rto = new RawTideObs();
			rto.setEpoch(arr2.getJSONObject(j).get("epoch").toString());
			rto.setHeight(arr2.getJSONObject(j).get("height").toString());
			rt.getRawTideObsList().add(rto);
		}
		
		tide.add(rt);
		
		return tide;
	}


	public static GeoLookup geoLookupData(String url) throws IOException, JSONException{
		GeoLookup geo = new GeoLookup();
		JSONObject json = readJsonFromUrl(url);
		JSONObject next = (JSONObject) json.get("location");
		geo.setType(next.getString("type").toString());
		geo.setCountry(next.getString("country").toString());
		geo.setCountry_iso(next.getString("country_iso3166").toString());
		geo.setCountryName(next.getString("country_name").toString());
		geo.setState(next.getString("state").toString());
		geo.setCity(next.getString("city").toString());
		geo.setTz_short(next.getString("tz_short").toString());
		geo.setTz_Long(next.getString("tz_long").toString());
		geo.setLat(next.getString("lat").toString());
		geo.setLon(next.getString("lon").toString());
		geo.setZip(next.getString("zip").toString());
		geo.setMagic(next.getString("magic").toString());
		geo.setWmo(next.getString("wmo").toString());
		geo.setL(next.getString("l").toString());
		geo.setRequestURL(next.getString("requesturl").toString());
		geo.setWuiUrl(next.getString("wuiurl").toString());

		return geo;
	}


	
	public static Astronomy astronomyData(String url) throws IOException, JSONException{
		Astronomy astro = new Astronomy();
		JSONObject json = readJsonFromUrl(url);
		JSONObject first = (JSONObject) json.get("moon_phase");
		JSONObject second = (JSONObject) first.get("sunrise");
		JSONObject third = (JSONObject) first.get("sunset");
		
		astro.setPercentIllum(first.get("percentIlluminated").toString());
		astro.setAgeOfMoon(first.get("ageOfMoon").toString());
		
		astro.setSunriseHour(second.get("hour").toString());
		astro.setSunriseMinute(second.get("minute").toString());
		astro.setSunsetHour(third.get("hour").toString());
		astro.setSunsetMinute(third.get("minute").toString());
		return astro;
	}
	
	
}

