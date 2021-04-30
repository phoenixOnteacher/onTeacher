package com.onteacher.vo;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("article")
public class Article {

	private int id; // PK

	private String title;
	private String content;
	private MultipartFile file;
	private String filename;
	private String created_at; // Date
	private int hits;
	private int user_id; // FK
	private int s_row;
	private int e_row;

	public int getS_row() {
		return s_row;
	}

	public void setS_row(int s_row) {
		this.s_row = s_row;
	}

	public int getE_row() {
		return e_row;
	}

	public void setE_row(int e_row) {
		this.e_row = e_row;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}