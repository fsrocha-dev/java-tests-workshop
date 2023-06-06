package com.example.developerapi.constants;

import com.example.developerapi.domain.Developer;

public class DeveloperConstants {
	public static final Developer DEVELOPER = new Developer("Joao", "Java", "Mid");
	public static final Developer INVALID_DEVELOPER = new Developer("", "", "");
}
