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
package com.tanjie.demo.merkle;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanjie
 *
 */
public class MerkleTrees {
	// transaction List
	List<String> txList;
	// Merkle Root
	String root;

	/**
	 * constructor
	 * 
	 * @param txList transaction List 交易List
	 */
	public MerkleTrees(List<String> txList) {
		this.txList = txList;
		root = "";
	}

	/**
	 * execute merkle_tree and set root.
	 */
	public void merkle_tree() {

		List<String> tempTxList = new ArrayList<String>();

		for (int i = 0; i < this.txList.size(); i++) {
			tempTxList.add(this.txList.get(i));
		}

		List<String> newTxList = getNewTxList(tempTxList);

		// 执行循环，直到只剩下一个hash值
		while (newTxList.size() != 1) {
			newTxList = getNewTxList(newTxList);
		}

		this.root = newTxList.get(0);
	}

	/**
	 * return Node Hash List.
	 * 
	 * @param tempTxList
	 * @return
	 */
	private List<String> getNewTxList(List<String> tempTxList) {

		List<String> newTxList = new ArrayList<String>();
		int index = 0;
		while (index < tempTxList.size()) {
			// left
			String left = tempTxList.get(index);
			index++;
			// right
			String right = "";
			if (index != tempTxList.size()) {
				right = tempTxList.get(index);
			}
			// sha2 hex value
			String sha2HexValue = getSHA2HexValue(left + right);
			newTxList.add(sha2HexValue);
			index++;

		}

		return newTxList;
	}

	/**
	 * Return hex string
	 * 
	 * @param str
	 * @return
	 */
	public String getSHA2HexValue(String str) {
		byte[] cipher_byte;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			cipher_byte = md.digest();
			StringBuilder sb = new StringBuilder(2 * cipher_byte.length);
			for (byte b : cipher_byte) {
				sb.append(String.format("%02x", b & 0xff));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * Get Root
	 * 
	 * @return
	 */
	public String getRoot() {
		return this.root;
	}
}
