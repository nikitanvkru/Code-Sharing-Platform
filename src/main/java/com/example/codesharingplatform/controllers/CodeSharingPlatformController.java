package com.example.codesharingplatform.controllers;

import com.example.codesharingplatform.entities.CodeSnippet;
import com.example.codesharingplatform.models.CodeSnippetRequest;
import com.example.codesharingplatform.services.CodeSnippetService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CodeSharingPlatformController {
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String TEXT_HTML = "text/html";
    private final CodeSnippetService codeSnippetService;


    public CodeSharingPlatformController(CodeSnippetService codeSnippetService) {
        this.codeSnippetService = codeSnippetService;
    }


    /**
     * API name: Get quiz by id
     * HTTP method: Get
     *
     * @return quiz obj from given id
     * @path api/quizzes/{id}
     */
    @PostMapping(value = "/api/code/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, String>> createCode(@RequestBody CodeSnippetRequest request) {
        CodeSnippet codeSnippet = codeSnippetService
                .createCodeSnippet(new CodeSnippet(request.getCode(), request.getTime(), request.getViews()));
        Map<String, String> response = new HashMap<>();
        response.put("id", codeSnippet.getUuid());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/api/code/{UUID}")
    @ResponseBody
    public CodeSnippet getCode(@PathVariable String UUID) {
        return codeSnippetService.getCodeSnippetById(UUID);
    }

    @GetMapping("/api/code/latest")
    @ResponseBody
    public ResponseEntity<List<CodeSnippet>> getRecentlyCreatedCodes() {
        List<CodeSnippet> recentCodeSnippets = codeSnippetService.getRecentCodeSnippets();
        return new ResponseEntity<>(recentCodeSnippets, HttpStatus.OK);
    }

    @GetMapping("/code/new")
    public ModelAndView getCodeCreationPage(HttpServletResponse response) {
        response.addHeader(CONTENT_TYPE, TEXT_HTML);

        ModelAndView view = new ModelAndView();
        view.setViewName("createCode");

        return view;
    }

    @GetMapping("/code/{UUID}")
    public ModelAndView getCodePageById(HttpServletResponse response, @PathVariable String UUID) {
        response.addHeader(CONTENT_TYPE, TEXT_HTML);

        CodeSnippet codeSnippet = codeSnippetService.getCodeSnippetById(UUID);
        ModelAndView model = new ModelAndView();
        if (codeSnippet == null) {
            model.setViewName("error");
        } else {
            model.addObject("codeSnippet", codeSnippet);
            model.setViewName("code");
        }
        return model;

    }

    @GetMapping("/code/latest")
    public ModelAndView getRecentCodesPage(HttpServletResponse response) {
        response.addHeader(CONTENT_TYPE, TEXT_HTML);

        List<CodeSnippet> recentCodes = codeSnippetService.getRecentCodeSnippets();

        ModelAndView model = new ModelAndView();
        model.addObject("recentCodes", recentCodes);
        model.setViewName("latest");

        return model;
    }
}
