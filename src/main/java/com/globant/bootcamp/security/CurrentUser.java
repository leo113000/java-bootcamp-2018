package com.globant.bootcamp.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
/**
 * This meta-annotation is for wrapper the logged user in RestControllers
 */
public @interface CurrentUser {
}
