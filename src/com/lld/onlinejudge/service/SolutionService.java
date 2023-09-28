package com.lld.onlinejudge.service;

import com.lld.onlinejudge.constant.SupportedLanguage;
import com.lld.onlinejudge.exception.CompilationException;
import com.lld.onlinejudge.exception.NoCompilerFoundException;
import com.lld.onlinejudge.model.*;
import com.lld.uberdriverdispatch.exception.ServiceException;

import java.util.List;

public interface SolutionService {
    SolutionRunResult run(Question question, SupportedLanguage language, String solutionCode)
            throws NoCompilerFoundException, CompilationException, ServiceException;
    SolutionSubmittedResult submit(Question question, User author, SupportedLanguage language, String solutionCode)
            throws NoCompilerFoundException, CompilationException, ServiceException;
    SolutionComment doComment(Solution solution, String comment) throws ServiceException;
    List<SolutionComment> getComments(Solution solution);
    List<Solution> getSolutionsByAuthor(User author);
}
