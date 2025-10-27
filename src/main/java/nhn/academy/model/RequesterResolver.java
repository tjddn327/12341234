package nhn.academy.model;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.LocaleResolver;

public class RequesterResolver implements HandlerMethodArgumentResolver {
    private LocaleResolver localeResolver;
    public RequesterResolver(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Requester.class)
                && parameter.hasParameterAnnotation(Auth.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        // 로직
        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        return new Requester(httpServletRequest.getRemoteAddr(), localeResolver.resolveLocale(httpServletRequest));
    }
}