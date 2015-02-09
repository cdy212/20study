package com.study20.web.part0.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class Push {
	   static String MESSAGE_ID = String.valueOf(Math.random() % 100 + 1);
	    static boolean SHOW_ON_IDLE = false;
	    static int LIVE_TIME = 10;
	    static int RETRY = 2;
	 
	    static String simpleApiKey = "AIzaSyD1q9Jz01RR5iJ0yQ5JdXrNropJFBE6zYA";//서버키
	    //String gcmURL = "https://android.googleapis.com/gcm/send";
	    
	public static void main(String[] args) {
		  String rtnMsg = null ;
		  
	        try {
	            Sender sender = new Sender(simpleApiKey);//서버
	 
	            Message message = new Message.Builder()
	                                    .collapseKey(MESSAGE_ID)//messageid 값으로 받아옴?
	                                    .delayWhileIdle(SHOW_ON_IDLE)
	                                    .timeToLive(LIVE_TIME)
	                                    .addData(PushMessage.title.getValue(), "메세지 타이틀")
	                                    .addData(PushMessage.msg.getValue(), "메세지 입니다.")
	                                    .addData(PushMessage.ticker.getValue(), "이순란 댈 싸워서 이겨라!")
	                                    .addData("articleId", Integer.toString(218593991))
	                                    .build();
	            //message, 단말기 별 전달받은 키값 -regId
	            //대열
	            //Result result = sender.send(message, "APA91bEKqvjpIg-9uF1ttXgDzS5CVvo0vAMRxcQMojJ6zhUxFYk9r5ZQ_Bx8HbYy5SEGe2IU7vVvIZlodDoa_vnerFE32wQmXT8woDlbAjOClBWkGmkQ3mEXewEF83MvOrkGyM8hjEjx1T9gJxnSC-mxb5RTFgnvayDgsKAZ218n4lngoFgTmzI", RETRY);//메세지,단말기 id
	            //순란
	            //Result result = sender.send(message, "APA91bE8rPYUMPBpf6DX94hCb-d9B7WFCCJ0zdWPhcirwsMOW9iGCSCGk0NI0cvL0IUrhhsdJ23y8agy9wydA7svRsXyQY1BYmVPBl8UMV0ZlHE2OuZgZMXupfqXNiXvpwGFZZOMWHwR9KcjHs8A7TYjMNB8K-oC_g", RETRY);//메세지,단말기 id
	            //진주
	            //APA91bFecjbQgOQG_7oOO1uweSuz1-bao0TS-MEAeZLU_LJ2bgpc6JwN2FIdWKB6bLYgQEMmfsWMELmHlCAtswD2tWj9Hn47LcirTCT7_zM5pPlDaazQ5PFPAg5IPlPok_fQEIhDmA-621AWcA0E23dBQaREaxRlPA

	            //1000개
	            List<String> arlRegId = new ArrayList<String>();
	            
	            //arlRegId.add("APA91bFecjbQgOQG_7oOO1uweSuz1-bao0TS-MEAeZLU_LJ2bgpc6JwN2FIdWKB6bLYgQEMmfsWMELmHlCAtswD2tWj9Hn47LcirTCT7_zM5pPlDaazQ5PFPAg5IPlPok_fQEIhDmA-621AWcA0E23dBQaREaxRlPA");
	            //arlRegId.add("APA91bE8rPYUMPBpf6DX94hCb-d9B7WFCCJ0zdWPhcirwsMOW9iGCSCGk0NI0cvL0IUrhhsdJ23y8agy9wydA7svRsXyQY1BYmVPBl8UMV0ZlHE2OuZgZMXupfqXNiXvpwGFZZOMWHwR9KcjHs8A7TYjMNB8K-oC_g");
	            arlRegId.add("APA91bEKqvjpIg-9uF1ttXgDzS5CVvo0vAMRxcQMojJ6zhUxFYk9r5ZQ_Bx8HbYy5SEGe2IU7vVvIZlodDoa_vnerFE32wQmXT8woDlbAjOClBWkGmkQ3mEXewEF83MvOrkGyM8hjEjx1T9gJxnSC-mxb5RTFgnvayDgsKAZ218n4lngoFgTmzI");
	            
	            //arlRegId.add("APA91bEKqvjpIg-9uF1ttXgDzS5CVvo0vAMRxcQMojJ6zhUxFYk9r5ZQ_Bx8HbYy5SEGe2IU7vVvIZlodDoa_vnerFE32wQmXT8woDlbAjOClBWkGmkQ3mEXewEF83MvOrkGyM8hjEjx1T9gJxnSC-mxb5RTFgnvayDgsKAZ218n4lngoFgTmzI");
	            //arlRegId.add("APA91bE8rPYUMPBpf6DX94hCb-d9B7WFCCJ0zdWPhcirwsMOW9iGCSCGk0NI0cvL0IUrhhsdJ23y8agy9wydA7svRsXyQY1BYmVPBl8UMV0ZlHE2OuZgZMXupfqXNiXvpwGFZZOMWHwR9KcjHs8A7TYjMNB8K-oC_g");
	            
	            MulticastResult result = sender.send(message, arlRegId, RETRY);//메세지,단말기 id
	            
//				안드로이드에서 받는 값들	            
//	            onMessage. key = pushMsg, value = 푸쉬 테스트입니다!!!
//	            onMessage. key = from, value = 214053910266
//	            onMessage. key = articleId, value = 218593991
//	            onMessage. key = collapse_key, value = 1.1778277064054752
	            rtnMsg = result.toString();
	            System.out.println(result);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	}

}
