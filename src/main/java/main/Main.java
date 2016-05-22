package main;

import java.util.HashMap;
import java.util.Map;

import util.AuthorizationUtil;
import util.HttpHelperAsync;
import util.HttpHelperAsync.Headers;
import util.HttpHelperAsync.Response;

public class Main {

	public static Response get(String consumerKey, String consumerSecret, 
			String signatureMethod, long timestamp, 
			String nonce, float version, 
			String oauthToken, String oauthTokenSecret, 
			String verifier, String url, Headers headers, Map<String, Object> parameters, long timeoutMillis) throws Exception {
		headers = headers(consumerKey, consumerSecret, signatureMethod,
				timestamp, nonce, version, oauthToken, oauthTokenSecret,
				verifier, url, headers, parameters, timeoutMillis, "GET");
		return HttpHelperAsync.get(url, headers, parameters, timeoutMillis);
	}
	
	public static Response post(String consumerKey, String consumerSecret, 
			String signatureMethod, long timestamp, 
			String nonce, float version, 
			String oauthToken, String oauthTokenSecret, 
			String verifier, String url, Headers headers, Map<String, Object> parameters, long timeoutMillis) throws Exception {
		headers = headers(consumerKey, consumerSecret, signatureMethod,
				timestamp, nonce, version, oauthToken, oauthTokenSecret,
				verifier, url, headers, parameters, timeoutMillis, "POST");
		return HttpHelperAsync.post(url, headers, parameters, timeoutMillis);
	}
	
	private static Headers headers(String consumerKey, String consumerSecret, 
			String signatureMethod, long timestamp, 
			String nonce, float version, 
			String oauthToken, String oauthTokenSecret, 
			String verifier, String url, Headers headers, Map<String, Object> parameters, long timeoutMillis, String reqType) throws Exception {
		if (null != headers) {
			Object contentType = headers.get("Content-Type");
			if (null != contentType && HttpHelperAsync.APPLICATION_JSON.equals(contentType.toString())) {
				headers.put("Authorization", AuthorizationUtil.generateAuthorizationHeader(consumerKey, consumerSecret, 
						signatureMethod, timestamp, nonce, version, 
						oauthToken, oauthTokenSecret, verifier, url, null, reqType));
				return headers;
			}
		} else {
			headers = new Headers();
		}
		headers.put("Authorization", AuthorizationUtil.generateAuthorizationHeader(consumerKey, consumerSecret, 
				signatureMethod, timestamp, nonce, version, 
				oauthToken, oauthTokenSecret, verifier, url, parameters, reqType));
		return headers;
	}
	
	/**
	 * ��ҵ������֯��Ա[��Ҫ����Ա��Ȩ]
	 * @param oauth_consumer_key  appid
	 * @param oauth_consumer_secret appSecret
	 * @param oauth_signature_method
	 * @param oauth_timestamp
	 * @param oauth_nonce
	 * @param oauth_version
	 * @param oauth_token
	 * @param oauth_token_secret
	 * @param oauth_verifier
	 * @throws Exception
	 */
	public static void getallpersons(String oauth_consumer_key, String oauth_consumer_secret, 
			long oauth_timestamp,String oauth_nonce, float oauth_version) throws Exception {
		String url = "https://www.yunzhijia.com/openapi/third/v1/opendata-control/data/getallpersons";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("eid", "4027242");
		parameters.put("time", "2008-01-01 00:00:00");
		parameters.put("begin", "0");//Ĭ��0
		parameters.put("count", "1000");//Ĭ��1000
		post(oauth_consumer_key, oauth_consumer_secret, 
				null, oauth_timestamp, 
				oauth_nonce, oauth_version, null, null, null, url, null, parameters, 0);
	}
	
	/**
	 * ������Ϣ[ֻ��Ҫ������Ȩ�������ֶ�������Ȩ�޾���]
	 * @param oauth_consumer_key  appid
	 * @param oauth_consumer_secret appSecret001abc002
	 * @param oauth_signature_method
	 * @param oauth_timestamp
	 * @param oauth_nonce
	 * @param oauth_version
	 * @param oauth_token
	 * @param oauth_token_secret
	 * @param oauth_verifier
	 * @throws Exception
	 */
	public static void getperson(String oauth_consumer_key, String oauth_consumer_secret, 
			long oauth_timestamp,String oauth_nonce, float oauth_version) throws Exception {
		String url = "https://www.yunzhijia.com/openapi/third/v1/opendata-control/data/getperson";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("eid", "4027242");
		parameters.put("openId", "5710ebdbe4b0cf1802e897ae");
		post(oauth_consumer_key, oauth_consumer_secret, 
				null, oauth_timestamp, 
				oauth_nonce, oauth_version, null, null, null, url, null, parameters, 0);
	}
	
	/**
	 * ��ȡ��ǰ���Ż�����Ϣ�����Ÿ�����[��Ҫ����Ա��Ȩ]
	 * @param oauth_consumer_key  appid
	 * @param oauth_consumer_secret appSecret001abc002
	 * @param oauth_signature_method
	 * @param oauth_timestamp
	 * @param oauth_nonce
	 * @param oauth_version
	 * @param oauth_token
	 * @param oauth_token_secret
	 * @param oauth_verifier
	 * @throws Exception
	 */
	public static void getorg(String oauth_consumer_key, String oauth_consumer_secret, 
			long oauth_timestamp, String oauth_nonce, float oauth_version) throws Exception {
		String url = "https://www.yunzhijia.com/openapi/third/v1/opendata-control/data/getorg";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("eid", "4027242");
		parameters.put("orgId", "0f5b48a7-eb3b-11e5-9fc9-ecf4bbea1498");
		post(oauth_consumer_key, oauth_consumer_secret, 
				null, oauth_timestamp, 
				oauth_nonce, oauth_version, null, null, null, url, null, parameters, 0);
	}
	
	public static void main(String[] args) throws Exception{
		String oauth_consumer_key = "10194";//appid,Ӧ��id
		String oauth_consumer_secret = "appsecret001a002b003c";//appsecret,����Ӧ���㹻�����Ա�֤��ȫ��
		long oauth_timestamp = System.currentTimeMillis()/1000;
		String oauth_nonce = String.valueOf(oauth_timestamp + AuthorizationUtil.RAND.nextInt());
		float oauth_version = 1.0f;
		
		//��ҵ������֯��Ա[��Ҫ����Ա��Ȩ]
		getallpersons(oauth_consumer_key, oauth_consumer_secret, 
				oauth_timestamp,oauth_nonce, oauth_version);
		
//		//������Ϣ[ֻ��Ҫ������Ȩ�������ֶ�������Ȩ�޾���]
//		getperson(oauth_consumer_key, oauth_consumer_secret, 
//				oauth_timestamp,oauth_nonce, oauth_version);
		
//		//��ȡ��ǰ���Ż�����Ϣ�����Ÿ�����[��Ҫ����Ա��Ȩ]
//		getorg(oauth_consumer_key, oauth_consumer_secret, 
//				oauth_timestamp,oauth_nonce, oauth_version);
		
		System.exit(0);
	}
	
}
