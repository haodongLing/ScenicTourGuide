package com.haodong.scenictourguide.common.net.api;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 网络工具类。
 */
public   class WebUtils {

	private static final String METHOD_LIST = "LIST";
//	private static final String METHOD_POST = "POST";
//	private static final String METHOD_POST = "POST";
	private static final String METHOD_POST = "POST";
	private static final String METHOD_GET = "GET";

	public static class VerisignTrustManager implements X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//			Exception exp = null;
//			for (X509Certificate cert : chain) {
//				cert.checkValidity(); // 验证证书有效期
//				try {
//					cert.verify(verisign.getPublicKey());// 验证签名
//					exp = null;
//					break;
//				} catch (Exception e) {
//					exp = e;
//				}
//			}
//
//			if (exp != null) {
//				throw new CertificateException(exp);
//			}
		}
	}
	
	
	public static class TrustAllTrustManager implements X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
	}
	
	

	


}
