/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility_pkg;

/**
 *
 * @author Rob
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class ParserJSON {
     
   public static void main(String[] args) {
 
	JSONParser parser = new JSONParser();
 
	try {
 
		Object obj = parser.parse(new FileReader("C://Users//Rob//Documents//NetBeansProjects//OnLineStowagePlanningSolutionChecker//OnLineStowagePlanningSolutionChecker-war//web/vessel_profile_57665.json"));
 
		JSONObject jsonObject = (JSONObject) obj;
                
                ////****************PARSING*********************************************************
		String group = (String) jsonObject.get("group");
		System.out.println(group);
 
                String profile_type = (String) jsonObject.get("profile_type");
		System.out.println(profile_type);
 
                String vessel_code = (String) jsonObject.get("vessel_code");
		System.out.println(vessel_code);
 
                String nb_bays = (String) jsonObject.get("nb_bays");
		int nb_bays_int = Integer.parseInt(nb_bays);
                
                String nb_layout_stacks = (String) jsonObject.get("nb_layout_stacks");
		int nb_layout_stacks_int = Integer.parseInt(nb_layout_stacks);
                
                String nb_layout_tiers = (String) jsonObject.get("nb_layout_tiers");
		int nb_layout_tiers_int = Integer.parseInt(nb_layout_tiers);
                
                String hatch_tier_idx = (String) jsonObject.get("hatch_tier_idx");
		int hatch_tier_idx_int = Integer.parseInt(hatch_tier_idx);
                
                String nb_locations = (String) jsonObject.get("nb_locations");
		int nb_locations_int = Integer.parseInt(nb_locations);
 
                JSONArray observer = (JSONArray) jsonObject.get("observer");
                
                String vessel_length = (String) jsonObject.get("vessel_length");
		float vessel_length_float = Float.parseFloat(vessel_length);
                
                String first_bay = (String) jsonObject.get("first_bay");
		float first_bay_float = Float.parseFloat(first_bay);
                
                String ap = (String) jsonObject.get("ap");
		float ap_float = Float.parseFloat(ap);
                
                JSONArray extreme_points = (JSONArray) jsonObject.get("extreme_points");
                
                String nominal_intake = (String) jsonObject.get("nominal_intake");
		int nominal_intake_int = Integer.parseInt(nominal_intake);
                
                String min_gm = (String) jsonObject.get("min_gm");
		float min_gm_float = Float.parseFloat(min_gm);
                
                JSONArray trim_bounds = (JSONArray) jsonObject.get("trim_bounds");
                
                String support_tiers_above_lashing = (String) jsonObject.get("support_tiers_above_lashing");
		int support_tiers_above_lashing_int = Integer.parseInt(support_tiers_above_lashing);
                
                String difference_support_locations = (String) jsonObject.get("difference_support_locations");
		int difference_support_locations_int = Integer.parseInt(difference_support_locations);
                
                String initial_stack = (String) jsonObject.get("initial_stack");
		int initial_stack_int = Integer.parseInt(initial_stack);
                		
                String lowest_tier_on = (String) jsonObject.get("lowest_tier_on");
		int lowest_tier_on_int = Integer.parseInt(lowest_tier_on);
                
                String lowest_tier_below = (String) jsonObject.get("lowest_tier_below");
		int lowest_tier_below_int = Integer.parseInt(lowest_tier_below);
                
                JSONArray cells = (JSONArray) jsonObject.get("cells");
                
                JSONArray stacks = (JSONArray) jsonObject.get("stacks");
                
                JSONArray tanks = (JSONArray) jsonObject.get("tanks");
                
                JSONArray tanks_cg = (JSONArray) jsonObject.get("tanks_cg");
                
                JSONArray lightship_data = (JSONArray) jsonObject.get("lightship_data");
                
                JSONArray lightship_blocks = (JSONArray) jsonObject.get("lightship_blocks");
                
                JSONArray constant_blocks = (JSONArray) jsonObject.get("constant_blocks");
                
                JSONArray frames = (JSONArray) jsonObject.get("frames");
                
                JSONArray bonjean_stations = (JSONArray) jsonObject.get("bonjean_stations");
                
                JSONArray bonjean_drafts = (JSONArray) jsonObject.get("bonjean_drafts");
                
                JSONArray bonjean_areas = (JSONArray) jsonObject.get("bonjean_areas");
                
                JSONArray hydrostatics = (JSONArray) jsonObject.get("hydrostatics");
                
                
                ////*******************************************************************************************
		////*******************************************************************************************
                
                // loop array
		JSONArray msg = (JSONArray) jsonObject.get("messages");
		Iterator<String> iterator = msg.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}
 
     }
 
}
