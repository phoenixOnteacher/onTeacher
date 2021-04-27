package com.onteacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.onteacher.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	SearchService searchService;
}
