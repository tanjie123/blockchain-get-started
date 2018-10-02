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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * 消息摘要
 * 
 * @author tanjie
 *
 */
public class MsgDigestDemo {
	public static void main(String args[]) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String msg = "Hello World!";

		MessageDigest md5Digest = MessageDigest.getInstance("MD5");
		// 更新要计算的内容
		md5Digest.update(msg.getBytes());
		// 完成哈希计算，得到摘要
		byte[] md5Encoded = md5Digest.digest();

		MessageDigest shaDigest = MessageDigest.getInstance("SHA");
		// 更新要计算的内容
		shaDigest.update(msg.getBytes());
		// 完成哈希计算，得到摘要
		byte[] shaEncoded = shaDigest.digest();

		System.out.println("原文: " + msg);
		System.out.println("MD5摘要: " + Base64.encodeBase64URLSafeString(md5Encoded));
		System.out.println("SHA摘要: " + Base64.encodeBase64URLSafeString(shaEncoded));
	}
}
