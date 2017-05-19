package com.digitalglobe.controller;
/*******************************************************************************
 * Copyright 2017, DigitalGlobe Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * HelloController is a controller that tells the user hello and provides a movie quote.
 * 
 * @author Marjorie Lynum
 * @since 1.0
 */

@RestController
public class HelloController {
	
	private final static int QUOTE_COUNT = 16;
	private final static String ORACLE_WELCOME = "Here, take a cookie. I promise, by the time you're done eating it, you'll feel right as rain.";
	private final static String JAWS_WELCOME = "You're gonna need a bigger boat.";
	private final static String ANCHORMAN_WELCOME = "I'm Ron Burgandyyyy?";
	private final static String BATTLESTAR_WELCOME = "So say we all.";
	private final static String PREDATOR_WELCOME = "If it bleeds, we can kill it.";
	private final static String FEWGOODMEN_WELCOME = "You want me on that wall. You NEED me on that wall";
	private final static String GOODFATHER_WELCOME = "Leave the gun. Take the cannoli.";
	private final static String HUNTREDOCTOBER_WELCOME = "Re-verify our range to target... one ping only. ";
	private final static String HEAT_WELCOME = "Clean up, go home.";
	private final static String DUNE_WELCOME = "And how can this be? For he IS the Kwisatz Haderach!";
	private final static String USUAL_SUSPECTS_WELCOME = "The greatest trick the Devil "
			+ "ever pulled was convincing the world he didn't exist. " + "And like that, poof. He's gone.";
	private final static String PASSENGER57_WELCOME = "Ever played roulette?......Well, let me give you a word of advice..... Always bet on black!";
	private final static String DEVILWEARSPRADA_WELCOME = "Why is no one ready...?";
	private final static String FUNNYFARM_WELCOME = "Cue the deer.";
	private final static String PRESTIGE_WELCOME = "You always were the better magician, we both know that. "
			+ "But whatever your secret was, you will have to agree, mine is better......";
	private final static String THETOWN_WELCOME = "So in the future if you guys need to try to be slick, be slicker than a six year old.";
												 		
	String[] welcomeMessages = { ORACLE_WELCOME, JAWS_WELCOME, ANCHORMAN_WELCOME, BATTLESTAR_WELCOME, PREDATOR_WELCOME,
			FEWGOODMEN_WELCOME, GOODFATHER_WELCOME, HUNTREDOCTOBER_WELCOME, HEAT_WELCOME, DUNE_WELCOME,
			USUAL_SUSPECTS_WELCOME, PASSENGER57_WELCOME, DEVILWEARSPRADA_WELCOME, FUNNYFARM_WELCOME, PRESTIGE_WELCOME, THETOWN_WELCOME };

	/**
	 * Provide a movie quote hello
	 * 
	 * @param none
	 * @return JSON {result:<A greeting>}
	 */
	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String movieWelcomeParms(@RequestParam(value = "name", defaultValue = "World") String name) {
		
		String response = "";
		
		ObjectMapper mapper = new ObjectMapper();
	        
        ObjectNode objectNode = mapper.createObjectNode();
        
        objectNode.put("message", "Hello " + name + ", Welcome to Coastline!!!" );
        objectNode.put("moviequote",  welcomeMessages[getRandomNumber()]);
        try {
        	 response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
        }
        catch (JsonProcessingException jsonException) {
        	response = jsonException.getMessage();
        }

		return response;
	}
	
	/**
	 * Generate a random number
	 * @return
	 */
	private int getRandomNumber() {
		Random rand = new SecureRandom();
		return rand.nextInt(QUOTE_COUNT);
	}
}
