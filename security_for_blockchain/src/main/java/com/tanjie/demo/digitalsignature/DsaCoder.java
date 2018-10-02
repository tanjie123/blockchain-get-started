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
package com.tanjie.demo.digitalsignature;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 基于DSA的数字签名
 * 
 * @author tanjie
 *
 */
public class DsaCoder {
	public static final String KEY_ALGORITHM = "DSA";

	public enum DsaTypeEn {
		MD5withDSA, SHA1withDSA
	}

	/**
	 * DSA密钥长度默认1024位。 密钥长度必须是64的整数倍，范围在512~1024之间
	 */
	private static final int KEY_SIZE = 1024;

	private KeyPair keyPair;

	public DsaCoder() throws Exception {
		keyPair = initKey();
	}

	public byte[] signature(byte[] data, byte[] privateKey) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey key = keyFactory.generatePrivate(keySpec);

		Signature signature = Signature.getInstance(DsaTypeEn.SHA1withDSA.name());
		signature.initSign(key);
		signature.update(data);
		return signature.sign();
	}

	public boolean verify(byte[] data, byte[] publicKey, byte[] sign) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey key = keyFactory.generatePublic(keySpec);

		Signature signature = Signature.getInstance(DsaTypeEn.SHA1withDSA.name());
		signature.initVerify(key);
		signature.update(data);
		return signature.verify(sign);
	}

	private KeyPair initKey() throws Exception {
		// 初始化密钥对生成器
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		// 实例化密钥对生成器
		keyPairGen.initialize(KEY_SIZE);
		// 实例化密钥对
		return keyPairGen.genKeyPair();
	}

	public byte[] getPublicKey() {
		return keyPair.getPublic().getEncoded();
	}

	public byte[] getPrivateKey() {
		return keyPair.getPrivate().getEncoded();
	}

	public static void main(String[] args) throws Exception {
		String msg = "Hello World";
		DsaCoder dsa = new DsaCoder();
		byte[] sign = dsa.signature(msg.getBytes(), dsa.getPrivateKey());
		boolean flag = dsa.verify(msg.getBytes(), dsa.getPublicKey(), sign);
		String result = flag ? "数字签名匹配" : "数字签名不匹配";
		System.out.println("数字签名：" + Base64.encodeBase64URLSafeString(sign));
		System.out.println("验证结果：" + result);
	}

}
