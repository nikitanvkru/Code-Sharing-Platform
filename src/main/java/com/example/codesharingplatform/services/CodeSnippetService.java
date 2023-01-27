package com.example.codesharingplatform.services;

import com.example.codesharingplatform.entities.CodeSnippet;
import com.example.codesharingplatform.exceptions.CodeSnippetNotFoundException;
import com.example.codesharingplatform.repositories.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeSnippetService {
    @Autowired
    private CodeRepository codeRepository;

    /**
     *
     */
    public CodeSnippet createCodeSnippet(final CodeSnippet codeSnippet) {
        return codeRepository.save(codeSnippet);
    }

    public CodeSnippet getCodeSnippetById(String UUID) {
        CodeSnippet codeSnippet = codeRepository.findAll()
                .stream()
                .filter(x -> x.getUuid().equals(UUID))
                .findFirst()
                .orElse(null);
        if (codeSnippet == null) throw new CodeSnippetNotFoundException();
        else {
            if (codeSnippet.isTimeRestricted() && codeSnippet.isViewsRestricted()) {
                return validateBoth(codeSnippet);
            } else if (codeSnippet.isTimeRestricted()) {
                return validate(codeSnippet, "time");
            } else if (codeSnippet.isViewsRestricted()) {
                return validate(codeSnippet, "views");
            } else {
                return codeSnippet;
            }
        }

    }

    public List<CodeSnippet> getRecentCodeSnippets() {
        return codeRepository.findAll()
                .stream()
                .filter(x -> x.getTime() <= 0 && x.getViews() <= 0)
                .sorted(Comparator.comparing(CodeSnippet::getDate).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    private CodeSnippet validate(CodeSnippet codeSnippet, String validate) {
        int toValid = validate.equals("time") ? codeSnippet.getTime() : codeSnippet.getViews();
        if (toValid > 0) {
            if (validate.equals("time")) {
                codeSnippet.setTime();
            } else {
                codeSnippet.setViews();
            }
            codeRepository.save(codeSnippet);
            return codeSnippet;
        } else {
            codeRepository.delete(codeSnippet);
            throw new CodeSnippetNotFoundException();
        }
    }

    private CodeSnippet validateBoth(CodeSnippet codeSnippet) {
        if (codeSnippet.getTime() > 0 && codeSnippet.getViews() > 0) {
            codeSnippet.setTime();
            codeSnippet.setViews();
            codeRepository.save(codeSnippet);
            return codeSnippet;
        } else {
            codeRepository.delete(codeSnippet);
            throw new CodeSnippetNotFoundException();
        }
    }


}
