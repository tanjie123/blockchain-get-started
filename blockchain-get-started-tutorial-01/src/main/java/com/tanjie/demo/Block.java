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
package com.tanjie.demo;

import java.util.Arrays;

import lombok.Getter;
import lombok.ToString;

/**
 * @author tanjie
 *
 */
@Getter
@ToString
public class Block {
	private int previousHash;
	private String data;
	private int hash;

	public Block(String data, int previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		// Mix the content of this block with previous hash to create the hash of this
		// new block
		// and that's what makes it block chain
		this.hash = Arrays.hashCode(new Integer[] { data.hashCode(), previousHash });
	}
}
