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

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author tanjie
 *
 */
@NoArgsConstructor
public class BlockChain {
	private List<Block> blockChainList = new ArrayList<>();

	public void add(Block block) {
		blockChainList.add(block);
	}

	public Block get(int i) {
		return blockChainList.get(i);
	}

	public void remove(int i) {
		blockChainList.remove(i);
	}

	public void add(int index, Block block) {
		blockChainList.add(index, block);
	}

	public int indexOf(Block block) {
		return blockChainList.indexOf(block);
	}

	public void forEach() {
		blockChainList.forEach(System.out::println);
	}

	public int size() {
		return blockChainList.size();
	}

	public boolean validate() {
		boolean result = true;
		Block lastBlock = null;
		for (int i = blockChainList.size() - 1; i >= 0; i--) {
			if (lastBlock == null) {
				lastBlock = blockChainList.get(i);
			} else {
				Block current = blockChainList.get(i);
				if (lastBlock.getPreviousHash() != current.getHash()) {
					result = false;
					break;
				}
				lastBlock = current;
			}
		}
		return result;
	}
}
