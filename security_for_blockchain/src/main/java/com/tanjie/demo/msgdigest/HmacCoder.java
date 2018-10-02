/*
 * Copyright 2016-2018 Tanjie.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */
package com.tanjie.demo.msgdigest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 基于HMac算法的消息摘要
 * 
 * @author tanjie
 *
 */
public class HmacCoder {
	/**
	 * JDK支持HmacMD5, HmacSHA1,HmacSHA256, HmacSHA384, HmacSHA512
	 */
	public enum HmacTypeEn {
	HmacMD5, HmacSHA1, HmacSHA256, HmacSHA384, HmacSHA512;
	}

	public static byte[] encode(byte[] plaintext, byte[] secretKey, HmacTypeEn type) throws Exception {
		SecretKeySpec keySpec = new SecretKeySpec(secretKey, type.name());
		Mac mac = Mac.getInstance(keySpec.getAlgorithm());
		mac.init(keySpec);
		return mac.doFinal(plaintext);
	}

	public static void main(String[] args) throws Exception {
		String msg = "Hello World!";
		byte[] secretKey = "Secret_Key".getBytes("UTF8");
		byte[] digest = HmacCoder.encode(msg.getBytes(), secretKey, HmacTypeEn.HmacSHA256);
		System.out.println("原文: " + msg);
		System.out.println("摘要: " + Base64.encodeBase64URLSafeString(digest));
	}

}
