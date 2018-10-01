package com.tanjie.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlockchainGetStartedTutorial01Application {

	public static void main(String[] args) {
		SpringApplication.run(BlockchainGetStartedTutorial01Application.class, args);

		BlockChain blockChainList = new BlockChain();

		Block genesis = new Block("BlockChain", 0);
		blockChainList.add(genesis);

		Block helloBlock = new Block("Hello", blockChainList.get(blockChainList.size() - 1).getHash());
		blockChainList.add(helloBlock);

		Block worldBlock = new Block("World", blockChainList.get(blockChainList.size() - 1).getHash());
		blockChainList.add(worldBlock);

		Block dZoneBlock = new Block("Tanjie", blockChainList.get(blockChainList.size() - 1).getHash());
		blockChainList.add(dZoneBlock);

		System.out.println("---------------------");
		System.out.println("- BlockChain -");
		System.out.println("---------------------");
		blockChainList.forEach();
		System.out.println("---------------------");
		System.out.println("Is valid?: " + blockChainList.validate());
		System.out.println("---------------------");

		// corrupt block chain by modifying one of the block
		Block hiBlock = new Block("Hi", genesis.getHash());
		int index = blockChainList.indexOf(helloBlock);
		blockChainList.remove(index);
		blockChainList.add(index, hiBlock);
		System.out.println("Corrupted block chain by replacing 'Hello' block with 'Hi' Block");
		System.out.println("---------------------");
		System.out.println("- BlockChain -");
		System.out.println("---------------------");
		blockChainList.forEach();
		System.out.println("---------------------");
		System.out.println("Is valid?: " + blockChainList.validate());
		System.out.println("---------------------");
	}
}
