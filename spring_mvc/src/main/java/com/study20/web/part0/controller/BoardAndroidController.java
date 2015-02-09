package com.study20.web.part0.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.study20.web.cmm.util.MessageSetter;
import com.study20.web.part0.service.AndroidService;

@Controller
public class BoardAndroidController {
    static String MESSAGE_ID = String.valueOf(Math.random() % 100 + 1);
    static boolean SHOW_ON_IDLE = false;
    static int LIVE_TIME = 10;
    static int RETRY = 2;
 
    static String simpleApiKey = "AIzaSyD1q9Jz01RR5iJ0yQ5JdXrNropJFBE6zYA";//서버키
	
    @Resource(name="MessageSetter")
	private MessageSetter messageSetter;
	@Resource(name = "AndroidService")
	private AndroidService boardService;
	
	@RequestMapping(value="/android/bbs/list.do")
	public String loadList(HttpServletRequest request, Model model) throws Exception {
		model.addAttribute("boardList", boardService.getBoardList());
		return "android/boardList";
	}
	
	@RequestMapping(value="/android/write.do")
	public String loadWrite(Model model) throws Exception {
        List<PushInfo> arlPushInfo = new ArrayList<PushInfo>();
        
        PushInfo pushInfo = new PushInfo();
        pushInfo.setRegId("APA91bEXobrwXPu4VGoa3EZLp7G_AXY6EkV7pA_9vPUkd4wKFgNw0RUV683mYKW2PNMfDQyKac4BH-Vq8NZ3nVfYo5tuf72tG_M_HaviLs5J65LTdMtIkPftaGuKFhGocCnG-3Zy2O45D1VOlsUBNEFckNdGCrFczQ");
        pushInfo.setUserName("최대열");
        PushInfo pushInfo2 = new PushInfo();
        pushInfo2.setRegId("APA91bE8rPYUMPBpf6DX94hCb-d9B7WFCCJ0zdWPhcirwsMOW9iGCSCGk0NI0cvL0IUrhhsdJ23y8agy9wydA7svRsXyQY1BYmVPBl8UMV0ZlHE2OuZgZMXupfqXNiXvpwGFZZOMWHwR9KcjHs8A7TYjMNB8K-oC_g");
        pushInfo2.setUserName("이순란");
        PushInfo pushInfo3 = new PushInfo();
        pushInfo3.setRegId("APA91bFecjbQgOQG_7oOO1uweSuz1-bao0TS-MEAeZLU_LJ2bgpc6JwN2FIdWKB6bLYgQEMmfsWMELmHlCAtswD2tWj9Hn47LcirTCT7_zM5pPlDaazQ5PFPAg5IPlPok_fQEIhDmA-621AWcA0E23dBQaREaxRlPA");
        pushInfo3.setUserName("오진주");
        
        arlPushInfo.add(pushInfo);
        arlPushInfo.add(pushInfo2);
        arlPushInfo.add(pushInfo3);
        
        model.addAttribute("arlPushInfo", arlPushInfo);
        
        
		return "android/pushOtherUser";
	}

	
	@RequestMapping(value="/android/write.json", method=RequestMethod.POST)
	public String writeArticle(Model model, PushInfo request) throws Exception {
        List<PushInfo> arlPushInfo = new ArrayList<PushInfo>();
        String rtnMsg = null ;
		 /**
		- registration_ids : array. 1~1000 개의 아이디가 들어갈 수 있다.
		- collapse_key : message type 을 grouping 하는 녀석으로, 해당 단말이 offline 일 경우 가장 최신 메세지만 전달되는 형태다.
		- data : key-value pair.
		- delay_while_idle : message 가 바로 전송되는 것이 아니라, phone 이 active 되었을 때 collapse_key 의 가장 마지막 녀석만 전송되도록.
		- time_to_live : 단말이 offline 일 때 GCM storage 에서 얼마나 있어야 하는지를 설정함. collapse_key 와 반드시 함께 설정되야 한다.
		  */
	        try {
	            Sender sender = new Sender(simpleApiKey);//서버
	 
	            Message message = new Message.Builder()
	                                    .collapseKey(MESSAGE_ID)//messageid 값으로 받아옴?
	                                    .delayWhileIdle(SHOW_ON_IDLE)
	                                    .timeToLive(LIVE_TIME)
	                                    .addData(PushMessage.ticker.getValue(), request.getTicker())
	                                    .addData(PushMessage.title.getValue(), request.getTitle())
	                                    .addData(PushMessage.msg.getValue(), request.getMessage())
	                                    .addData("articleId", Integer.toString(218593991))
	                                    .build();
	            //message, 단말기 별 전달받은 키값 -regId
	            List<String> arlRegId = new ArrayList<String>();

	            String[] arlPrmRegId = request.getArlRegId();
	            for (String prmRegId : arlPrmRegId) {
	            	arlRegId.add(prmRegId);
				}
	            //1000개
	            MulticastResult result = sender.send(message, arlRegId, RETRY);//메세지,단말기 id
	            
	            rtnMsg = result.toString();
	            System.out.println(result);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
		messageSetter.message0000(model, "test");
		
		return "redirect:/android/write.do";
	}
}
