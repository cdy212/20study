package com.study20.web.cmm.util;

import java.util.HashMap;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository("MessageSetter")
public class MessageSetter {
	public void messageSet(Model model, String resultCode, Object data, String error) {
		HashMap<String, Object> messageSet = new HashMap<String, Object>();
		
		messageSet.put("result", resultCode);
		messageSet.put("data", data);
		messageSet.put("error", error);
		
		model.addAttribute("response", messageSet);
	}
	
	public void message0000(Model model) {
		HashMap<String, Object> messageSet = new HashMap<String, Object>();
		
		messageSet.put("result", MessageCode.result0000);
		messageSet.put("data", "");
		messageSet.put("error", "");
		
		model.addAttribute("response", messageSet);
	}
	
	public void message0000(Model model, Object data) {
		HashMap<String, Object> messageSet = new HashMap<String, Object>();
		
		messageSet.put("result", MessageCode.result0000);
		messageSet.put("data", data);
		messageSet.put("error", "");
		
		model.addAttribute("response", messageSet);
	}
}
