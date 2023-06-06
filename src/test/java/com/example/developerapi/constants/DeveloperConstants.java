package com.example.developerapi.constants;

import com.example.developerapi.domain.Developer;
import com.example.developerapi.domain.DeveloperDTO;

public class DeveloperConstants {
	public static final DeveloperDTO DEVELOPER_DTO = new DeveloperDTO("Joao", "Java", "Mid");
	public static final Developer DEVELOPER = new Developer("Joao", "Java", "Mid");
	public static final Developer INVALID_DEVELOPER = new Developer("", "", "");
}
