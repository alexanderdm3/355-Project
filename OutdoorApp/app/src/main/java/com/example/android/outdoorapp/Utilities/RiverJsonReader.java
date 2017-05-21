package com.example.android.outdoorapp.Utilities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jlaidlaw on 4/2/17.
 *
 * Created class to handle parsing out of
 * river conditions from usgs response
 * to make the code more readable.
 */

public class RiverJsonReader {

    private JSONObject riverData;
    //sitename
    private final String valueObject = "value";
    private final String timeSeriesArray = "timeSeries";
    private final int arrayPositionOne = 0;
    private final int arrayPositionTwo = 1;
    private final String sourceObject = "sourceInfo";
    private final String geoObject = "geoLocation";
    private final String geoGObject = "geogLocation";
    private final String varObject = "variable";
    private final String varNameObject = "variableName";
    private final String varDescObject = "variableDescription";
    private final String valsObject = "values";
    private final String siteName = "siteName";
    private final String longitude = "longitude";
    private final String latitude = "latitude";
    private final String valueField = "value";

    public RiverJsonReader(JSONObject river){
        setJSONRiver(river);
    }

    private void setJSONRiver(JSONObject jsonObject){
        this.riverData = jsonObject;
    }
    /*
Parse out site name from JSON object returned by https request
*/
    public String getSiteName() {

        String thisSiteName = "";

        try {
            JSONObject first_obj = this.riverData.getJSONObject(valueObject);
            JSONArray first_arr = first_obj.getJSONArray(timeSeriesArray);
            JSONObject second_obj = first_arr.getJSONObject(arrayPositionOne);
            JSONObject third_obj = second_obj.getJSONObject(sourceObject);
            thisSiteName = third_obj.getString(siteName);
        } catch (JSONException j) {
            Log.e("Site Error", "Error parsing site name");
        }
        return thisSiteName;
    }

    /*
   Parse out longitude from JSON object returned by https request

   */
    public double getLongitude() {

        double thisLongitude = 0;

        try {
            //get first JSON object with passed in json object
            JSONObject first_obj = this.riverData.getJSONObject(valueObject);

            //get JSON array from within object
            JSONArray first_arr = first_obj.getJSONArray(timeSeriesArray);

            //get object from first pos of array
            JSONObject second_obj = first_arr.getJSONObject(arrayPositionOne);

            //get object from within that object
            JSONObject third_obj = second_obj.getJSONObject(sourceObject);

            // go into this object
            JSONObject fourth_obj = third_obj.getJSONObject(geoObject);

            // go get this last object
            JSONObject fifth_obj = fourth_obj.getJSONObject(geoGObject);

            //access longitude field from this object
            thisLongitude = fifth_obj.getDouble(longitude);

        } catch (JSONException j) {
            Log.e("Geo Error", "Error parsing longitude");
        }

        return thisLongitude;
    }

    /*
   Parse out latitude from JSON object returned by https request

   */
    public double getLatitude() {

        double thisLatitude = 0;

        try {
            //get first JSON object with passed in json object
            JSONObject first_obj = this.riverData.getJSONObject(valueObject);

            //get JSON array from within object
            JSONArray first_arr = first_obj.getJSONArray(timeSeriesArray);

            //get object from first pos of array
            JSONObject second_obj = first_arr.getJSONObject(arrayPositionOne);

            //get object from within that object
            JSONObject third_obj = second_obj.getJSONObject(sourceObject);

            // go into this object
            JSONObject fourth_obj = third_obj.getJSONObject(geoObject);

            // go get this last object
            JSONObject fifth_obj = fourth_obj.getJSONObject(geoGObject);

            //access longitude field from this object
            thisLatitude = fifth_obj.getDouble(latitude);

        } catch (JSONException j) {
            Log.e("Geo Error", "Error parsing latitude");
        }

        return thisLatitude;
    }

    /*
   Parse out discharge description from JSON object returned by https request

   */
    public String getDischargeDescription() {


        String desc = "";
        try {
            //get first JSON object with passed in json object
            JSONObject first_obj = this.riverData.getJSONObject(valueObject);

            //get JSON array from within object
            JSONArray first_arr = first_obj.getJSONArray(timeSeriesArray);

            //get object from first pos of array
            JSONObject second_obj = first_arr.getJSONObject(arrayPositionOne);

            //Get for variable info, first variable is streamflow
            JSONObject flowVar = second_obj.getJSONObject(varObject);

            //JSONArray flowDetail = flowVar.getJSONArray("variableCode");
            desc = flowVar.getString(varDescObject);

        } catch (JSONException j) {
            Log.e("Discharge Error", "Error parsing discharge description");
        }

        return desc;

    }

    /*
   Parse out discharge amount from JSON object returned by https request

   */
    public String getDischargeAmount() {


        String amount = "";
        try {

            //get first JSON object with passed in json object
            JSONObject first_obj = this.riverData.getJSONObject(valueObject);

            //get JSON array from within object
            JSONArray first_arr = first_obj.getJSONArray(timeSeriesArray);

            //get object from first pos of array
            JSONObject second_obj = first_arr.getJSONObject(arrayPositionOne);

            //Get for values array inside time series array
            JSONArray values = second_obj.getJSONArray(valsObject);

            //get first obj in that values array
            JSONObject outer_first_value = values.getJSONObject(arrayPositionOne);

            //get the value array in that object
            JSONArray value_array = outer_first_value.getJSONArray(valueObject);

            //get the first object in that value array
            JSONObject inner_first_value = value_array.getJSONObject(arrayPositionOne);

            //set discharge amount to that value field
            amount = inner_first_value.getString(valueField);


        } catch (JSONException j) {
            Log.e("Discharge Error", "Error parsing discharge amount");
        }
        return amount;
    }

    /*
   Parse out gage description from JSON object returned by https request

   */
    public String getGageDescription() {

        String gageDesc = "";

        try {
            //get first JSON object with passed in json object
            JSONObject first_obj = this.riverData.getJSONObject(valueObject);

            //get JSON array from within object
            JSONArray first_arr = first_obj.getJSONArray(timeSeriesArray);

            //get object from second pos of array
            JSONObject second_obj = first_arr.getJSONObject(arrayPositionTwo);

            //Get for variable info, second variable is gage height
            JSONObject gage = second_obj.getJSONObject(varObject);

            //JSONArray flowDetail = flowVar.getJSONArray("variableCode");
            gageDesc = gage.getString(varNameObject);

        } catch (JSONException j) {
            Log.e("Gage Description Error", "Error parsing gage description");
        }
        return gageDesc;
    }

    /*
    Parse out gage height from JSON object returned by https request

    */
    public String getGageHeight() {

        String amount = "";

        try {

            //get first JSON object with passed in json object
            JSONObject first_obj = this.riverData.getJSONObject(valueObject);

            //get JSON array from within object
            JSONArray first_arr = first_obj.getJSONArray(timeSeriesArray);

            //get object from first pos of array
            JSONObject second_obj = first_arr.getJSONObject(arrayPositionTwo);

            //Get for values array inside time series array
            JSONArray values = second_obj.getJSONArray(valsObject);

            //get first obj in that values array
            JSONObject outer_first_value = values.getJSONObject(arrayPositionOne);

            //get the value array in that object
            JSONArray value_array = outer_first_value.getJSONArray(valueObject);

            //get the first object in that value array
            JSONObject inner_first_value = value_array.getJSONObject(arrayPositionOne);

            //set discharge amount to that value field
            amount = inner_first_value.getString(valueField);


        } catch (JSONException j) {
            Log.e("Gage Height Error", "Error parsing gage height");
        }
        return amount;
    }
}