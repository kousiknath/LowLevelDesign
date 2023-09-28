package com.lld.onlinejudge.factory;

import com.lld.onlinejudge.constant.SupportedLanguage;
import com.lld.onlinejudge.exception.NoCompilerFoundException;
import com.lld.onlinejudge.service.compiler.CompilerService;
import com.lld.onlinejudge.service.impl.compiler.CppCompilerServiceImpl;
import com.lld.onlinejudge.service.impl.compiler.GoCompilerServiceImpl;
import com.lld.onlinejudge.service.impl.compiler.JavaCompilerServiceImpl;

public class CompilerServiceSimpleFactory {
    public static CompilerService getCompilerService(SupportedLanguage language) throws NoCompilerFoundException {
        switch (language) {
            case JAVA -> {
                return new JavaCompilerServiceImpl();
            }

            case CPP -> {
                return new CppCompilerServiceImpl();
            }

            case GOLANG -> {
                return new GoCompilerServiceImpl();
            }
        }

        throw new NoCompilerFoundException("No supported compiler found");
    }
}
