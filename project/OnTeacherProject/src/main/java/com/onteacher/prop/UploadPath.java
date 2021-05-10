package com.onteacher.prop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UploadPath {
	@Value("${aws}")
	private boolean aws;
	
	@Value("${path.ocr}")
	private String ocrPath;

	@Value("${path.board}")
	private String boardPath;
	
	@Value("${path.homework}")
	private String homeworkPath;	
	
	@Value("${path.homeworkanswer}")
	private String homeworkanswerPath;
	
	@Value("${path.stprofile}")
	private String stprofilePath;
	
	@Value("${path.thcerti}")
	private String thcertiPath;	
	
	@Value("${path.thprofile}")
	private String thprofilePath;		
	
	@Value("${path.course}")
	private String coursePath;

	public boolean isAws() {
		return aws;
	}

	public String getOcrPath() {
		return ocrPath;
	}

	public String getBoardPath() {
		return boardPath;
	}

	public String getHomeworkPath() {
		return homeworkPath;
	}

	public String getHomeworkanswerPath() {
		return homeworkanswerPath;
	}

	public String getStprofilePath() {
		return stprofilePath;
	}

	public String getThcertiPath() {
		return thcertiPath;
	}

	public String getThprofilePath() {
		return thprofilePath;
	}

	public String getCoursePath() {
		return coursePath;
	}
	
	
}
