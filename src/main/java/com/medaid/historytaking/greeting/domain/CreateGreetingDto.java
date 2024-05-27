package com.medaid.historytaking.greeting.domain;

import java.io.Serializable;


public record CreateGreetingDto(String language, String message) implements Serializable {
	
}